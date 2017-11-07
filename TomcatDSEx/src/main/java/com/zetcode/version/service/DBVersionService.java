package com.zetcode.version.service;

import com.zetcode.version.Version;
import com.zetcode.version.util.ServiceLocator;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

public class DBVersionService {
    
    public static String getMySQLVersion() {

        String version = "no version";
        String jndi_name = "java:comp/env/jdbc/testdb";
        
        DataSource ds = ServiceLocator.getDataSource(jndi_name);
        Connection con = null;
        
        try {
            con = ds.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("SELECT VERSION()");

            if (rs.next()) {

                version = rs.getString(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Version.class.getName()).log(Level.SEVERE, 
                    null, ex);
        } finally {
            
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DBVersionService.class.getName())
                            .log(Level.WARNING, null, ex);
                }
            }
        }

        return version;
    }
}
