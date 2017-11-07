package com.zetcode;

import java.sql.SQLException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class SpringDBSqlRowSet {

    public static void main(String[] args) throws SQLException {

        SimpleDriverDataSource ds = new SimpleDriverDataSource();
        ds.setDriver(new com.mysql.jdbc.Driver());
        ds.setUrl("jdbc:mysql://localhost:3306/testdb?useSSL=false");
        ds.setUsername("testuser");
        ds.setPassword("test623");

        String sql = "SELECT * FROM Cars WHERE Id=?";
        Long id = 1L;

        JdbcTemplate jtm = new JdbcTemplate(ds);

        SqlRowSet srs = jtm.queryForRowSet(sql, id);

        Car car = new Car();

        if (srs.next()) {
            car.setId(srs.getLong(1));
            car.setName(srs.getString(2));
            car.setPrice(srs.getInt(3));
        }

        System.out.printf("%d ", car.getId());
        System.out.printf("%s ", car.getName());
        System.out.printf("%d ", car.getPrice());
    }
}
