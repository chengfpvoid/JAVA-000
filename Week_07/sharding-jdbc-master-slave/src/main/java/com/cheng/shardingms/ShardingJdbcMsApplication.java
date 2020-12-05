package com.cheng.shardingms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.cheng.**.mapper")
public class ShardingJdbcMsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShardingJdbcMsApplication.class, args);
    }

}
