package com.zetcode;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class SpringDBUpdateKey {

    public static void main(String[] args) throws SQLException {

        SimpleDriverDataSource ds = new SimpleDriverDataSource();
        ds.setDriver(new com.mysql.jdbc.Driver());
        ds.setUrl("jdbc:mysql://localhost:3306/testdb?useSSL=false");
        ds.setUsername("testuser");
        ds.setPassword("test623");

        String sql = "INSERT INTO Cars(Name, Price) VALUES (?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        JdbcTemplate jtm = new JdbcTemplate(ds);

        jtm.update(new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException  {

                PreparedStatement ps = con.prepareStatement(sql, new String[] {"id"});
                ps.setString(1, "Mazda");
                ps.setInt(2, 25600);
                return ps;
            }

        }, keyHolder);

        System.out.printf("Inserted row has ID: %d", keyHolder.getKey().intValue());

    }
}
