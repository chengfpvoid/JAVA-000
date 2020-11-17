package com.cheng.ioc.bean;

import org.springframework.stereotype.Component;

import java.util.StringJoiner;

/**
 * 通过@Component @Service @Repository等注解+ComponentScan扫描包发现的bean
 */
@Component
public class UserComponent {

    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", UserComponent.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .toString();
    }
}
