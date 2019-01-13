package com.xxq.vip.spring.annotation;

import java.lang.annotation.*;

/**
 * @author xiaoqiang
 * @Title: GPRequestParam
 * @ProjectName spring-ioc-demo
 * @Description: TODO
 * @date 2019-01-13 11:46
 */
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface GPRequestParam {
    String value() default "";
}
