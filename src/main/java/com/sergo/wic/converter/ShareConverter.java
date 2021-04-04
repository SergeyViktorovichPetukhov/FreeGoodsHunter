package com.sergo.wic.converter;

import com.sergo.wic.coordinate_referense_system.CRSResolver;
import com.sergo.wic.dto.*;
import com.sergo.wic.dto.Response.*;
import com.sergo.wic.entities.*;
import com.sergo.wic.entities.enums.ShareCellType;
import com.sergo.wic.utils.DistanceCalculator;
import com.sergo.wic.utils.RandomString;
import org.apache.logging.log4j.util.PropertySource;
import org.locationtech.jts.geom.Coordinate;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Component
public class ShareConverter {

    private static final String PRODUCT_PHOTO_PATH = "product.photo.path";

    private ModelMapper modelMapper;
    private TypeMap<ItemDto, Item> typeMapItem;
    private TypeMap<ShareDto, Share> typeMapShare;
    private ItemConverter itemConverter;
    private CoordinateReferenceSystem crs = CRSResolver.resolveFromCRS("EPSG:4326");

    private Comparator<CellModelDto> comparatorByType = (o1, o2) -> o1.getCellType().getValue() > o2.getCellType().getValue() ? 1 : 0;

    private Comparator<CellModelDto> comparatorByVerificated = (o1, o2) -> {
        if ((o1.isVerificated() & !o2.isVerificated())) {
            return 1;
        }
        if (!o1.isVerificated() & o2.isVerificated()) {
            return -1;
        }
        else return 0;
    };

    private Comparator<CellModelDto> comparatorByPrice = (o1, o2) -> o1.getProductPrice() > o2.getProductPrice() ? 1 : 0;

    public ShareConverter(@Autowired ModelMapper modelMapper,
                          @Autowired TypeMap<ItemDto, Item> typeMapItem,
                          @Autowired TypeMap<ShareDto, Share> typeMapShare,
                          @Autowired ItemConverter itemConverter){
        this.typeMapItem = typeMapItem;
        this.typeMapShare = typeMapShare;
        this.modelMapper = modelMapper;
        this.itemConverter = itemConverter;
    }

    public Share convertToModel(ShareDto source) {
        final Share share = new Share();
        modelMapper.map(source, share);
        return share;
    }

    public Share convertToModel(CreateShareDto source, Company company) {
        Share share = new Share();
        share.setLogin(company.getLogin());
        share.setCompany(company);
        share.setShareId(company.getLogin() + " " + RandomString.getAlphaNumericString(6));
        share.setItems(itemConverter.convertAllDtos(source.getItems(), share));
        share.setAllItemsCount(source.getItems().size());
        share.setDate(new Timestamp(System.currentTimeMillis()));
        modelMapper.typeMap(CreateShareDto.class, Share.class)
                .addMappings(mapper -> mapper.map(CreateShareDto::getItems,Share::setItems));
        modelMapper.map(source, share);
        return share;
    }

    public Share convertToModel(UploadShareDto source) {
        Share share = new Share();
        modelMapper.map(source,share);
        return share;
    }

    public ShareDto convertToDto(Share source) {
        ShareDto shareDto = new ShareDto();
        modelMapper.map(source, shareDto);
        shareDto.setPhotoProductUrl(Objects.nonNull(
                source.getProductPhotoUrl()) ? source.getProductPhotoUrl() : null
        );
        return shareDto;
    }

    public GetShareResponse convertToShareResponse(Share source){
        final GetShareResponse response = new GetShareResponse();
        modelMapper.map(source, response);
            List<Item> items = source.getItems();
            List<PickedItemDto> pickedItemDtos = Arrays.asList(modelMapper.map(items, PickedItemDto[].class));
//                List<User> users = source.getUsers();
//                List<UserDto> userDtoList = Arrays.asList(modelMapper.map(users, UserDto[].class));
            response.setPoints(pickedItemDtos);
//            response.setUsersWithShareItems(userDtoList);
        return response;
    }

    public AllSharesResponse convertToAllShareResponse(List<Share> allShares){
        AllSharesResponse response = new AllSharesResponse();
        List<GetShareResponse> shares = new ArrayList<>();
        IntStream.range(0,allShares.size()).forEach(i ->
                shares.add(convertToShareResponse(allShares.get(i))));
        response.setShares(shares);
        return response;
    }

