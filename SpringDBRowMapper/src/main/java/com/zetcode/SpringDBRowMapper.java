package com.zetcode;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

public class SpringDBRowMapper {

    public static void main(String[] args) throws SQLException {

        SimpleDriverDataSource ds = new SimpleDriverDataSource();
        ds.setDriver(new com.mysql.jdbc.Driver());
        ds.setUrl("jdbc:mysql://localhost:3306/testdb?useSSL=false");
        ds.setUsername("testuser");
        ds.setPassword("test623");
        
        RowMapper rm = (RowMapper<Car>) (ResultSet result, int rowNum) -> {
            
            Car car = new Car();
            car.setId(result.getLong("Id"));
            car.setName(result.getString("Name"));
            car.setPrice(result.getInt("Price"));
            
            return car;
        };

        String sql = "SELECT * FROM Cars WHERE Id=?";
        Long id = 1L;
                
        JdbcTemplate jtm = new JdbcTemplate(ds);
        Car car = (Car) jtm.queryForObject(sql, new Object[] {id}, rm);
      
        System.out.printf("%d ", car.getId());
        System.out.printf("%s ", car.getName());
        System.out.printf("%d ", car.getPrice());
    }
}