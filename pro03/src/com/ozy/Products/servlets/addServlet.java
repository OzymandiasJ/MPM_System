package com.ozy.Products.servlets;

import com.ozy.Products.dao.ProductsDao;
import com.ozy.Products.dao.impl.ProductsDaoImpl;
import com.ozy.beans.Product;
import com.ozy.myssm.myspringmvc.ViewBaseServlet;
import com.ozy.myssm.utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/add.do")
public class addServlet extends ViewBaseServlet {
    private ProductsDao productsDao=new ProductsDaoImpl();
    ProductsDaoImpl productsDaoImpl=new ProductsDaoImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        设置编码
        req.setCharacterEncoding("utf-8");
//        获取参数
        String productName=req.getParameter("productName") ;
        double price= Double.parseDouble(req.getParameter("price"));
        double productRemain= Double.parseDouble(req.getParameter("productRemain"));
        double inPrice= Double.parseDouble(req.getParameter("inPrice"));
//        执行添加
        try {
            productsDao.addProduct(new Product(productName,price,productRemain,inPrice));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
//        更新表单数据,必须要更新，否则前端显示的数据是旧的
        List<Product> productList = null;
        try {
            productList = productsDaoImpl.getProductsList();
            HttpSession session = req.getSession();
            session.removeAttribute("productlist");//先删除原有的
            session.setAttribute("productList",productList );//设置新的数据
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

//        跳转
        super.processTemplate("index",req,resp);
    }
}
