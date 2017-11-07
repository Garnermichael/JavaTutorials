package com.zetcode;

import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;

public class JDBIEx5 {

    public static void main(String[] args) {

        DBI dbi = new DBI("jdbc:mysql://localhost:3306/testdb?useSSL=false",
                "testuser", "test623");
        String sql = "SELECT * FROM Cars WHERE Id = :id";
        int id = 3;

        Car car = dbi.withHandle((Handle h) -> {

            return h.createQuery(sql)
                    .map(new CarMapper())
                    .bind("id", id) 
                    .first(); 
        });
                
        System.out.println(car);
    }
}