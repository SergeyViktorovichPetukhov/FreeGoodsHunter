package com.sergo.wic.company_check;

import com.google.maps.*;
import com.google.maps.errors.ApiException;
import com.google.maps.model.FindPlaceFromText;
import com.google.maps.model.PlaceDetails;
import com.google.maps.model.PlacesSearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GooglePlacesRequestor {

    @Autowired
    private GeoApiContext geoApiContext;

    public boolean checkPlaceByPhoneAndPlaceName(String phone, String placeName) {
        FindPlaceFromTextRequest req = PlacesApi.findPlaceFromText(
                                       geoApiContext,
                                       phone,
                                       FindPlaceFromTextRequest.InputType.PHONE_NUMBER);

                                 req.fields(FindPlaceFromTextRequest.FieldMask.PLACE_ID,
                                            FindPlaceFromTextRequest.FieldMask.NAME);

            FindPlaceFromText place = null;
        try {
            place = req.await();
        }catch (ApiException | InterruptedException | IOException e){
            e.printStackTrace();
            System.out.println(e.getMessage());
            return false;
        }

        PlacesSearchResult[] candidates = place.candidates;
        for(PlacesSearchResult res : candidates){
            System.out.println(res.placeId + " -id, name: " + res.name);
        if (res.name.equals(placeName)) {
            PlaceDetailsRequest placeReq = PlacesApi.placeDetails(geoApiContext,res.placeId);
                try{
                    PlaceDetails placeDetails = placeReq.await();
                    System.out.println(placeDetails.internationalPhoneNumber + " " + placeDetails.formattedPhoneNumber);
                    String regex = "{+7,8}";
                    if (placeDetails.internationalPhoneNumber.equals(phone)){
                        System.out.println("phone checked!");
                        return true;
                    }
                }catch (ApiException | InterruptedException | IOException e){
                    e.printStackTrace();
                    System.out.println(e.getMessage());
                }
            }
        }
        return false;
    }
}


//    private static final String API_KEY = "AIzaSyDGzXLWA6Lp69ELTKPwwVMtY9GcSgnWAIQ";
//
//    private GooglePlaces client = new GooglePlaces(API_KEY);
//
//    public boolean checkPhone(String phone){
//        List<Place> places = null;
//        try {
//            places = client.getPlacesByQuery(phone, GooglePlaces.MAXIMUM_RESULTS);
//        }catch (NoResultsFoundException e){
//            System.out.println("no such place in google places");
//            return false;
//        }
//        for (Place place : places){
//            //    Place place1 = client.getPlaceById(place.getPlaceId());
//            System.out.println(place.getPlaceId() + " placeId");
//            String phoneNumber = place.getPhoneNumber();
//            System.out.println(phoneNumber);
//            if (phoneNumber.equals(phone))
//                return true;
//        }
//        return false;
//    }


//
//
//        req.setCallback(new PendingResult.Callback<FindPlaceFromText>() {
//@Override
//public void onResult(FindPlaceFromText findPlaceFromText) {
//        PlacesSearchResult[] candidates = findPlaceFromText.candidates;
//        for(PlacesSearchResult res : candidates){
//        if (res.name.equals(placeName)) {
//        PlaceDetailsRequest placeReq = PlacesApi.placeDetails(geoApiContext,res.placeId);
//        placeReq.fields(PlaceDetailsRequest.FieldMask.INTERNATIONAL_PHONE_NUMBER);
//        placeReq.region("RU");
//
//        placeReq.setCallback();
//        }
//        }
//        }
//
//@Override
//public void onFailure(Throwable throwable) {
//
//        }
//        });