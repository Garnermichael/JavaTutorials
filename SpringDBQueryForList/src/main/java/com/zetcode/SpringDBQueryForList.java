package com.zetcode;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Driver;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

public class SpringDBQueryForList {

    public static void main(String[] args) throws IOException,
            ClassNotFoundException {

        String fileName = "src/main/resources/db.properties";
        Properties prop = new Properties();
        prop.load(new FileInputStream(fileName));

        SimpleDriverDataSource ds = new SimpleDriverDataSource();
        ds.setDriverClass(((Class<Driver>) Class.forName(prop.getProperty("jdbc.driver"))));
        ds.setUrl(prop.getProperty("jdbc.url"));
        ds.setUsername(prop.getProperty("jdbc.username"));
        ds.setPassword(prop.getProperty("jdbc.password"));

        String sql = "SELECT * FROM Cars";

        JdbcTemplate jtm = new JdbcTemplate(ds);
        List<Map<String, Object>> rows = jtm.queryForList(sql);

        rows.stream().forEach((row) -> {
            System.out.printf("%d ", row.get("Id"));
            System.out.printf("%s ", row.get("Name"));
            System.out.println(row.get("Price"));
        });
    }
}