    public ShareInfoResponse convertToShareInfoResponse(Share share){
        Company company = share.getCompany();
        CompanyDto companyDto = new CompanyDto();
        ProductDto productDto = new ProductDto();
        modelMapper.map(company,companyDto);
        companyDto.setLogin(null);
        productDto.setProductName(share.getProductName());
        productDto.setDescription(share.getProductDescription());
        productDto.setLogo(share.getProductPhotoUrl());
        productDto.setPrice(share.getProductPrice());
        productDto.setWebSite(share.getProductWebsite());
        return new ShareInfoResponse(companyDto,productDto);
    }

    public ShareUserItemsResponse convertToShareItemsResponse(List<Item> shareItems){

        List<UserItem> userItems = shareItems.stream()
                .filter(item -> item.getUserItem() != null)
                .map(item -> new UserItem(item.getUserItem()))
                .collect(Collectors.toList());

        List<UserDto> users = userItems.stream()
                .map(userItem -> {
                    UserDto dto = new UserDto();
                    User user = userItem.getUser();
                    dto.setLogin(user.getLogin());
                    dto.setPickedItemsCount(user.getPickedItemsCount());
                    dto.setPhotoUrl(user.getUserProfile().getPhotoUrl());
                    return dto;
                })
                .collect(Collectors.toList());

        return new ShareUserItemsResponse(users);
    }

    public ShareCellTypesResponse cellTypesResponse(List<Share> shares, List<ShareCellType> cellTypes, CoordinatesDto userCoordinates){

        List<CellModelDto> started = new ArrayList<>();
        List<CellModelDto> chosen = new ArrayList<>();
        List<CellModelDto> activeVerified = new ArrayList<>();
        List<CellModelDto> activeNotVerified = new ArrayList<>();
        List<CellModelDto> previewVerified = new ArrayList<>();
        List<CellModelDto> previewNotVerified = new ArrayList<>();
        List<CellModelDto> finishedVerified = new ArrayList<>();
        List<CellModelDto> finishedNotVerified = new ArrayList<>();

        List<List<CellModelDto>> cellModelsList = new LinkedList<>();
        cellModelsList.add(started);
        cellModelsList.add(chosen);
        cellModelsList.add(activeVerified);
        cellModelsList.add(activeNotVerified);
        cellModelsList.add(previewVerified);
        cellModelsList.add(previewNotVerified);
        cellModelsList.add(finishedVerified);
        cellModelsList.add(finishedNotVerified);

        List<CellModelDto> cellModels = IntStream.range(0, shares.size())
                .mapToObj( i -> {
                    Share share = shares.get(i);
                    CellModelDto dto = new CellModelDto();
                    dto.setCellType(cellTypes.get(i));
                    dto.setNumItemsToWin(itemsToWin(share.getAllItemsCount(), share.getPickedItemsCount()));
                    dto.setVerificated(share.getIsVerificated());
                    Coordinate userPosition = new Coordinate(userCoordinates.getLatitude(), userCoordinates.getLongitude());
                    if (share.getItems() != null) {
                        List<Coordinate> shareItems = share.getItems().stream()
                                .map(item -> new Coordinate(item.getLatitude(), item.getLongitude()))
                                .collect(Collectors.toList());
                        dto.setDistanceToNearestItem((int)DistanceCalculator.nearestDistance(crs, userPosition, shareItems));
                    }
                    modelMapper.map(share, dto);
                    return dto;
                })
                .collect(Collectors.toList());
        System.out.println("\n\n\n cellModels.size()                       " + cellModels.size() + "\n\n\n");
        cellModels.forEach( cellModel -> {
            switch (cellModel.getCellType()) {
                case STARTED: {
                    started.add(cellModel);
                    break;
                }
                case CHOSEN: {
                    chosen.add(cellModel);
                    break;
                }
                case ACTIVE: {
                    boolean verified = cellModel.isVerificated() ? (activeVerified.add(cellModel)) : (activeNotVerified.add(cellModel));
                    break;
                }
                case PREVIEW: {
                    boolean verified = cellModel.isVerificated() ? (previewVerified.add(cellModel)) : (previewNotVerified.add(cellModel));
                    break;
                }
                case FINISHED: {
                    boolean verified = cellModel.isVerificated() ? (finishedVerified.add(cellModel)) : (finishedNotVerified.add(cellModel));
                    break;
                }
            }
        });

        List<CellModelDto> result = new LinkedList<>();
        cellModelsList.forEach(list -> {
            list.sort(Comparator.comparing(CellModelDto::getProductPrice));
            list.forEach(result::add);
        });
        return new ShareCellTypesResponse(result);
    }

    private int itemsToWin(int allItemsCount, int pickedItemsCount){
        return pickedItemsCount > allItemsCount / 2 ? (allItemsCount / 2) + 1 : allItemsCount - pickedItemsCount;
    }
}
