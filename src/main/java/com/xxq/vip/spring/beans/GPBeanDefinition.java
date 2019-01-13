package com.xxq.vip.spring.beans;

/**
 * @author xiaoqiang
 * @Title: GPBeanDefinition
 * @ProjectName spring-ioc-demo
 * @Description: TODO
 * @date 2019-01-13 13:44
 */
public class GPBeanDefinition {
    /**
     * 全限制类名eg:com.xxq.rest
     */
    private String beanClassName;
    /**
     * 工厂bean的名称 userController
     */
    private String factoryBeanName;

    private Boolean lazyInit;

    /**
     * setter/getter
     */

    public String getBeanClassName() {
        return beanClassName;
    }

    public void setBeanClassName(String beanClassName) {
        this.beanClassName = beanClassName;
    }

    public String getFactoryBeanName() {
        return factoryBeanName;
    }

    public void setFactoryBeanName(String factoryBeanName) {
        this.factoryBeanName = factoryBeanName;
    }

    public Boolean getLazyInit() {
        return lazyInit;
    }

    public void setLazyInit(Boolean lazyInit) {
        this.lazyInit = lazyInit;
    }
}
