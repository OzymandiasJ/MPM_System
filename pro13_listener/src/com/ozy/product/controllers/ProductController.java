package com.ozy.product.controllers;

import com.ozy.product.beans.Product;
import com.ozy.myssm.myspringmvc.ViewBaseServlet;
import com.ozy.myssm.utils.StringUtils;
import com.ozy.product.service.ProductService;
import ognl.Ognl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.List;
//@WebServlet("/products.do")
public class ProductController extends ViewBaseServlet {
private ProductService productService =null;
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //        设置编码
        request.setCharacterEncoding("utf-8");

        //        获取参数
        String operate=request.getParameter("operate") ;
        request.removeAttribute("operate");
        if(StringUtils.isEmpty(operate)){
            operate="index";
        }
        System.out.println(operate);

        //        反射机制
        Method[] methods=this.getClass().getDeclaredMethods();
        for(Method m:methods){
            String methodName=m.getName();
            if(operate.equals(methodName)){
                try {
                    m.invoke(this,request,response);
                    return;
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        System.out.println("operate值非法");
        throw new RuntimeException("operate值非法");
    }

    protected String index(String keyword,Integer pageNo,HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();
            List<Product> productList=null;
            productList = productService.getProductList(keyword,pageNo);
            session.setAttribute("productList",productList );//获取成功
            int pageCount= productService.getPageCount("all");
            session.setAttribute("pageCount",pageCount);
            if(!keyword.equals("all")){
//                说明是查询来的
                pageNo=1;
                session.setAttribute("keyword",keyword);
            }
            else {
                session.removeAttribute(keyword);
            }
            session.setAttribute("pageNo",pageNo );
            System.out.println(pageCount);
            return "index";

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    protected String add(String productName,double price,double productRemain,double inPrice) {
//        执行添加
        try {
            productService.addProduct(new Product(productName,price,productRemain,inPrice));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
////        更新表单数据,必须要更新，否则前端显示的数据是旧的
//        List<Product> productList = null;
//        try {
//            productList = productsDaoImpl.getProductsList();
//            HttpSession session = req.getSession();
//            session.removeAttribute("productlist");//先删除原有的
//            session.setAttribute("productList",productList );//设置新的数据
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } catch (NoSuchFieldException e) {
//            throw new RuntimeException(e);
//        } catch (InstantiationException e) {
//            throw new RuntimeException(e);
//        } catch (IllegalAccessException e) {
//            throw new RuntimeException(e);
//        }

    //  重定向
    //  resp.sendRedirect("products.do");
        return "redirect:products.do";
    }

    protected String edit(Integer id,HttpServletRequest request) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        if(id!=null){
            Product product= productService.getProductByPid(id);
            request.removeAttribute("product");
            request.setAttribute("product",product);
//                super.processTemplate("edit",req,resp);
            return "edit";
        }
        else
            return "error";
    }

    protected String delete(Integer id) {
//        获取参数
        if(id!=null){
//        执行更新
            try {
                productService.delete(id);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
////        更新表单数据,必须要更新，否则前端显示的数据是旧的
//            List<Product> productList = null;
//            try {
//                productList = productsDaoImpl.getProductsList();
//                HttpSession session = req.getSession();
//                session.removeAttribute("productlist");//先删除原有的
//                session.setAttribute("productList",productList );//设置新的数据
//                //        跳转
////                super.processTemplate("index",req,resp);
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            } catch (NoSuchFieldException e) {
//                throw new RuntimeException(e);
//            } catch (InstantiationException e) {
//                throw new RuntimeException(e);
//            } catch (IllegalAccessException e) {
//                throw new RuntimeException(e);
//            }
            return "redirect:products.do";
        } else
            return "error";
    }

    protected String update(String productName,double price,double productRemain,double inPrice,Integer id){
//        执行更新
        try {
            productService.updateProduct(new Product(id,productName,price,productRemain,inPrice));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
////        更新表单数据,必须要更新，否则前端显示的数据是旧的
//        List<Product> productList = null;
//        try {
//            productList = productsDaoImpl.getProductsList();
//            HttpSession session = req.getSession();
//            session.removeAttribute("productlist");//先删除原有的
//            session.setAttribute("productList",productList );//设置新的数据
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } catch (NoSuchFieldException e) {
//            throw new RuntimeException(e);
//        } catch (InstantiationException e) {
//            throw new RuntimeException(e);
//        } catch (IllegalAccessException e) {
//            throw new RuntimeException(e);
//        }

//        跳转
//        super.processTemplate("index",req,resp);
        return "redirect:products.do";
    }

//
//    protected String search(String searchName) throws IOException {
//        if(!StringUtils.isEmpty(searchName)) {
//            Product product= null;
//            try {
//                product = productService.getProductByName(searchName);
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            } catch (NoSuchFieldException e) {
//                throw new RuntimeException(e);
//            } catch (InstantiationException e) {
//                throw new RuntimeException(e);
//            } catch (IllegalAccessException | ClassNotFoundException e) {
//                throw new RuntimeException(e);
//            }
////            req.setAttribute("product",product);
////            super.processTemplate("edit",req,resp);
//            return "edit";
//        }
//        return "error";
//    }
}
