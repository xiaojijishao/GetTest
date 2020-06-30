package com.shop.service.admin;

import com.shop.dao.impl.TypeDaoImpl;
import com.shop.dao.inte.TypeDaoInte;
import com.shop.entity.Type;

import java.util.List;

public class TypeService {

    private TypeDaoInte tdi = new TypeDaoImpl();

    public List<Type> queryAll(){
        return tdi.selectAll(null);
    }

    public Type find(int id) {
        return tdi.selectOne(id);
    }
}
