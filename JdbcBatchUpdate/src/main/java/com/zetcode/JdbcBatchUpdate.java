package com.zetcode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JdbcBatchUpdate {

    public static void main(String[] args) {

        Connection con = null;
        Statement st = null;

        String url = "jdbc:mysql://localhost:3306/testdb?useSSL=false";
        String user = "testuser";
        String password = "test623";

        try {

            con = DriverManager.getConnection(url, user, password);

            con.setAutoCommit(false);
            st = con.createStatement();

            st.addBatch("DROP TABLE IF EXISTS Authors2");
            st.addBatch("CREATE TABLE Authors2(Id INT PRIMARY KEY, "
                    + "Name VARCHAR(100))");
            st.addBatch("INSERT INTO Authors2(Id, Name) "
                    + "VALUES(1, 'Jack London')");
            st.addBatch("INSERT INTO Authors2(Id, Name) "
                    + "VALUES(2, 'Honore de Balzac')");
            st.addBatch("INSERT INTO Authors2(Id, Name) "
                    + "VALUES(3, 'Lion Feuchtwanger')");
            st.addBatch("INSERT INTO Authors2(Id, Name) "
                    + "VALUES(4, 'Emile Zola')");
            st.addBatch("INSERT INTO Authors2(Id, Name) "
                    + "VALUES(5, 'Truman Capote')");
            st.addBatch("INSERT INTO Authors2(Id, Name) "
                    + "VALUES(6, 'Umberto Eco')");

            int counts[] = st.executeBatch();

            con.commit();

            System.out.println("Committed " + 
                    counts.length + " updates");

        } catch (SQLException ex) {

            if (con != null) {

                try {

                    con.rollback();
                } catch (SQLException ex1) {

                    Logger lgr = Logger.getLogger(JdbcBatchUpdate.class.getName());
                    lgr.log(Level.WARNING, ex1.getMessage(), ex1);
                }
            }

            Logger lgr = Logger.getLogger(JdbcBatchUpdate.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);

        } finally {

            try {

                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {

                Logger lgr = Logger.getLogger(JdbcBatchUpdate.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }
    }
}