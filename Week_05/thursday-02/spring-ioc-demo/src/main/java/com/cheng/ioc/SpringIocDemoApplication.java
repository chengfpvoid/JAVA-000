package com.cheng.ioc;

import com.cheng.ioc.bean.User;
import com.cheng.ioc.bean.UserComponent;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class SpringIocDemoApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext context =  new SpringApplicationBuilder(SpringIocDemoApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);
        UserComponent userComponent = context.getBean(UserComponent.class);
        System.out.println("通过component注解得到的bean:"+userComponent);
        User user = (User) context.getBean("userBean");
        User user2 = (User) context.getBean("user");
        System.out.println("通过java配置类 + bean注解 得到bean:" + user);
        System.out.println("通过java配置类 + bean注解 得到bean:" + user2);
        ClassPathXmlApplicationContext xmlContext = new ClassPathXmlApplicationContext("spring-bean.xml");
        User user3 = (User) xmlContext.getBean("userXml");
        System.out.println("通过xml配置方式得到bean:" + user3);
    }



}
