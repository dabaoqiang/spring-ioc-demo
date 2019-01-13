package com.xxq.vip.demo.service.impl;

import com.xxq.vip.demo.service.UserService;
import com.xxq.vip.spring.annotation.GPService;

/**
 * @author xiaoqiang
 * @Title: UserServiceImpl
 * @ProjectName spring-ioc-demo
 * @Description: TODO
 * @date 2019-01-13 18:30
 */
@GPService(value = "userService")
public class UserServiceImpl implements UserService {

    public void sayHello(String name) {
        System.out.println("say hello : " + name);
    }
}
