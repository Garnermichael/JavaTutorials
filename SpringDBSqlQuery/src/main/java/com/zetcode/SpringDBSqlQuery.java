package com.zetcode;

import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

public class SpringDBSqlQuery {

    public static void main(String[] args) throws SQLException {

        SimpleDriverDataSource ds = new SimpleDriverDataSource();
        ds.setDriver(new com.mysql.jdbc.Driver());
        ds.setUrl("jdbc:mysql://localhost:3306/testdb?useSSL=false");
        ds.setUsername("testuser");
        ds.setPassword("test623");

        Long id = 1L;

        SingleCarQuery query = new SingleCarQuery(ds);
        
        List result =  query.execute(id);
        System.out.println(result.get(0));
    }
}
