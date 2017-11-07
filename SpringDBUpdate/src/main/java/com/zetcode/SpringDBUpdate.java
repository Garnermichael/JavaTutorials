package com.zetcode;

import java.sql.SQLException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

public class SpringDBUpdate {
    
    public static void main(String[] args) throws SQLException {
        
        SimpleDriverDataSource ds = new SimpleDriverDataSource();
        ds.setDriver(new com.mysql.jdbc.Driver());
        ds.setUrl("jdbc:mysql://localhost:3306/testdb?useSSL=false");
        ds.setUsername("testuser");
        ds.setPassword("test623");
        
        String sql = "INSERT INTO Cars(Name, Price) VALUES (?, ?)";
        Object[] params = new Object[] { "Renault", 18000 };

        JdbcTemplate jtm = new JdbcTemplate(ds);
        jtm.update(sql, params);
    }    
}
