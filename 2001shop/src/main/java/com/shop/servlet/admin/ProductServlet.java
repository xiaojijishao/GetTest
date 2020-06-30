package com.shop.servlet.admin;

import com.alibaba.fastjson.JSON;
import com.shop.entity.Admin;
import com.shop.entity.Product;
import com.shop.service.admin.ProductService;
import com.shop.service.admin.TypeService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

@WebServlet("/api/admin/product")
public class ProductServlet extends HttpServlet {

    private ProductService ps = new ProductService();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("a");

        switch(action){
            case "save":save(req,resp);break;
        }
    }

    private void save(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        req.setCharacterEncoding("utf-8");

        //获取到所有参数的map
        Map<String,String[]> map = req.getParameterMap();
        Map<String,String> newMap = new HashMap<>();
        Set<String> set = map.keySet();
        for(String key : set){
            String value;
            if(map.get(key) instanceof String[] ){
                //将 数组 连接 成 字符串，用，隔开
                value = String.join(",",map.get(key));
            }else{
                value = String.valueOf(map.get(key));
            }
            newMap.put(key,value);
            //System.out.println(key+":"+value );
        }

        Product product = new Product();
        try {
            BeanUtils.populate(product,newMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        product.setAddtime(new Date());
        product.setDetail(newMap.get("editorValue"));
        product.setImages(newMap.get("files"));
        product.setSid(1);//店铺id
        product.setStatus(1);
        product.setIsHot(1);
        product.setIsNew(1);
        product.setIsHome(1);
        product.setStore(100);
        product.setWatchNum(0);
        product.setSellNum(0);

        //System.out.println(product);

        boolean flag = ps.save(product);

        Map<String,String> responseMap = new HashMap<>();
        responseMap.put("code",flag ? "ok" : "error");
        responseMap.put("msg",flag ? "操作成功！" : "操作失败！");
        resp.setCharacterEncoding("utf-8");
        resp.getWriter().print(JSON.toJSONString(responseMap));
    }

}
