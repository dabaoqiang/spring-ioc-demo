package com.xxq.vip.spring.annotation;

import java.lang.annotation.*;

/**
 * @author xiaoqiang
 * @Title: GPRequestMapping
 * @ProjectName spring-ioc-demo
 * @Description: TODO
 * @date 2019-01-13 11:40
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface GPRequestMapping {
    String value() default "";
}
