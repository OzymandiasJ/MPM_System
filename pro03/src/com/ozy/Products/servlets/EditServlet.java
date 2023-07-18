package com.ozy.Products.servlets;

import com.ozy.Products.dao.ProductsDao;
import com.ozy.Products.dao.impl.ProductsDaoImpl;
import com.ozy.beans.Product;
import com.ozy.myssm.myspringmvc.ViewBaseServlet;
import com.ozy.myssm.utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigInteger;
import java.sql.SQLException;


@WebServlet("/edit.do")
public class EditServlet extends ViewBaseServlet {
    private ProductsDao productsDao=new ProductsDaoImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("edit");
        try
        {
            String idstr=req.getParameter("id");
            System.out.println(StringUtils.isEmpty(idstr));
            if(!StringUtils.isEmpty(idstr)) {
                BigInteger id = BigInteger.valueOf(Integer.parseInt(idstr));
                Product product=productsDao.getProductById(id);
                System.out.println(product.getProductName());
                req.setAttribute("product",product);
                super.processTemplate("edit",req,resp);
            }
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
