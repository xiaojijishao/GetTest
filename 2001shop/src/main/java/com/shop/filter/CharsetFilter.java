package com.shop.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
//字符集的过滤器
//@WebFilter("/*")
public class CharsetFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
//        System.out.println("CharsetFilter.init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
//        System.out.println("CharsetFilter.doFilter");

        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        //放行
        filterChain.doFilter(request,response);
    }

    @Override
    public void destroy() {
//        System.out.println("CharsetFilter.destroy");
    }
}
