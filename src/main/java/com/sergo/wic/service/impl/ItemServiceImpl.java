package com.sergo.wic.service.impl;

import com.sergo.wic.converter.ItemConverter;
import com.sergo.wic.coordinate_referense_system.CRSResolver;
import com.sergo.wic.dto.CoordinatesDto;
import com.sergo.wic.dto.ItemDto;
import com.sergo.wic.entities.Item;
import com.sergo.wic.entities.Share;
import com.sergo.wic.entities.User;
import com.sergo.wic.entities.UserItem;
import com.sergo.wic.entities.enums.ItemState;
import com.sergo.wic.repository.ItemRepository;
import com.sergo.wic.repository.PointsRepository;
import com.sergo.wic.service.*;
import com.sergo.wic.utils.Constants;
import com.sergo.wic.utils.RandomString;
import org.geotools.geometry.jts.JTS;
import org.geotools.referencing.GeodeticCalculator;
import org.locationtech.jts.geom.Coordinate;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.TransformException;
import org.postgis.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.postgis.PGgeometry;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ItemServiceImpl implements ItemService {

    private PointsRepository pointsRepository;
    private ItemRepository repository;
    private ItemConverter itemConverter;
    private UserItemService userItemService;
    private ShareService shareService;
    private CRSResolver crsResolver;

    public ItemServiceImpl(@Autowired ItemRepository repository,
                           @Autowired ItemConverter itemConverter,
                           @Autowired UserItemService userItemService,
                           @Autowired ShareService shareService,
                           @Autowired PointsRepository pointsRepository,
                           @Autowired CRSResolver crsResolver
                           ){
        this.repository = repository;
        this.pointsRepository = pointsRepository;
        this.userItemService = userItemService;
        this.itemConverter = itemConverter;
        this.shareService = shareService;
        this.crsResolver = crsResolver;
    }

    @Override
    @Transactional
    public Item save(Item item) {
        return repository.save(item);
    }

    @Override
    public Item save(Item item, Share share) {
        item.setShare(share);
        return repository.save(item);
    }

    @Override
    public Item findById(Long itemId) {
        Optional<Item> item = repository.findById(itemId);
        if (item.isPresent())
           return repository.findById(itemId).get();
        else return null;
    }

    @Override
    public boolean existsByCoordinates(double lon, double lat) {
        return repository.existsByLongitudeAndLatitude(lon,lat);
    }

    @Override
    public Item findByItemId(String itemId) {
        return repository.findByItemId(itemId);
    }

    @Transactional
    @Override
    public boolean save(Item item, User user, String shareId) {
        Optional<Share> share = shareService.findByShareId(shareId);
        Optional<UserItem> userItem;
        if (share.isPresent()){
        //    userItems = user ItemService.findByUserAndShare(user, share.get());
            userItem = share.get().getItems().stream()
                    .filter(item1 ->
                            item1.getUserItem() != null &&
                            item1.getUserItem().getUser().getLogin().equals(user.getLogin()))
                    .map(item2 -> new UserItem(user, item2))
                    .findFirst();
        }else
            return false;
        UserItem newUserItem;
        if (userItem.isPresent()){
            UserItem ui = userItem.get();
            if (user.getPickedItemsCount() != null){
                user.setPickedItemsCount(user.getPickedItemsCount() + 1);
            }else {
                user.setPickedItemsCount(1);
            }
            ui.setUser(user);
            item.setUserItem(ui);
            userItemService.save(userItem.get());
        }else{
            newUserItem = new UserItem(user);
            if (user.getPickedItemsCount() != null){
                user.setPickedItemsCount(user.getPickedItemsCount() + 1);
            }else {
                user.setPickedItemsCount(1);
            }
            newUserItem.setUser(user);
            item.setUserItem(newUserItem);
            userItemService.save(newUserItem);
        }
        item.setState(ItemState.PICKED);
        repository.saveAndFlush(item);
        return true;
    }


    @Override
    public List<Item> findAll() {
        return repository.findAll();
    }

    @Override
    public List<ItemDto> getShareItems(Share share) {
        Optional<List<Item>> items = repository.findAllByShare(share);
        List<Item> result;
        if (items.isPresent()){
            result = items.get().stream().filter((item) -> item.getUserItem() == null).collect(Collectors.toList());
            return itemConverter.convertAllItems(result);
        }
        else return null;
    }

    public List<ItemDto> convertAllItems(List<Item> list){
        return itemConverter.convertAllItems(list);
    }

    @Override
    public Integer getMaxCountItems(String projectPath,String layerName){
        return 20;
    }


    @Override
    public List<ItemDto> getRandomCoordinates(String table, int quantity, int seed) throws NullPointerException {
        List<Point> points = null;
        CoordinateReferenceSystem crs = null;
        try {
            points = pointsRepository.getRandomCoordinates(table,quantity,seed).stream()
                    .map(geometry -> new Point(
                            geometry.getGeometry().getPoint(0).x,
                            geometry.getGeometry().getPoint(0).y)
                    )
                    .collect(Collectors.toList());
            crs = crsResolver.resolve(points.get((int) (Math.random() * points.size())));
        } catch (NullPointerException e) {
            getRandomCoordinates(table, quantity, seed);
        }
        List<Point> acceptablePoints = getAcceptablePoints(Constants.ACCEPTABLE_RADIUS, crs, points, table);
        List<ItemDto> result = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            String itemId = RandomString.getAlphaNumericString(8);
            try {
                CoordinatesDto coordinates = new CoordinatesDto(
                        acceptablePoints.get(i).y,
                        acceptablePoints.get(i).x
                );
                ItemDto item = new ItemDto(coordinates,itemId);
                result.add(item);
            } catch (IndexOutOfBoundsException | NullPointerException e) {
                PGgeometry point = pointsRepository.getRandomPoint(table,seed);
                CoordinatesDto coordinates = new CoordinatesDto(
                        point.getGeometry().getPoint(0).y,
                        point.getGeometry().getPoint(0).x);
                ItemDto item = new ItemDto(coordinates,itemId);
                result.add(item);
            }
        }
        return result;
    }

    @Override
    public List<Item> addNewShareItems(List<ItemDto> items) {
        return null;
    }

    private List<Point> getAcceptablePoints(double radius, CoordinateReferenceSystem crs, List<Point> points, String table){
        int randomPointIndex = (int) (Math.random() * points.size());
        Point initialPoint = points.get(randomPointIndex);
        List<Point> acceptablePoints = points.stream()
                .filter(point -> isPointInAcceptableRadius(crs, radius, point,initialPoint))
                .collect(Collectors.toList());

        int necessaryPointsNumber = points.size() - acceptablePoints.size();
        List<Point> necessaryPoints = null;
        while(acceptablePoints.size() < points.size()){
            try {
                necessaryPoints = pointsRepository.getRandomCoordinates(table, necessaryPointsNumber, 30).stream()
                        .map(geometry -> new Point(
                                geometry.getGeometry().getPoint(0).x,
                                geometry.getGeometry().getPoint(0).y))
                        .filter(point -> isPointInAcceptableRadius(crs, radius, point,initialPoint))
                        .collect(Collectors.toList());
            } catch (NullPointerException e) { }
            if (necessaryPoints != null){
                acceptablePoints.addAll(necessaryPoints);
                necessaryPointsNumber = points.size() - acceptablePoints.size();
            }
        }
        return acceptablePoints;
    }

    private boolean isPointInAcceptableRadius(CoordinateReferenceSystem crs, double radius, Point point, Point initialPoint){
        GeodeticCalculator gc = new GeodeticCalculator(crs);
        Coordinate initial = new Coordinate(initialPoint.x, initialPoint.y);
        Coordinate current = new Coordinate(point.x, point.y);
        Map<String, Integer> map = new HashMap<>();
        Stream.of(map);
        try {
            gc.setStartingPosition(JTS.toDirectPosition(initial, crs));
            gc.setDestinationPosition(JTS.toDirectPosition(current, crs));
        } catch (TransformException e) {
            e.printStackTrace();
            return false;
        }
        return gc.getOrthodromicDistance() < radius;
    }
}
