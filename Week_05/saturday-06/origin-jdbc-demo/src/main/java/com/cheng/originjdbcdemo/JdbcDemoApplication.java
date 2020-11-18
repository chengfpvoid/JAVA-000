package com.cheng.originjdbcdemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@SpringBootApplication
@Slf4j
public class JdbcDemoApplication implements CommandLineRunner {

    private static final String URL = "jdbc:h2:mem:test";

    private static final String DRIVER_NAME = "org.h2.Driver";


    public static void main(String[] args) {
        SpringApplication.run(JdbcDemoApplication.class, args);
    }



    @Override
    public void run(String... args) throws Exception {
        Class.forName(DRIVER_NAME);
       try(Connection conn = DriverManager.getConnection(URL);
        Statement statement = conn.createStatement()) {
           String createSql = "CREATE TABLE FOO (ID INT IDENTITY, BAR VARCHAR(64))";
           String insertSql = "insert into FOO values( 1,'aaa'),(2,'bbb')";
           String updateSql = "UPDATE FOO SET BAR='ccc' WHERE id = 1";
           String delSql = "DELETE FROM FOO WHERE BAR='ccc'";
           statement.execute("DROP TABLE IF EXISTS FOO");
           statement.execute(createSql);
           //插入
           statement.execute(insertSql);
           //更新
           statement.executeLargeUpdate(updateSql);
           //删除
           statement.executeUpdate(delSql);
           //查询
           ResultSet resultSet = statement.executeQuery("SELECT * FROM FOO ");
           while (resultSet.next()) {
               System.out.println(resultSet.getLong("id") + "," + resultSet.getString("BAR"));
           }

       }


    }

}

