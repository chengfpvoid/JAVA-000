package com.cheng.shardingdt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.cheng.**.mapper")
public class ShardingJdbcDtApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShardingJdbcDtApplication.class, args);
    }

}
