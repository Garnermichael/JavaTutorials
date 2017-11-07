package com.zetcode;

import org.skife.jdbi.v2.DBI;

public class JDBIEx8 {

    public static void main(String[] args) {

        DBI dbi = new DBI("jdbc:mysql://localhost:3306/testdb?useSSL=false",
                "testuser", "test623");
        
        int id = 3;
        
        MyDAO dao = dbi.onDemand(MyDAO.class);
        Car car = dao.findById(id);

        System.out.println(car);
        
        int nCars = dao.countCars();
        
        System.out.printf("There are %d cars in the table", nCars);
    }
}
