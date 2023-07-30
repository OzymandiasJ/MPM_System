package com.ozy.myssm.myspringmvc;

import com.ozy.myssm.ioc.BeanFactory;
import com.ozy.myssm.utils.StringUtils;
import com.ozy.myssm.ioc.impl.ClassPathXmlApplicationContext;
import org.xml.sax.SAXException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

@WebServlet("*.do")
public class DispatcherServlet extends ViewBaseServlet {

    private BeanFactory beanFactory=null;

    public DispatcherServlet(){}

    /**
     * 作用是读取xml配置文件，将xml文件bean项的id值和controller值对应起来，扔到Map容器中去
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    @Override
    public void init() throws ServletException {
        System.out.println("initFun");
        super.init();
        //之前是在此处主动创建IOC容器的
        // 现在优化为从application作用域中获取
        ServletContext application =getServletContext();
        Object beanFactoryObj =application.getAttribute("beanFactory");
        if(beanFactoryObj!=null){
            beanFactory=(BeanFactory) beanFactoryObj;
        }else {
            throw new RuntimeException("IOC容器获取失败");
        }
    }


    /**
     * 根据配置文件中的信息找到对应的controller，然后根据operate参数确定调用的方法
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //注意不要选错了，要选ServletRequest
        System.out.println("DispatcherServlet");

        String servletPath=request.getServletPath();//内容：/hello.do
        System.out.println(servletPath);
        int start = servletPath.indexOf("/") + 1;
        int end = servletPath.indexOf(".");
        servletPath=servletPath.substring(start, end);//取到hello
        System.out.println(servletPath);
        Object controllerBeanObj =beanFactory.getBean(servletPath);
        //得到对应的方法ProductsController
        //        获取参数
        String operate=request.getParameter("operate") ;
        if(StringUtils.isEmpty(operate)||operate.equals("search")){
            //初始页和search查询操作进入index
            operate="index";
        }

        try {
            Method[] methods=controllerBeanObj.getClass().getDeclaredMethods();
            for (Method method:methods) {
                if(operate.equals(method.getName())){
                    //1,统一获取请求参数
                    //获取当前方法 的参数，返回参数数组
                    Parameter[] parameters=method.getParameters();
                    //parameterValues用于承载参数的值
                    Object[] parameterValues = new Object[parameters.length];
                    for (int i = 0; i < parameters.length; i++) {
                        Parameter parameter=parameters[i];
                        String parameterName=parameter.getName();
                        if("request".equals(parameterName)){
                            parameterValues[i]=request;
                        } else if ("response".equals(parameterName)) {
                            parameterValues[i]=response;
                        } else if ("session".equals(parameterName)) {
                            parameterValues[i]=request.getSession();
                        } else {
                            //从请求中获取参数
                            String parameterValue=request.getParameter(parameterName);
                            String typeName=parameter.getType().getName();
                            Object parameterObj =parameterValue;
                            if(parameterObj!=null&&"java.lang.Integer".equals(typeName)){
                                parameterObj=Integer.parseInt(parameterValue);
                            }else if(parameterObj!=null&&"double".equals(typeName)){
                                parameterObj= Double.parseDouble(parameterValue);
                            }
                            else if(parameterName.equals("pageNo")&&parameterValue==null) {
                                parameterObj = 1;
                            }
                            if(parameterName.equals("keyword")&&(parameterValue==null||parameterValue.equals(""))){
                                parameterObj="all";
                            }
                            parameterValues[i]=parameterObj;
                        }
                    }
                    //2,controller方法调用
                    method.setAccessible(true);
                    Object returnObj= method.invoke(controllerBeanObj,parameterValues);
                    //3.视图处理
                    String methodReturnStr=(String) returnObj;
                    if(methodReturnStr.startsWith("redirect:")){//比如redirect:products.do
                        String redirectStr=methodReturnStr.substring("redirect:".length());
                        System.out.println(redirectStr);
                        response.sendRedirect(redirectStr);
                    } else {
                        super.processTemplate(methodReturnStr,request,response);//比如“edit”
                    }
                }
            }
//            else {
//                System.out.println("operate值非法");
//                throw new RuntimeException("operate值非法");
//            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new DispatcherServletException("DispatcherServlet出现异常！！！");
        }
    }
}
