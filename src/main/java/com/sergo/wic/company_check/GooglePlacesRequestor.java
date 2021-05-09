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
                if (res.placeId.equals(placeID)) {
                    PlaceDetailsRequest placeReq = PlacesApi.placeDetails(geoApiContext,res.placeId);
                    try{
                        PlaceDetails placeDetails = placeReq.await();
                        String formattedPhoneClient = phone.replaceAll("[\\s-()\\.]","");
                        String formattedPhoneGoogle = placeDetails.internationalPhoneNumber.replaceAll("(\\+7)?[\\s-()\\.]","");
                        if (formattedPhoneClient.equals(formattedPhoneGoogle)){
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
        return false;
    }
}
