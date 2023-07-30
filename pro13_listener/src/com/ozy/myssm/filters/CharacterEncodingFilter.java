package com.ozy.myssm.filters;

import com.ozy.myssm.utils.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
@WebFilter("*.do")
public class CharacterEncodingFilter implements Filter {
    private  String encoding="UTF-8";
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    String encodingstr=filterConfig.getInitParameter("encoding");
    if(StringUtils.isNotEmpty(encodingstr)){
        encoding=encodingstr;
    }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Encoding:UTF-8");
        ((HttpServletRequest)servletRequest).setCharacterEncoding(encoding);
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
    }
}
