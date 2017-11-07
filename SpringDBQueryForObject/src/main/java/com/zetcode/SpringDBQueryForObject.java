package com.zetcode;

import java.sql.SQLException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

public class SpringDBQueryForObject {
    
    public static void main(String[] args) throws SQLException {
        
        SimpleDriverDataSource ds = new SimpleDriverDataSource();
        ds.setDriver(new com.mysql.jdbc.Driver());
        ds.setUrl("jdbc:mysql://localhost:3306/testdb?useSSL=false");
        ds.setUsername("testuser");
        ds.setPassword("test623");
        
        String sql = "SELECT Count(*) FROM Cars";

        JdbcTemplate jtm = new JdbcTemplate(ds);
        int numOfCars = jtm.queryForObject(sql, Integer.class);
        
        System.out.format("There are %d cars in the table", numOfCars);
    }    
}
