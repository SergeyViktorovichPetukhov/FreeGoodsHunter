package com.sergo.wic.utils;


import org.geotools.geometry.jts.JTS;
import org.geotools.referencing.GeodeticCalculator;
import org.locationtech.jts.geom.Coordinate;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.TransformException;

import java.util.List;

public class DistanceCalculator {

    public static double calculateDistance(CoordinateReferenceSystem crs, Coordinate start, Coordinate end)  {

        GeodeticCalculator gc = new GeodeticCalculator(crs);

        try {
            gc.setStartingPosition(JTS.toDirectPosition(start, crs));
            gc.setDestinationPosition(JTS.toDirectPosition(end, crs));
        } catch (TransformException e) {
            e.printStackTrace();
        }
        double orthodromicDist = gc.getOrthodromicDistance();

        return orthodromicDist < 2200.00 ? orthodromicDist / 1.5 : orthodromicDist;
    }

    public static double nearestDistance(CoordinateReferenceSystem crs, Coordinate point, List<Coordinate> items) {

       Coordinate nearestItem = items.stream()
                .reduce((coordinate1, coordinate2) ->  {
            if (calculateDistance(crs, point, coordinate1) > calculateDistance(crs, point, coordinate2)) {
                return coordinate2;
            }
            return coordinate1;
        }).get();

       return calculateDistance(crs, point, nearestItem);
    }
}
