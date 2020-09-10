package com.sergo.wic.repository;

import org.postgis.PGgeometry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RandomPointsRepositoryImpl implements RandomPointsRepository {


        private JdbcTemplate jdbcTemplate;

        public RandomPointsRepositoryImpl(@Autowired JdbcTemplate jdbcTemplate){
            this.jdbcTemplate = jdbcTemplate;
        }

    @Override
    public List<PGgeometry> getRandomCoordinates(String table, int quantity, int seed){
        String rowsQuery = "select count(*) FROM " + table + " ;";
        Integer rawsCount = jdbcTemplate.queryForObject(rowsQuery,Integer.class);
        int[] randomNums = new int[quantity];
        for (int i = 0; i < randomNums.length; i++) {
            randomNums[i] = (int) Math.round(rawsCount * Math.random());
        }
//        String query = "SELECT ST_GeomFromWKB"
//                + "(ST_GeneratePoints(the_geom,"
//                + "? ,"
//                + seed + "))"
//                + " FROM " + table
//                + " WHERE gid= ? ;";
        StringBuilder sb = new StringBuilder("SELECT ST_GeomFromWKB (ST_GeneratePoints(the_geom , 1 , ?)) FROM " + table + " WHERE gid IN ( ? ");
        while (quantity > 1){
            sb.append(", ? ");
            quantity--;
        }
        sb.append(");");
        String query = sb.toString();
        List<PGgeometry> points = new ArrayList<>();
        return jdbcTemplate.execute(query,(PreparedStatementCallback<List<PGgeometry>>) ps -> {
            ResultSet rs;
            ps.setInt(1,seed);
            for (int i = 2 , j = 0; i < randomNums.length + 2; i++ , j++) {
                ps.setInt(i,randomNums[j]);
            }
            rs = ps.executeQuery();
            while (rs.next()){
                points.add((PGgeometry) rs.getObject(1));
            }
            ps.close();
            return points;
        });
    }

}
