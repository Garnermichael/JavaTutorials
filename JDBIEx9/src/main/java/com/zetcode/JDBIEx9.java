package com.zetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.skife.jdbi.v2.DBI;

public class JDBIEx9 {

    public static void main(String[] args) {

        DBI dbi = new DBI("jdbc:mysql://localhost:3306/testdb?useSSL=false",
                "testuser", "test623");

        List<Map<String, List<String>>> authorsBooks = new ArrayList<>();
        
        Map<String, List<String>> autMap1 = new HashMap<>();
        
        List<String> books1 = new ArrayList<>();
        books1.add("Call of the Wild");
        books1.add("Martin Eden");
        books1.add("The Iron Heel");
        books1.add("White Fang");
        
        autMap1.put("Jack London", books1);
        
        Map<String, List<String>> autMap2 = new HashMap<>();
        
        List<String> books2 = new ArrayList<>();
        books2.add("Father Goriot");
        books2.add("Colonel Chabert");
        books2.add("Cousing Pons");
//        books2.add("Cousing Pons");
        
        autMap2.put("Honore de Balzac", books2);        
        
        authorsBooks.add(autMap1);
        authorsBooks.add(autMap2);
        
        MyDAO dao = dbi.onDemand(MyDAO.class);
        
        for (Map<String, List<String>> map : authorsBooks) {
            
            Set<String> ks = map.keySet();
            
            for (String author : ks) {
                
                dao.createAuthor(author);
                
                List<String> titles = map.get(author);

                dao.insertBooksForAuthor(author, titles);
            }
        }
    }
}
