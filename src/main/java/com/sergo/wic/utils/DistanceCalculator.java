package com.sergo.wic.utils;


import org.geotools.geometry.jts.JTS;
import org.geotools.referencing.GeodeticCalculator;
import org.locationtech.jts.geom.Coordinate;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.TransformException;

import java.util.Arrays;
import java.util.List;

public class DistanceCalculator {

    public static double calculateDistance(CoordinateReferenceSystem crs, Coordinate start, Coordinate end)  {

        GeodeticCalculator gc = new GeodeticCalculator(crs);

        try {
            gc.setStartingPosition(JTS.toDirectPosition(start, crs));
            gc.setDestinationPosition(JTS.toDirectPosition(end, crs));
        } catch (TransformException | NullPointerException e) {
            return 0.0;
        }
        double orthodromicDist = gc.getOrthodromicDistance();
        return orthodromicDist < 2200.00 ? orthodromicDist / 1.5 : orthodromicDist;
    }

    public static String nearestDistance(CoordinateReferenceSystem crs, Coordinate point, List<Coordinate> items) {

       Coordinate nearestItem = items.stream()
                .reduce((coordinate1, coordinate2) ->  {
            if (calculateDistance(crs, point, coordinate1) > calculateDistance(crs, point, coordinate2)) {
                return coordinate2;
            }
            return coordinate1;
        }).orElse(null);
       return convertDistanceToString(calculateDistance(crs, point, nearestItem));
    }

    private static String convertDistanceToString(double distance) {
        if (distance < 1000) {
            return (int)distance + " m";
        }
        String km = String.valueOf(((int)(distance / 1000)));
        String m = String.valueOf((int)distance % 1000);
        return km + " km, " + m + " m";
    }
}
