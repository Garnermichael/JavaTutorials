package com.zetcode;

import com.zetcode.bean.Friend;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class SpringClassicEx {

    public static void main(String[] args) {

        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("my-beans.xml");

        JdbcTemplate jt = (JdbcTemplate) ctx.getBean("jdbcTemplate");

        jt.execute("DROP TABLE IF EXISTS Friends");
        jt.execute("CREATE TABLE Friends(Id INT, Name VARCHAR(30), "
                + "Age INT)");
        jt.update("INSERT INTO Friends VALUES(1, 'Paul', 27)");
        jt.update("INSERT INTO Friends VALUES(2, 'Monika', 34)");
        jt.update("INSERT INTO Friends VALUES(3, 'Peter', 20)");
        jt.update("INSERT INTO Friends VALUES(4, 'Lucy', 45)");
        jt.update("INSERT INTO Friends VALUES(5, 'Roman', 57)");

        int id = 1;
        String sql = "SELECT * FROM Friends WHERE Id=?";

        Friend f = (Friend) jt.queryForObject(sql, new Object[]{id},
                new BeanPropertyRowMapper(Friend.class));

        System.out.println(f);

        List<Friend> allFriends = jt.query("SELECT * FROM Friends",
                new BeanPropertyRowMapper(Friend.class));
        
        allFriends.stream().forEach(System.out::println);
    }
}
