package com.zetcode;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

public class SpringDBRSExtractor {

    public static void main(String[] args) throws SQLException {

        SimpleDriverDataSource ds = new SimpleDriverDataSource();
        ds.setDriver(new com.mysql.jdbc.Driver());
        ds.setUrl("jdbc:mysql://localhost:3306/testdb?useSSL=false");
        ds.setUsername("testuser");
        ds.setPassword("test623");

        String sql = "SELECT * FROM Cars WHERE Id=?";
        Long id = 1L;

        JdbcTemplate jtm = new JdbcTemplate(ds);
        
        ResultSetExtractor rse = new ResultSetExtractor<Car>() {
            @Override
            public Car extractData(ResultSet rs) throws SQLException,
                    DataAccessException {
                
                Car car = new Car();
                
                if (rs.next()) {
                    
                    car.setId(rs.getLong(1));
                    car.setName(rs.getString(2));
                    car.setPrice(rs.getInt(3));
                }
                
                return car;
            }
        };

        Car car = (Car) jtm.query(sql, rse, id);

        System.out.printf("%d ", car.getId());
        System.out.printf("%s ", car.getName());
        System.out.printf("%d ", car.getPrice());
    }
}
