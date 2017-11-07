package com.zetcode;

import java.sql.SQLException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

public class SpringDBNamedParam {

    public static void main(String[] args) throws SQLException {

        SimpleDriverDataSource ds = new SimpleDriverDataSource();
        ds.setDriver(new com.mysql.jdbc.Driver());
        ds.setUrl("jdbc:mysql://localhost:3306/testdb?useSSL=false");
        ds.setUsername("testuser");
        ds.setPassword("test623");

        String carName = "Skoda";
        String sql = "SELECT Price FROM Cars WHERE Name = :name";
        SqlParameterSource npar = new MapSqlParameterSource("name", carName);

        NamedParameterJdbcTemplate ntm = new NamedParameterJdbcTemplate(ds);
        int carPrice = ntm.queryForObject(sql, npar, Integer.class);
        
        System.out.printf("The price of %s is %d", carName, carPrice);
    }
}
