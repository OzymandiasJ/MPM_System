package com.ozy.demo_application;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/demo5")
public class servletdemo5 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getServletContext().setAttribute("uname","zhangsan");
        System.out.println("demo5");
        resp.sendRedirect("demo6");
//        req.getRequestDispatcher("demo2").forward(req,resp);
    }
}
