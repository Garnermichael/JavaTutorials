package com.zetcode;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

public class SpringDBSimpleInsert {

    public static void main(String[] args) throws SQLException {

        SimpleDriverDataSource ds = new SimpleDriverDataSource();
        ds.setDriver(new com.mysql.jdbc.Driver());
        ds.setUrl("jdbc:mysql://localhost:3306/testdb?useSSL=false");
        ds.setUsername("testuser");
        ds.setPassword("test623");

        JdbcTemplate jtm = new JdbcTemplate(ds);

        SimpleJdbcInsert jins = new SimpleJdbcInsert(jtm)
                .withTableName("Cars").usingColumns("Name", "Price")
                .usingGeneratedKeyColumns("Id");

        Map<String, Object> params = new HashMap<>();
        
        params.put("Name", "Seat");
        params.put("Price", 28900);
        
        Number key = jins.executeAndReturnKey(params);

        System.out.printf("Inserted row has ID: %d", key.intValue());

    }
}
