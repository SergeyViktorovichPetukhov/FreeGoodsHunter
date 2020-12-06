package com.sergo.wic.coordinate_referense_system;

import com.sergo.wic.dto.GeoLocationData;
import org.geotools.referencing.CRS;
import org.geotools.referencing.crs.DefaultProjectedCRS;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.postgis.Point;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CRSResolver {

    public CoordinateReferenceSystem resolve(Point point){
        CoordinateReferenceSystem crs = null;
        try {
            crs = CRS.decode("AUTO2:42001," + point.x  + "," + point.y);
        } catch (FactoryException e) {
            e.printStackTrace();
        }
        return crs;
    }
}
