package com.shop.servlet.home;

import com.alibaba.fastjson.JSON;
import com.shop.entity.Address;
import com.shop.entity.CartItemProduct;
import com.shop.entity.Product;
import com.shop.service.home.AddressService;
import com.shop.service.home.CartItemService;
import com.shop.service.home.OrderService;
import com.shop.service.home.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/api/home/order")
public class OrderServlet extends HttpServlet {

    private AddressService as = new AddressService();
    private CartItemService cis = new CartItemService();
    private OrderService os = new OrderService();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        String action = req.getParameter("a");
        
        switch (action){
            case "put":put(req,resp);break;
        }
        
    }

    private void put(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        int aid = Integer.parseInt(req.getParameter("aid"));//地址id
        double sum = Double.parseDouble(req.getParameter("sum"));//订单总金额
        String ids = req.getParameter("ids");//购买的商品，就是订单项
        int cid = 1;//TODO 登录用户id

        int oid = os.order(cid,aid,sum,ids);

        Map<String,Object> responseData = new HashMap<>();
        if(oid > 0){
            responseData.put("code",200);
            responseData.put("msg","下单成功！");
            responseData.put("oid",oid);
            responseData.put("sum",sum);
        }else{
            responseData.put("code",500);
            responseData.put("msg","网络异常！");
        }

        resp.setCharacterEncoding("utf-8");
        resp.getWriter().print(JSON.toJSONString(responseData));

    }

}
