package com.shop.dao.inte;

import java.util.List;
import java.util.Map;

public interface BaseDaoInte<T> {

    boolean insert(T t);
    boolean delete(int id);
    boolean update(T t);
    List<T> selectAll(Map<String,String> where);
    List<T> selectAllPage(Map<String,String> where,int offSet,int PageSize);
    T selectOne(Map<String,String> where);
    T selectOne(int id);


}
