package com.zetcode;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcExportCSV {

    public static void main(String[] args) throws SQLException {

        Connection con = null;
        PreparedStatement pst = null;

        String url = "jdbc:mysql://localhost:3306/testdb?useSSL=false";
        String user = "testuser";
        String password = "test623";

        try {

            con = DriverManager.getConnection(url, user, password);
            String query = "SELECT Name, Title INTO OUTFILE "
                    + "'/var/lib/mysql-files/authors_books.csv' "
                    + "FIELDS TERMINATED BY ',' "
                    + "FROM Authors, Books WHERE "
                    + "Authors.Id=Books.AuthorId";
            
            pst = con.prepareStatement(query);
            pst.execute();

        } finally {

            if (pst != null) {
                pst.close();
            }

            if (con != null) {
                con.close();
            }

        }
    }
}
