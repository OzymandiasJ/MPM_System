package com.ozy.Products.servlets;

import com.ozy.Products.dao.impl.ProductsDaoImpl;
import com.ozy.beans.Product;
import com.ozy.myssm.myspringmvc.ViewBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/index")
public class IndexServlet extends ViewBaseServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductsDaoImpl productsDaoImpl=new ProductsDaoImpl();

        try {
            List<Product> productList = productsDaoImpl.getProductsList();
            HttpSession session = req.getSession();
            session.setAttribute("productList",productList );//获取成功
            super.processTemplate("index",req,resp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
