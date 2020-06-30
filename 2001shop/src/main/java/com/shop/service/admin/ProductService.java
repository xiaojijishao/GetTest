package com.shop.service.admin;

import com.shop.dao.impl.ProductDaoImpl;
import com.shop.dao.inte.ProductDaoInte;
import com.shop.entity.Product;
import com.shop.entity.Type;

import java.util.List;

public class ProductService {

    private ProductDaoInte pdi = new ProductDaoImpl();

    public List<Product> queryAll(){
        return pdi.selectAll(null);
    }

    public Product find(int id) {
        return pdi.selectOne(id);
    }

    public boolean save(Product product) {

        if(product.getId()== null || product.getId() == 0){
            return pdi.insert(product);
        }else{
            return pdi.update(product);
        }

    }
}
