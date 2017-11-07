package com.zetcode;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DBClient2 {

    public static void main(String[] args) {

        EntityManagerFactory efact = Persistence.createEntityManagerFactory("cars-pu");
        EntityManager eman = efact.createEntityManager();

        try {

            eman.getTransaction().begin();

            Car car = new Car();
            car.setName("Toyota");
            car.setPrice(26700);

            eman.persist(car);
            eman.getTransaction().commit();
            
        } finally {
            
            eman.close();
            efact.close();
        }
    }
}
