package com.zetcode.service;

import com.zetcode.bean.Book;
import java.util.List;

public interface IBooksService {
    
    List<Book> getAllBooks();
}
