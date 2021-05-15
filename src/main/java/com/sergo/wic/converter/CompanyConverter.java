package com.sergo.wic.converter;

import com.sergo.wic.coordinate_referense_system.CRSResolver;
import com.sergo.wic.dto.AddressDto;
import com.sergo.wic.dto.ContactDto;
import com.sergo.wic.dto.CoordinatesDto;
import com.sergo.wic.dto.Response.CompanyResponse;
import com.sergo.wic.dto.ShareDto;
import com.sergo.wic.entities.Company;
import com.sergo.wic.entities.enums.ShareCellType;
import com.sergo.wic.entities.enums.ShareState;
import com.sergo.wic.utils.DateUtils;
import com.sergo.wic.utils.DistanceCalculator;
import org.locationtech.jts.geom.Coordinate;
import org.modelmapper.ModelMapper;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CompanyConverter {

    @Autowired
    ModelMapper modelMapper;

    private CoordinateReferenceSystem crs = CRSResolver.resolveFromCRS("EPSG:4326");

    public CompanyResponse companyResponse(Company company, CoordinatesDto coordinates) {
        List<ContactDto> contacts = company.getContacts().stream()
                .map(contact -> {
                   return new ContactDto(contact.getTypeContact(), contact.getContact());
                })
                .collect(Collectors.toList());
        List<AddressDto> addresses = company.getAddresses().stream()
                .map(address -> {
                    return new AddressDto(address.getCountry() ,address.getRegion(), address.getCity(), address.getAddressLine());
                })
                .collect(Collectors.toList());
        List<ShareDto> shares = company.getShares().stream()
                .map(share -> {
                    ShareDto shareDto = new ShareDto();
                    shareDto.setShareId(share.getShareId());
                    shareDto.setPhotoProductUrl(share.getProductPhotoUrl());

                    Integer cellType =
                             share.getStatus() == ShareState.ACTIVE ? (ShareCellType.ACTIVE.getValue()) :
                            (share.getStatus() == ShareState.PREVIEW ? ShareCellType.PREVIEW.getValue() :
                            (share.getStatus() == ShareState.CREATED ? ShareCellType.PREVIEW.getValue() :
                                    ShareCellType.FINISHED.getValue()));

                    shareDto.setCellType(cellType);
                    shareDto.setCompanyId(company.getLogin());
                    shareDto.setPromoColor(share.getColor());
                    shareDto.setNumItemsToWin(share.getAllItemsCount());
                    shareDto.setProductName(share.getProductName());
                    shareDto.setDate(DateUtils.convertTimestampToStringDate("yyyy-MM-dd", share.getDate()));
                    shareDto.setProductPrice(share.getProductPrice() + " ₽");
                    shareDto.setPickedItemsCount(share.getPickedItemsCount());
                    shareDto.setAllItemsCount(share.getAllItemsCount());
                    Coordinate userPoint = new Coordinate(coordinates.getLatitude(), coordinates.getLongitude());
                    List<Coordinate> items = share.getItems().stream()
                            .map(item -> new Coordinate(item.getLatitude(), item.getLongitude()))
                            .collect(Collectors.toList());
                    String distanceToNearestItem = DistanceCalculator.convertDistanceToString(DistanceCalculator.nearestDistance(crs, userPoint, items));
                    shareDto.setDistanceToNearestItem(distanceToNearestItem);
                    return shareDto;

                }).collect(Collectors.toList());
        CompanyResponse companyResponse = new CompanyResponse();
        companyResponse.setLabel(company.getLogoUrl());
        companyResponse.setNameCompany(company.getName());
        companyResponse.setShortDescription(company.getInfo());
        companyResponse.setIsCompanyVerificated(company.getIsVerificated());
        companyResponse.setContacts(contacts);
        companyResponse.setShares(shares);
        companyResponse.setShops(addresses);
        return companyResponse;
    }
}
