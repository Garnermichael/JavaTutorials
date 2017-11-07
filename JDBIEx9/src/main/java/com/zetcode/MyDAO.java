package com.zetcode;

import java.util.List;
import org.skife.jdbi.v2.exceptions.TransactionFailedException;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.Transaction;

public abstract class MyDAO {

    @SqlUpdate("INSERT INTO Authors(Name) VALUES(:author)")
    public abstract void createAuthor(@Bind("author") String author);
   
    @SqlQuery("SELECT Id FROM Authors WHERE Name = :name")
    abstract long getAuthorId(@Bind("name") String name);        
        
    @SqlUpdate("INSERT INTO Books(AuthorId, Title) "
            + "VALUES(:authorId, :title)")
    abstract void insertBook(@Bind("authorId") Long authorId, 
           @Bind("title") String title);
    
    @Transaction
    public void insertBooksForAuthor(String author, 
            List<String> titles) {
        
        Long authorId = getAuthorId(author);
        
        if (authorId == null) {
            throw new TransactionFailedException("No author found");
        }
        
        for (String title : titles) {
            
            insertBook(authorId, title);
        }
    }
}
