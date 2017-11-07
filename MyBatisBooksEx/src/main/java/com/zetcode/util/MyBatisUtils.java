package com.zetcode.util;

import java.io.IOException;
import java.io.Reader;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtils {

    private static SqlSessionFactory factory;

    private MyBatisUtils() {
    }

    static {
        Reader read = null;

        try {
            read = Resources.getResourceAsReader("mybatis-config.xml");

            factory = new SqlSessionFactoryBuilder().build(read);

            read.close();
            
        } catch (IOException ex) {
            Logger.getLogger(MyBatisUtils.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
    }

    public static SqlSessionFactory getSqlSessionFactory() {

        return factory;
    }
}
