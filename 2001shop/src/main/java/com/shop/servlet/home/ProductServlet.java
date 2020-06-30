package com.shop.servlet.home;

import com.alibaba.fastjson.JSON;
import com.shop.entity.Product;
import com.shop.service.home.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

@WebServlet("/api/home/product")
public class ProductServlet extends HttpServlet {

    private ProductService ps = new ProductService();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        String action = req.getParameter("a");
        
        switch (action){
            case "list":list(req,resp);break;
            case "detail":detail(req,resp);break;
        }
        
    }

    private void detail(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        Integer id = req.getParameter("id") == null ? 0 : Integer.parseInt(req.getParameter("id"));

        Product product = ps.find(id);

        resp.setCharacterEncoding("utf-8");
        resp.getWriter().print(JSON.toJSONString(product));

    }

    private void list(HttpServletRequest req, HttpServletResponse resp) throws IOException {

            List<Product> list = ps.queryAll();

            resp.setCharacterEncoding("utf-8");
            resp.getWriter().print(JSON.toJSONString(list));

    }
}
