package com.zetcode;

import org.skife.jdbi.v2.Batch;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;

public class JDBIEx6 {

    public static void main(String[] args) {

        DBI dbi = new DBI("jdbc:mysql://localhost:3306/testdb?useSSL=false",
                "testuser", "test623");

        Handle handle = dbi.open();
        Batch batch = handle.createBatch();

        batch.add("DROP TABLE IF EXISTS Friends");
        batch.add("CREATE TABLE Friends(Id INT AUTO_INCREMENT "
                + "PRIMARY KEY, Name TEXT)");
        batch.add("INSERT INTO Friends(Name) VALUES ('Monika')");
        batch.add("INSERT INTO Friends(Name) VALUES ('Tom')");
        batch.add("INSERT INTO Friends(Name) VALUES ('Jane')");
        batch.add("INSERT INTO Friends(Name) VALUES ('Robert')");

        batch.execute();
    }
}
