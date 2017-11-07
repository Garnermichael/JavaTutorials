package com.zetcode;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import org.skife.jdbi.v2.Query;
import org.skife.jdbi.v2.util.IntegerColumnMapper;

public class JDBIEx3 {

    public static MysqlDataSource getMySQLDataSource() throws 
            FileNotFoundException, IOException {
        
        Properties props = new Properties();
        FileInputStream fis = null;
        MysqlDataSource ds = null;

        fis = new FileInputStream("src/main/resources/db.properties");
        props.load(fis);
        
        ds = new MysqlDataSource();
        ds.setURL(props.getProperty("mysql.url"));
        ds.setUser(props.getProperty("mysql.username"));
        ds.setPassword(props.getProperty("mysql.password"));

        return ds;
    }

    public static void main(String[] args) throws IOException {

        Handle handle = null;
        MysqlDataSource ds = getMySQLDataSource();

        DBI dbi = new DBI(ds);

        try {

            handle = dbi.open();

            String sql = "SELECT Price FROM Cars WHERE Id = ?";

            Query<Map<String, Object>> q = handle.createQuery(sql);
            q.bind(0, 1);

            Integer price = q.map(IntegerColumnMapper.WRAPPER).first();

            System.out.println(price);

        } finally {
            if (handle != null) {
                handle.close();
            }
        }
    }
}
