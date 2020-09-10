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

    public boolean checkPlaceByPhoneAndPlaceName(String phone1, String placeID, String countryCode) {

        StringBuffer sb = new StringBuffer(phone1);
        sb.insert(0," (");
        sb.insert(5,")");
        String phone = new String(sb);
        FindPlaceFromTextRequest req;
        FindPlaceFromTextRequest req2 = null;
            FindPlaceFromText place = null;
        if (countryCode.equals("RU")){
             req = PlacesApi.findPlaceFromText(
                    geoApiContext,
                    "+7"+phone,
                    FindPlaceFromTextRequest.InputType.PHONE_NUMBER);
            try {
                place = req.await();
            }catch (ApiException | InterruptedException | IOException e){
                req2 = PlacesApi.findPlaceFromText(
                        geoApiContext,
                        "8"+phone,
                        FindPlaceFromTextRequest.InputType.PHONE_NUMBER);

                req2.fields(FindPlaceFromTextRequest.FieldMask.PLACE_ID,
                        FindPlaceFromTextRequest.FieldMask.NAME);
                try {
                    place = req2.await();
                }catch (ApiException | InterruptedException | IOException ex){
                    ex.printStackTrace();
                    return false;
                }
            }


            PlacesSearchResult[] candidates = place.candidates;
            for(PlacesSearchResult res : candidates){
                System.out.println(res.placeId + " -id, name: " + res.name);
                if (res.placeId.equals(placeID)) {
                    System.out.println(res.placeId + " equals " + placeID);
                    PlaceDetailsRequest placeReq = PlacesApi.placeDetails(geoApiContext,res.placeId);
                    try{
                        PlaceDetails placeDetails = placeReq.await();
                        System.out.println(placeDetails.internationalPhoneNumber + " placeDetails.internationalPhoneNumber");
                        System.out.println(placeDetails.formattedPhoneNumber + " placeDetails.formattedPhoneNumber");
                        String formattedPhoneClient = phone.replaceAll("[\\s-()\\.]","");
                        String formattedPhoneGoogle = placeDetails.internationalPhoneNumber.replaceAll("(\\+7)?[\\s-()\\.]","");
                        System.out.println(phone + " -requested phone number, placeDetails phone number: " + placeDetails.internationalPhoneNumber);
                        System.out.println(formattedPhoneClient + " -formattedPhoneClient, formattedPhoneGoogle: " + formattedPhoneGoogle);
                        if (formattedPhoneClient.equals(formattedPhoneGoogle)){
                            System.out.println("phone checked!");
                            return true;
                        }
                    }
                    catch (ApiException | InterruptedException | IOException e){
                        e.printStackTrace();
                        return false;
                    }
                }
            }
            return false;
        }


//
//
//        try {
//            place = req.await();
//        }catch (ApiException | InterruptedException | IOException e){
//            e.printStackTrace();
//            return false;
//        }
//
//        PlacesSearchResult[] candidates = place.candidates;
//        for(PlacesSearchResult res : candidates){
//            System.out.println(res.placeId + " -id, name: " + res.name);
//            if (res.placeId.equals(placeID)) {
//                System.out.println(res.placeId + " equals " + placeID);
//                PlaceDetailsRequest placeReq = PlacesApi.placeDetails(geoApiContext,res.placeId);
//                    try{
//                        PlaceDetails placeDetails = placeReq.await();
//                        System.out.println(placeDetails.internationalPhoneNumber + " placeDetails.internationalPhoneNumber");
//                        System.out.println(placeDetails.formattedPhoneNumber + " placeDetails.formattedPhoneNumber");
//                        String formattedPhoneClient = phone.replaceAll("[\\s-()\\.]","");
//                        String formattedPhoneGoogle = placeDetails.internationalPhoneNumber.replaceAll("[\\s-()\\.]","");
//                        System.out.println(phone + " -requested phone number, placeDetails phone number: " + placeDetails.internationalPhoneNumber);
//                        System.out.println(formattedPhoneClient + " -formattedPhoneClient, formattedPhoneGoogle: " + formattedPhoneGoogle);
//                        if (formattedPhoneClient.equals(formattedPhoneGoogle)){
//                            System.out.println("phone checked!");
//                            return true;
//                        }
//                    }
//                    catch (ApiException | InterruptedException | IOException e){
//                        e.printStackTrace();
//                        return false;
//                    }
//            }
//        }
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