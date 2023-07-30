package com.ozy.myssm.listeners;

import com.ozy.myssm.ioc.BeanFactory;
import com.ozy.myssm.ioc.impl.ClassPathXmlApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * 监听上下文启动，在上下文启动的时候去创建IOC容器，
 * 然后将其保存在application作用域，后面中央控制器会从中获取
 */
@WebListener
public class ContextLoaderListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //            1,获取上下文对象
        ServletContext application = sce.getServletContext();
//            2，获取上下文初始化参数
        try {
            String path=application.getInitParameter("contextConfigLocation");
            BeanFactory beanFactory=new ClassPathXmlApplicationContext(path);
            application.setAttribute("beanFactory",beanFactory);
        }catch (Exception e){
            throw new RuntimeException("获取初始化参数contextConfigLocation失败，可能原因是在xml文件中没有配置该参数");
        }


    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContextListener.super.contextDestroyed(sce);
    }
}
