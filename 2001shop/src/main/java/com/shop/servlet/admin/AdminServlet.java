package com.shop.servlet.admin;

import com.alibaba.fastjson.JSON;
import com.shop.entity.Admin;
import com.shop.service.admin.AdminService;
import com.shop.util.RequestParserUtil;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/api/admin/admin")
public class AdminServlet extends HttpServlet {

    private AdminService as = new AdminService();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //System.out.println("AdminServlet.service");

        String action = req.getParameter("a") ==  null ? "" : req.getParameter("a");

        switch (action){
            case "list":list(req,resp);break;
            case "page":page(req,resp);break;
            case "status":status(req,resp);break;
            case "add":add(req,resp);break;
            case "find":find(req,resp);break;
            case "edit":edit(req,resp);break;

        }

    }

    private void edit(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        add(req,resp);
    }

    private void find(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        Integer id = req.getParameter("id")==null? null : Integer.parseInt(req.getParameter("id"));

        Map<String,Object> responseData = new HashMap<>();
        if(id == null){
            responseData.put("code","404");
            responseData.put("msg","请求错误");
        }else{
            Admin admin = as.find(id);
            if(admin != null){
                responseData.put("code","200");
                responseData.put("msg","ok");
                responseData.put("data",admin);

            }else{
                responseData.put("code","500");
                responseData.put("msg","服务器繁忙");
            }
        }
        resp.setCharacterEncoding("utf-8");
        resp.getWriter().print(JSON.toJSONString(responseData));

    }

    private void add(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        req.setCharacterEncoding("utf-8");

        //获取到所有参数的map
        Map<String,String[]>  map = req.getParameterMap();
        Admin admin = new Admin();

        try {
            //把map 的值 根据名字 填充到 admin对象中
            BeanUtils.populate(admin,map);
//            System.out.println(admin);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //接受参数的过程 被 BeanUtils 代替
       /* String account  = req.getParameter("account");
        String password  = req.getParameter("password");
        String confirmPassword  = req.getParameter("confirmpassword");
        String tel  = req.getParameter("tel");
        String email  = req.getParameter("email");
        int type  = Integer.parseInt(req.getParameter("type"));
        int gender  = Integer.parseInt(req.getParameter("gender"));
        String note  = URLDecoder.decode(URLDecoder.decode(req.getParameter("note")),"utf-8");*/

        //boolean flag = as.add(new Admin(0,account,password,1,tel,email,type,gender,note,new Date()));
        admin.setStatus(new Integer(1));
        admin.setAddtime(new Date());
        System.out.println(admin);

        boolean flag = as.addOrUpdate(admin);

        Map<String,String> responseData = new HashMap<>();
        if(flag){
            responseData.put("code","200");
            responseData.put("msg","操作成功!");
        }else{
            responseData.put("code","500");
            responseData.put("msg","服务器繁忙");
        }
        resp.setCharacterEncoding("utf-8");
        resp.getWriter().print(JSON.toJSONString(responseData));

    }

    private void status(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        Integer id = req.getParameter("id")==null? null : Integer.parseInt(req.getParameter("id"));
        Integer status = req.getParameter("status")==null? null : Integer.parseInt(req.getParameter("status"));

        Map<String,String> responseData = new HashMap<>();
        if(id == null || status == null){
            responseData.put("code","404");
            responseData.put("msg","请求错误");
        }else{
            boolean flag = as.changeStatus(id,status);
            if(flag){
                responseData.put("code","200");
                responseData.put("msg","ok");
            }else{
                responseData.put("code","500");
                responseData.put("msg","服务器繁忙");
            }
        }
        resp.setCharacterEncoding("utf-8");
        resp.getWriter().print(JSON.toJSONString(responseData));
    }

    private void list(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List list = as.queryAll();
        //System.out.println(list);

        //Gson 谷歌
        //fastjson 阿里巴巴
        String json = JSON.toJSONString(list);
        //System.out.println("json = " + json);

        resp.setCharacterEncoding("utf-8");
        resp.getWriter().print(json);
    }
    private void page(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List list = as.queryAll();
        //System.out.println(list);

        Map<String,Object> map = new HashMap<>();
        map.put("data",list);

        String json = JSON.toJSONString(map);
        //System.out.println("json = " + json);

        resp.setCharacterEncoding("utf-8");
        resp.getWriter().print(json);
    }

}
