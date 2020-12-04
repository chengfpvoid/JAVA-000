package com.cheng.dyds.annotation;

import java.lang.annotation.*;

/**
 * 动态数据源注解，通过指定的路由key来切换不同的数据源 默认路由key是master
 */
@Target({ElementType.METHOD, ElementType.TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Ds {
    String value() default "master";
}
