package com.zetcode;

import java.util.Map;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import org.skife.jdbi.v2.Query;
import org.skife.jdbi.v2.util.StringColumnMapper;

public class JDBIEx2 {

    public static void main(String[] args) {

        Handle handle = null;
        DBI dbi = new DBI("jdbc:mysql://localhost:3306/testdb?useSSL=false",
                "testuser", "test623");

        try {

            handle = dbi.open();
            
            String sql = "SELECT Name FROM Cars WHERE Id = ?";
            
            Query<Map<String, Object>> q = handle.createQuery(sql);
            q.bind(0, 1);
            
            String carName = q.map(StringColumnMapper.INSTANCE).first();
            
            System.out.println(carName);

        } finally {
            if (handle != null) {
                handle.close();
            }
        }
    }
}
