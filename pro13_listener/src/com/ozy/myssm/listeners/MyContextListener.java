package com.ozy.myssm.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class MyContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("servlet上下文初始化动作被监听");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("servlet上下文销毁动作被监听");
    }
}
