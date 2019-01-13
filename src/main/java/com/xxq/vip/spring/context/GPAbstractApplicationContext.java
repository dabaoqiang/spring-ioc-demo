package com.xxq.vip.spring.context;

/**
 * @author xiaoqiang
 * @Title: GPAbstractApplicationContext
 * @ProjectName spring-ioc-demo
 * @Description: TODO
 * @date 2019-01-13 13:15
 */
public abstract class GPAbstractApplicationContext {

    public abstract void onRefresh();

    public  abstract  void refreshBeanFactory();
}
