package com.shop.service.home;

import com.shop.dao.impl.AddressDaoImpl;
import com.shop.dao.impl.OrderItemDaoImpl;
import com.shop.dao.impl.OrdersDaoImpl;
import com.shop.dao.impl.ProductDaoImpl;
import com.shop.dao.inte.*;
import com.shop.entity.*;
import com.shop.util.JdbcUtils;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class OrderService {

   private OrdersDaoInte odi = new OrdersDaoImpl();
   private AddressDaoInte adi = new AddressDaoImpl();
   private OrderItemDaoInte oidi = new OrderItemDaoImpl();
   private CartItemService cis = new CartItemService();
   private ProductDaoInte pdi = new ProductDaoImpl();

   public int order(int cid,int aid,double sum,String ids) {

      Orders order = new Orders();

      order.setCid(cid);//谁买的
      order.setSum(sum);//总金额

      Address address = adi.selectOne(aid);
      order.setName(address.getName());
      order.setTel(address.getTel());
      order.setCode(address.getCode());
      order.setAddress(address.getProvince()+address.getCity()+address.getArea()+address.getStreet()+address.getDetail());

      order.setStatus(1);//待支付
      order.setAddtime(new Date());;

      try {
         //这里有大量的一系列的数据库操作，要么都成功，要么都不成功
         // 需要加事务。

         //开启事务
         JdbcUtils.beginTransaction();

         //订单生成了
         boolean flag = odi.insert(order);
         int oid = odi.getMaxId();

         if(flag){
            //将购物车商品项 转换成 订单项
            String[] str_ids = ids.split("-");
            for(String id : str_ids){
               CartItemProduct cartItemProduct = cis.queryOne(Integer.parseInt(id));

               OrderItem orderItem = new OrderItem();
               orderItem.setOid(oid);
               orderItem.setPid(cartItemProduct.getProduct().getId());
               orderItem.setNum(cartItemProduct.getCartItem().getNum());
               orderItem.setPrice(cartItemProduct.getProduct().getXprice());
               //生成订单项
               oidi.insert(orderItem);

               //System.out.println(10/0);

               //该商品的销售量 要增加
               Product product = cartItemProduct.getProduct();
               product.setSellNum(product.getSellNum()+cartItemProduct.getCartItem().getNum());
               pdi.update(product);

               //TODO 发货后 库存量减少

               //删除 购物车里的已经购买的商品项
               cis.del(cartItemProduct.getCartItem().getId());

               //提交事务
               JdbcUtils.commitTransaction();

               return oid;
            }

         }

      } catch (Exception e) {
         try {
            //回滚事务
            JdbcUtils.rollbackTransaction();
         } catch (Exception ex) {
            ex.printStackTrace();
         }
         e.printStackTrace();
      }finally {
      }

      return 0;

   }

}
