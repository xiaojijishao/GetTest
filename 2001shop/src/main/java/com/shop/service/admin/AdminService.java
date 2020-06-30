package com.shop.service.admin;

import com.shop.dao.impl.AdminDaoImpl;
import com.shop.dao.inte.AdminDaoInte;
import com.shop.entity.Admin;

import java.util.List;

public class AdminService {

    private AdminDaoInte adi = new AdminDaoImpl();

    public List<Admin> queryAll(){

        return adi.selectAll(null);

    }


    public boolean changeStatus(Integer id, Integer status) {
        //先根据id查找 到 Admin
        Admin admin = adi.selectOne(id);
        if(admin != null){
            //给admin 赋予新的状态
            admin.setStatus(status);
            //再更新
            return adi.update(admin);
        }
        return false;
    }

    public boolean addOrUpdate(Admin admin) {
        if (admin.getId() == null || admin.getId() == 0) {
            return adi.insert(admin);
        }else{
            return adi.update(admin);
        }


    }

    public Admin find(Integer id) {
        return adi.selectOne(id);
    }
}
