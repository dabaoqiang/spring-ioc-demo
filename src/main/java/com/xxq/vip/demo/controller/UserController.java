package com.xxq.vip.demo.controller;

import com.xxq.vip.demo.service.UserService;
import com.xxq.vip.spring.annotation.GPAutowried;
import com.xxq.vip.spring.annotation.GPController;
import com.xxq.vip.spring.annotation.GPService;

/**
 * @author xiaoqiang
 * @Title: UserController
 * @ProjectName spring-ioc-demo
 * @Description: TODO
 * @date 2019-01-13 18:32
 */
@GPController
public class UserController {

    @GPAutowried
    private UserService userService;

    public void sayHello(String name) {
        userService.sayHello("good " + name);
    }
}
