package com.zetcode;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

//Hello @PaulVargas I just discovered that it's as suspected, 
//Eclipselink simply doesn't support (yet) the finding of entity 
//classes on Java SE (it does on Java EE). So, I had to add <class> 
//elements to my persistence.xml; then it worked fine. 
public class DBClient {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("cars-pu");
        EntityManager eman = emf.createEntityManager();

        try {

            String sql = "SELECT c FROM Car c";

            Query query = eman.createQuery(sql);
            List<Car> cars = query.getResultList();

            for (Car car : cars) {
                System.out.printf("%d ", car.getId());
                System.out.printf("%s ", car.getName());
                System.out.println(car.getPrice());
            }
            
        } finally {

            eman.close();
            emf.close();
        }
    }
}
