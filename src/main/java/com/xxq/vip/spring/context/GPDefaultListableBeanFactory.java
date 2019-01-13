package com.xxq.vip.spring.context;

import com.xxq.vip.spring.beans.GPBeanDefinition;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xiaoqiang
 * @Title: GPDefaultListableBeanFactory
 * @ProjectName spring-ioc-demo
 * @Description: TODO
 * @date 2019-01-13 13:16
 */
public class GPDefaultListableBeanFactory extends  GPAbstractApplicationContext {

    /**
     * beanDefinitionMap
     */
    public Map<String, GPBeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, GPBeanDefinition>();


    public void onRefresh() {

    }

    public void refreshBeanFactory() {

    }
}
