package com.zetcode.map;

import com.zetcode.bean.Book;
import java.util.List;
import org.apache.ibatis.annotations.Select;

public interface MyMapper {

    @Select("SELECT * FROM MyBooks")
    public List<Book> getAllBooks();   
}
