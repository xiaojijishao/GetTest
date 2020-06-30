package com.shop.service.home;

import com.shop.dao.impl.AddressDaoImpl;
import com.shop.dao.inte.AddressDaoInte;
import com.shop.entity.Address;

import java.util.HashMap;
import java.util.Map;

public class AddressService {

    private AddressDaoInte adi = new AddressDaoImpl();

    public Address getDefaultAddress(int cid){

        Map<String,String> where = new HashMap<>();
        where.put("cid",cid+"");
        where.put("is_default",1+"");

        return adi.selectOne(where);

    }

    public Address find(int id) {
        return adi.selectOne(id);
    }
}
