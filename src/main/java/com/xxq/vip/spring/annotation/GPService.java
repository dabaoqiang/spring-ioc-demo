package com.xxq.vip.spring.annotation;

import java.lang.annotation.*;

/**
 * @author xiaoqiang
 * @Title: GPService
 * @ProjectName spring-ioc-demo
 * @Description: TODO
 * @date 2019-01-13 11:43
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface GPService {
    String value() default "";
}
