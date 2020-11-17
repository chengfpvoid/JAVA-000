package com.cheng.ioc.config;

import com.cheng.ioc.bean.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JavaConfig {

    @Bean
    public User userBean() {
        User user = new User(1L,"cheng");
        return user;
    }

    @Bean("user")
    public User userBean2() {
        User user = new User(2L,"cheng");
        return user;
    }
}
