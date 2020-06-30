package com.shop.dao.impl;

import com.shop.dao.inte.OrdersDaoInte;
import com.shop.entity.Orders;
import com.shop.util.DBUtil;
import com.shop.util.TXQueryRunner;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;

public class OrdersDaoImpl extends BaseDaoImpl<Orders> implements OrdersDaoInte {
    @Override
    public Integer getMaxId() {

        TXQueryRunner runner = new TXQueryRunner();
        String sql = "select max(id) from orders";
        try {
            Integer id = runner.query(sql,new ScalarHandler<Integer>());
            return id;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }
}

