package com.zetcode;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcWriteImage {

    public static void main(String[] args) throws SQLException,
            IOException, FileNotFoundException {

        Connection con = null;
        PreparedStatement pst = null;
        FileInputStream fin = null;

        String cs = "jdbc:mysql://localhost:3306/testdb?useSSL=false";
        String user = "testuser";
        String password = "test623";

        try {

            File img = new File("src/main/resources/tree.png");
            fin = new FileInputStream(img);

            con = DriverManager.getConnection(cs, user, password);

            String sql = "INSERT INTO Images(Data) VALUES(?)";
            pst = con.prepareStatement(sql);
            pst.setBinaryStream(1, fin, (int) img.length());
            pst.executeUpdate();

        } finally {

            if (pst != null) {
                pst.close();
            }

            if (con != null) {
                con.close();
            }

            if (fin != null) {
                fin.close();
            }
        }
    }
}
