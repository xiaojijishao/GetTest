package com.shop.service.home;

import com.shop.dao.impl.ProductDaoImpl;
import com.shop.dao.inte.ProductDaoInte;
import com.shop.entity.Product;

import java.util.List;

public class ProductService {

    private ProductDaoInte pdi = new ProductDaoImpl();

    public List<Product> queryAll(){
        return pdi.selectAll(null);
    }

    public Product find(int id) {
        return pdi.selectOne(id);
    }

}
