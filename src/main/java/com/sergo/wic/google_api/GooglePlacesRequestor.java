package com.sergo.wic.google_api;

import com.google.maps.FindPlaceFromTextRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.PendingResult;
import com.google.maps.PlacesApi;
import com.google.maps.model.FindPlaceFromText;
import com.google.maps.model.PlacesSearchResult;
import org.springframework.stereotype.Component;
import se.walkercrou.places.AddressComponent;
import se.walkercrou.places.GooglePlaces;
import se.walkercrou.places.Place;

import java.util.Arrays;
import java.util.List;

@Component
public class GooglePlacesRequestor {

    private static final String API_KEY = "AIzaSyDGzXLWA6Lp69ELTKPwwVMtY9GcSgnWAIQ";

    public void test(){
//        GeoApiContext context = new GeoApiContext.Builder()
//                .apiKey(API_KEY)
//                .build();
//        FindPlaceFromTextRequest request = PlacesApi.findPlaceFromText(context,"+74959805566", FindPlaceFromTextRequest.InputType.PHONE_NUMBER);
        GooglePlaces client = new GooglePlaces(API_KEY);
        List<Place> places = client.getPlacesByQuery("Тест (software development)", GooglePlaces.MAXIMUM_RESULTS);

        for (Place place : places){
            Place place1 = client.getPlaceById(place.getPlaceId());
            List<AddressComponent> list = place1.getAddressComponents();
            for(AddressComponent address : list){
                System.out.println(address.getLongName());
            }
        }

    }
}
