package com.zetcode;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

public class CarMapper implements ResultSetMapper<Car> {
   
    @Override
    public Car map(int idx, ResultSet rs, StatementContext ctx) 
            throws SQLException {
        
        return new Car(rs.getLong("id"), rs.getString("name"), 
                rs.getInt("price"));
    }
}
