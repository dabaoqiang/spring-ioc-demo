package com.xxq.vip.spring.context;

import com.xxq.vip.spring.annotation.GPAutowried;
import com.xxq.vip.spring.annotation.GPController;
import com.xxq.vip.spring.annotation.GPService;
import com.xxq.vip.spring.beans.GPBeanDefinition;
import com.xxq.vip.spring.beans.GPBeanWrapper;
import com.xxq.vip.spring.context.support.GPBeanDefinitionReader;
import com.xxq.vip.spring.core.BeanFactory;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xiaoqiang
 * @Title: GPAplicationContext
 * @ProjectName spring-ioc-demo
 * @Description: TODO
 * @date 2019-01-13 13:17
 */
public class GPAplicationContext extends GPDefaultListableBeanFactory implements BeanFactory {

    private GPBeanDefinitionReader beanDefinitionReader;
    /**
     * 单例bean
     */
    private Map<String, Object> cacheBean = new ConcurrentHashMap<String, Object>();

    /**
     * 封装wrapperMap
     */
    private Map<String, GPBeanWrapper> wrapperMap = new ConcurrentHashMap<String, GPBeanWrapper>();

    /**
     * 资源
     */
    private String[] locations;


    public GPAplicationContext(String[] locations) {
        this.locations = locations;
        onRefresh();
    }

    @Override
    public void onRefresh() {
        // 定位资源,加载资源
        this.beanDefinitionReader = new GPBeanDefinitionReader(locations);
        // 注册资源
        register(beanDefinitionReader.loadBeanDefinitions());
        // 注入 在注入的时候，真正去实现，NEW Bean以及组成Bean
        autoWried();
    }

    /**
     * 自动注入
     */
    private void autoWried() {
        for (Map.Entry<String, GPBeanDefinition> beanDefinitionEntry : beanDefinitionMap.entrySet()) {
            String beanClassName = beanDefinitionEntry.getKey();
            GPBeanDefinition definitionEntryValue = beanDefinitionEntry.getValue();
            // 这里有点坑，正常逻辑并不是循环两次，产生bean到wrapper
            getBean(beanClassName);
        }

        // 循环代理map，进行注入
        for (Map.Entry<String, GPBeanWrapper> stringGPBeanWrapperEntry : wrapperMap.entrySet()) {
            populateBean(stringGPBeanWrapperEntry.getKey(), stringGPBeanWrapperEntry.getValue().getOriginalInstance());
        }

        System.out.println("*********注入完成*********");
    }

    /**
     * 注入bean
     *
     * @param beanName
     * @param instance
     */
    private void populateBean(String beanName, Object instance) {
        try {
            Class<?> clazz = instance.getClass();
            // 只注入controller，service类
            if (!(clazz.isAnnotationPresent(GPController.class) || clazz.isAnnotationPresent(GPService.class))) {
                return;
            }
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (!field.isAnnotationPresent(GPAutowried.class)) {
                    continue;
                }
                GPAutowried annotation = field.getAnnotation(GPAutowried.class);
                String autoWriedBeanName = annotation.value().trim();
                if ("".equals(autoWriedBeanName)) {
                    autoWriedBeanName = field.getType().getName();
                }
                field.setAccessible(true);
                try {
                    autoWriedBeanName = lowerCase(autoWriedBeanName.substring(autoWriedBeanName.lastIndexOf(".") + 1));
                    field.set(instance, this.wrapperMap.get(autoWriedBeanName).getWrapperInstance());
                } catch (Exception e) {
                    e.getStackTrace();
                }
            }

        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    /**
     * 读取配置资源到BeanDefinition
     *
     * @param loadBeanDefinitions
     */
    private void register(List<String> loadBeanDefinitions) {
        if (loadBeanDefinitions.isEmpty()) {
            return;
        }

        // 开始注册
        for (String beanClassName : loadBeanDefinitions) {
            // 注册beanDefinition到Map
            Class<?> aClass = null;
            try {
                aClass = Class.forName(beanClassName);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            if (aClass.isInterface()) {
                continue;
            }

            GPBeanDefinition gpBeanDefinition = this.beanDefinitionReader.registerBean(beanClassName);
            if (gpBeanDefinition != null) {
                beanDefinitionMap.put(gpBeanDefinition.getFactoryBeanName(), gpBeanDefinition);
            }
            Class<?>[] interfaces = aClass.getInterfaces();
            for (Class<?> anInterface : interfaces) {
                // interface getName()方法返回的是全限定称类名，如果这个接口被实现了多次，会被覆盖多次
                beanDefinitionMap.put(lowerCase(anInterface.getName().substring(anInterface.getName().lastIndexOf(".") + 1)), gpBeanDefinition);
            }
        }

    }

    private String lowerCase(String substring) {
        char[] chars = substring.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }

    @Override
    public void refreshBeanFactory() {
    }

    /**
     * 获取bean的实例
     *
     * @param beanName
     * @return
     */
    public Object getBean(String beanName) {
        GPBeanDefinition gpBeanDefinition = this.beanDefinitionMap.get(beanName);
        if (gpBeanDefinition == null) {
            return null;
        }
        Object instance = null;
        try {
            instance = instantionBean(gpBeanDefinition);
            if (instance == null) {
                return null;
            }
            GPBeanWrapper gpBeanWrapper = new GPBeanWrapper(instance);
            wrapperMap.put(beanName, gpBeanWrapper);
            // 切记，返回的是对象的属性，不是对象
            return this.wrapperMap.get(beanName).getWrapperInstance();
        } catch (Exception e) {
            e.getStackTrace();
        }
        return null;
    }

    /**
     * 什么时候初始化bean，register bean的时候
     *
     * @param gpBeanDefinition
     * @return
     */
    private Object instantionBean(GPBeanDefinition gpBeanDefinition) {
        Object instance = null;
        try {
            String factoryBeanName = gpBeanDefinition.getBeanClassName();
            if (cacheBean.containsKey(factoryBeanName)) {
                return cacheBean.get(factoryBeanName);
            }
            Class<?> aClass = Class.forName(factoryBeanName);
            instance = aClass.newInstance();
            cacheBean.put(factoryBeanName, instance);

        } catch (Exception e) {
            e.getStackTrace();
        }
        return instance;
    }
}
