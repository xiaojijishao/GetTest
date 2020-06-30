package com.shop.servlet.admin;

import com.alibaba.fastjson.JSON;
import com.shop.entity.Type;
import com.shop.service.admin.TypeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/api/admin/type")
public class TypeServlet extends HttpServlet {

    private TypeService ts = new TypeService();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("a");

        switch(action){
            case "list":list(req,resp);break;
            case "find":find(req,resp);break;
        }

    }

    private void find(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = req.getParameter("id") == null ? 0 : Integer.parseInt(req.getParameter("id"));
        Type type = ts.find(id);
        resp.setCharacterEncoding("utf-8");
        resp.getWriter().print(JSON.toJSONString(type));
    }

    private void list(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        List<Type> list = ts.queryAll();

        resp.setCharacterEncoding("utf-8");
        resp.getWriter().print(JSON.toJSONString(list));
    }
}
