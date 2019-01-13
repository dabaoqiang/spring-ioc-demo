package com.xxq.vip.spring.annotation;

import java.lang.annotation.*;

/**
 * @author xiaoqiang
 * @Title: GPController
 * @ProjectName spring-ioc-demo
 * @Description: TODO
 * @date 2019-01-13 11:42
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface GPController {
    String value() default "";
}
