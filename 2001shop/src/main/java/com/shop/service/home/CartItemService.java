package com.shop.service.home;

import com.shop.dao.impl.CartItemDaoImpl;
import com.shop.dao.impl.ProductDaoImpl;
import com.shop.dao.inte.CartItemDaoInte;
import com.shop.dao.inte.ProductDaoInte;
import com.shop.entity.CartItem;
import com.shop.entity.CartItemProduct;
import com.shop.entity.Product;
import jdk.nashorn.internal.ir.CatchNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartItemService {

    private CartItemDaoInte cdi = new CartItemDaoImpl();
    private ProductDaoInte pdi = new ProductDaoImpl();

    public boolean del(int id){
        return cdi.delete(id);
    }

    public boolean add(CartItem cartItem){

        //如果该用户 已经购买过 该商品,需要去 增加该商品的数据，就是修改
        Map<String,String> where = new HashMap<>();
        where.put("cid",cartItem.getCid().toString());
        where.put("pid",cartItem.getPid().toString());
        CartItem oldCartItem = cdi.selectOne(where);
        if(oldCartItem != null){
            oldCartItem.setNum(cartItem.getNum()+oldCartItem.getNum());
            return cdi.update(oldCartItem);
        }else{
            return cdi.insert(cartItem);
        }

    }

    public List<CartItemProduct> queryAll(int cid) {
        Map<String,String> where = new HashMap<>();
        where.put("cid",cid+"");
        List<CartItem> list = cdi.selectAll(where);
        List<CartItemProduct> newList = new ArrayList<>();
        for(CartItem item : list){
            CartItemProduct cartItemProduct =new CartItemProduct();
            Product product = pdi.selectOne(item.getPid());
            cartItemProduct.setCartItem(item);
            cartItemProduct.setProduct(product);
            newList.add(cartItemProduct);
        }
        return newList;
    }

    public CartItemProduct queryOne(int id) {

        CartItem item = cdi.selectOne(id);

        CartItemProduct cartItemProduct =new CartItemProduct();

        Product product = pdi.selectOne(item.getPid());

        cartItemProduct.setCartItem(item);
        cartItemProduct.setProduct(product);

        return cartItemProduct;

    }
}
