package com.xxq.vip.spring.annotation;

import java.lang.annotation.*;

/**
 * @author xiaoqiang
 * @Title: GPAutowried
 * @ProjectName spring-ioc-demo
 * @Description: TODO
 * @date 2019-01-13 11:44
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface GPAutowried {
String value() default  "";
}
