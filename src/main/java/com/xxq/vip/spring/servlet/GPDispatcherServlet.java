package com.xxq.vip.spring.servlet;

import com.xxq.vip.demo.service.UserService;
import com.xxq.vip.spring.context.GPAplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author xiaoqiang
 * @Title: GPDispatcherServlet
 * @ProjectName spring-ioc-demo
 * @Description: TODO
 * @date 2019-01-13 13:00
 */
public class GPDispatcherServlet extends HttpServlet {

    private GPAplicationContext aplicationContext;

    private static final String configLocation = "contextConfigLocation";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        aplicationContext = new GPAplicationContext(new String[]{config.getInitParameter(configLocation)});

        UserService userService = (UserService)aplicationContext.getBean("userService");
        userService.sayHello("xiaoqiang");
    }
}
