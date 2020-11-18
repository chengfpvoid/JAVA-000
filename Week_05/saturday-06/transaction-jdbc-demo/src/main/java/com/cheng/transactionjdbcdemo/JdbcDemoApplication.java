package com.cheng.transactionjdbcdemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@Slf4j
public class JdbcDemoApplication implements CommandLineRunner {

    private static final String URL = "jdbc:h2:mem:test";

    private static final String DRIVER_NAME = "org.h2.Driver";


    public static void main(String[] args) {
        SpringApplication.run(JdbcDemoApplication.class, args);
    }



    @Override
    public void run(String... args)  {
        Connection conn = null;
        try {
            Class.forName(DRIVER_NAME);
            String createSql = "CREATE TABLE FOO (ID INT IDENTITY, BAR VARCHAR(64))";
            conn = DriverManager.getConnection(URL);
            conn.prepareStatement(createSql).execute();
            //关闭事务自动提交
            conn.setAutoCommit(false);
            //批量插入
            String insertSql = "INSERT INTO FOO(ID,BAR)VALUES(?,?)";
            PreparedStatement ps =  conn.prepareStatement(insertSql);
            List<Foo> foos = new ArrayList<>(3);
            foos.add(new Foo(1L,"aaa"));
            foos.add(new Foo(2L,"bbb"));
            foos.add(new Foo(1L,"ccc"));
            for(Foo foo : foos) {
                ps.setLong(1,foo.getId());
                ps.setString(2,foo.getBar());
                ps.addBatch();
            }
            int[] n = ps.executeBatch();
            //打印每个sql执行结果数量
            Arrays.stream(n).forEach(System.out::println);
            //提交事务
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                //回滚事务
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }finally {
            if(conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }


    }

}

