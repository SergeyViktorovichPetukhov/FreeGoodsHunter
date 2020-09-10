package com.sergo.wic.repository;

import org.postgis.PGgeometry;
import org.postgresql.util.PGobject;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface RandomPointsRepository {
   List<PGgeometry> getRandomCoordinates(String table, int quantity, int seed);
   PGgeometry getRandomPoint(String table, int seed);
}
