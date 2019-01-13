package com.xxq.vip.spring.core;

/**
 * @author xiaoqiang
 * @Title: BeanFactory
 * @ProjectName spring-ioc-demo
 * @Description: TODO
 * @date 2019-01-13 13:14
 */
public interface BeanFactory {

    Object getBean(String name);
}
