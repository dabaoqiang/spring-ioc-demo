package com.xxq.vip.spring.context.support;

import com.xxq.vip.spring.beans.GPBeanDefinition;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author xiaoqiang
 * @Title: GPBeanDefinitionReader
 * @ProjectName spring-ioc-demo
 * @Description: TODO
 * @date 2019-01-13 13:20
 */
public class GPBeanDefinitionReader {

    private String[] locations;
    private Properties properties = new Properties();
    private static final String scanPackage = "scanPackage";
    private List<String> registerBeanClass = new ArrayList<String>();

    public GPBeanDefinitionReader(String[] locations) {
        // 定位资源
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(locations[0].replaceAll("classpath:",""));
        try {
            properties.load(resourceAsStream);
        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            if (null != resourceAsStream) {
                try {
                    resourceAsStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        System.out.println("**********定位资源完成************");
        // 加载资源
        doScanner(properties.getProperty("scanPackage"));
        System.out.println("**********加载资源完成*************");
    }

    /**
     * 加载配置文件
     *
     * @param scanPackage
     */
    private void doScanner(String scanPackage) {
        // 将全定限类名，转成相对路径
        URL url = this.getClass().getClassLoader().getResource(scanPackage.replaceAll("\\.", "/"));
        File fileDirectory = new File(url.getFile());
        for (File file : fileDirectory.listFiles()) {
            if (file.isDirectory()) {
                doScanner(scanPackage + "." + file.getName());
            } else {
                registerBeanClass.add(scanPackage + "." + file.getName().replaceAll(".class", ""));
            }
        }
    }

    // 注册bean的方法
    public List<String> loadBeanDefinitions() {
        return this.registerBeanClass;
    }

    /**
     * 注入beanDefinition,配置文件
     *
     * @param className
     * @return
     */
    public GPBeanDefinition registerBean(String className) {
        GPBeanDefinition gpBeanDefinition = new GPBeanDefinition();
        gpBeanDefinition.setBeanClassName(className);
        gpBeanDefinition.setFactoryBeanName(lowerFirst(className.substring(className.lastIndexOf("." ) + 1)));
        return gpBeanDefinition;
    }

    /**
     * 小写
     *
     * @param beanClassName
     * @return
     */
    private String lowerFirst(String beanClassName) {
        char[] chars = beanClassName.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }

}
