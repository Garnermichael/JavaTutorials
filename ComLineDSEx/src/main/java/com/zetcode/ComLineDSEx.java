package com.zetcode;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.Properties;

public class ComLineDSEx {

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

    public static void main(String[] args) throws IOException, SQLException {

        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        MysqlDataSource ds = getMySQLDataSource();

        try {

            con = ds.getConnection();
            pst = con.prepareStatement("SELECT VERSION()");
            rs = pst.executeQuery();

            if (rs.next()) {

                String version = rs.getString(1);
                System.out.println(version);
            }

        } finally {

            if (rs != null) {
                rs.close();
            }

            if (pst != null) {
                pst.close();
            }

            if (con != null) {
                con.close();
            }
        }
    }
}
