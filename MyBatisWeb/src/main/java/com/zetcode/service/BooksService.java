package com.zetcode.service;

import com.zetcode.bean.Book;
import com.zetcode.map.MyMapper;
import com.zetcode.util.MyBatisUtils;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class BooksService implements IBooksService {

    private SqlSessionFactory factory = null;

    public BooksService() {

        factory = MyBatisUtils.getSqlSessionFactory();
        
        if (!factory.getConfiguration().hasMapper(MyMapper.class)) {
            factory.getConfiguration().addMapper(MyMapper.class);
        }
    }

    @Override
    public List<Book> getAllBooks() {

        SqlSession session = null;
        List<Book> books;

        try {
            session = factory.openSession();
            books = session.selectList("getAllBooks");

        } finally {

            if (session != null) {
                session.close();
            }
        }

        return books;
    }
}
