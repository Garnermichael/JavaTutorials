package com.zetcode;

import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import org.skife.jdbi.v2.util.IntegerColumnMapper;

public class JDBIEx4 {

    public static void main(String[] args) {

        DBI dbi = new DBI("jdbc:mysql://localhost:3306/testdb?useSSL=false",
                "testuser", "test623");
        String sql = "SELECT Price FROM Cars WHERE Id = :id";
        int id = 3;

        Integer price = dbi.withHandle((Handle h) -> {

            return h.createQuery(sql)
                    .map(IntegerColumnMapper.WRAPPER)
                    .bind("id", id) 
                    .first(); 
        });
        
        System.out.println(price);
    }
}
