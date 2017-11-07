package com.zetcode;

import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

public class SpringDBBeanPropertyRowMapper2 {

    public static void main(String[] args) throws SQLException {

        SimpleDriverDataSource ds = new SimpleDriverDataSource();
        ds.setDriver(new com.mysql.jdbc.Driver());
        ds.setUrl("jdbc:mysql://localhost:3306/testdb?useSSL=false");
        ds.setUsername("testuser");
        ds.setPassword("test623");
        
        String sql = "SELECT * FROM Cars";
                
        JdbcTemplate jtm = new JdbcTemplate(ds);
        
        List<Car> cars = jtm.query(sql, new BeanPropertyRowMapper(Car.class));  
        
        cars.stream().forEach(System.out::println);
    }
}
