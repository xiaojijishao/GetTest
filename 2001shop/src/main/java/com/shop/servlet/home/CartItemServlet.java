package com.shop.servlet.home;

import com.alibaba.fastjson.JSON;
import com.shop.entity.Address;
import com.shop.entity.CartItem;
import com.shop.entity.CartItemProduct;
import com.shop.entity.Product;
import com.shop.service.home.AddressService;
import com.shop.service.home.CartItemService;
import com.shop.service.home.ProductService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/api/home/cartItem")
public class CartItemServlet extends HttpServlet {

    private CartItemService cis = new CartItemService();
    private AddressService as = new AddressService();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        String action = req.getParameter("a");
        
        switch (action){
            case "change":change(req,resp);break;
            case "add":add(req,resp);break;
            case "list":list(req,resp);break;
            case "info":info(req,resp);break;
        }
        
    }

    private void info(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String ids = req.getParameter("ids");
        String[] str_ids = ids.split("-");

        //购物车商品的数据
        List<CartItemProduct> list = new ArrayList<>();
        for(String id : str_ids){
            CartItemProduct cartItemProduct = cis.queryOne(Integer.parseInt(id));
            list.add(cartItemProduct);
        }

        int cid = 1;//TODO 在session中获取*/

        //地址的数据
        Address address = as.getDefaultAddress(cid);


        Map responseData = new HashMap();

        responseData.put("list",list);
        responseData.put("address",address);
        responseData.put("code",200);
        responseData.put("msg","ok");

        resp.setCharacterEncoding("utf-8");
        resp.getWriter().print(JSON.toJSONString(responseData));

    }

    private void change(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        add(req,resp);
    }

    private void list(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int cid = 1;//TODO 在session中获取*/
        //查询这个人的购购车项目和对应商品的信息
        List<CartItemProduct> list = cis.queryAll(cid);
        //System.out.println(list);

        resp.setCharacterEncoding("utf-8");
        resp.getWriter().print(JSON.toJSONString(list));
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) throws IOException {

       /* Integer pid = req.getParameter("pid") == null ? 0 : Integer.parseInt(req.getParameter("pid"));
        Integer num = req.getParameter("num") == null ? 0 : Integer.parseInt(req.getParameter("num"));
        Integer cid = 1;*/

        CartItem cartItem = new CartItem();
        try {
            BeanUtils.populate(cartItem,req.getParameterMap());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        cartItem.setCid(1);//TODO 在session中获取*/

        boolean flag = cis.add(cartItem);
        Map<String,String> responseMap = new HashMap<>();
        responseMap.put("code",flag ? "200" : "404");
        responseMap.put("msg",flag ? "加入成功！" : "加入失败！");

        resp.setCharacterEncoding("utf-8");
        resp.getWriter().print(JSON.toJSONString(responseMap));

    }

}
