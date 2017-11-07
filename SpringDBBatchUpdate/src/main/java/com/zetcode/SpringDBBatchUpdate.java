package com.zetcode;

import com.google.common.collect.Lists;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

public class SpringDBBatchUpdate {

    public static void main(String[] args) throws SQLException {

        SimpleDriverDataSource ds = new SimpleDriverDataSource();
        ds.setDriver(new com.mysql.jdbc.Driver());
        ds.setUrl("jdbc:mysql://localhost:3306/testdb?useSSL=false");
        ds.setUsername("testuser");
        ds.setPassword("test623");

        List<Car> newCars = Lists.newArrayList(
                
                new Car("Toyota", 26800),
                new Car("Oldsmobile", 26800),
                new Car("Kia", 38900),
                new Car("Cadillac", 45600)
        );

        String sql = "INSERT INTO Cars(Name, Price) VALUES (?, ?)";

        JdbcTemplate jtm = new JdbcTemplate(ds);
        jtm.batchUpdate(sql, new BatchPreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps, int i) 
                    throws SQLException {
                
                Car car = newCars.get(i);
                ps.setString(1, car.getName());
                ps.setInt(2, car.getPrice());
            }

            @Override
            public int getBatchSize() {
                return newCars.size();
            }
        });
    }
}
