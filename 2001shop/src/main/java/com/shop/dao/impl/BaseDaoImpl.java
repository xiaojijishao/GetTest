package com.shop.dao.impl;

import com.shop.dao.inte.BaseDaoInte;
import com.shop.util.DBUtil;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class BaseDaoImpl<T> implements BaseDaoInte<T> {

    protected Class clazz;
    protected String tableName;

    public BaseDaoImpl() {
        getRealType();
    }

    // 使用反射技术得到T的真实类型
    public Class getRealType(){
        // 获取当前new的对象的泛型的父类类型
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        // 获取第一个类型参数的真实类型
        this.clazz = (Class<T>) pt.getActualTypeArguments()[0];
        this.tableName = this.clazz.getSimpleName().toLowerCase();
        return clazz;
    }


    @Override
    public boolean insert(T t) {

        String names = "";
        String values = "";
        Field[] fields = clazz.getDeclaredFields();
        Object[] args = new Object[fields.length-1];
        int i=0;
        for(Field f : fields){
            if(f.getName().equals("serialVersionUID")){
                continue;
            }

            names += f.getName()+",";
            values +="?,";
            try {
                f.setAccessible(true);
                args[i++] = f.get(t);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        names = names.substring(0,names.length()-1);
        values = values.substring(0,values.length()-1);

        String sql = "insert into "+tableName+"("+names+") values("+values+")";
        //System.out.println("sql = " + sql);

        int rows = DBUtil.update(sql,args);
        return rows>0;
    }

    @Override
    public boolean delete(int id) {
        String sql = "delete from "+tableName+" where id=?";
        Object[] args = {id};
        int rows = DBUtil.update(sql,args);
        return rows>0;
    }

    @Override
    public boolean update(T t) {
        StringBuilder sb = new StringBuilder();
        //获得该实体类的所有成员变量
        Field[] fields = clazz.getDeclaredFields();
        //sql语句的参数，要比成员变量多一个，多一个条件的id,但是要 减一个 serialVersionUID。相当于正好
        Object[] args = new Object[fields.length];
        int index = 0;
        for(Field f : fields){
            if(f.getName().equals("serialVersionUID")){
                continue;
            }
            //拼接sql
            sb.append(f.getName()+"=?,");
            f.setAccessible(true);
            try {
                //将值 放入 参数数组中
                args[index++]=f.get(t);
                //如果是id成员变量，将id值赋值到数组的最后一个元素上
                if(f.getName().equals("id")){
                    args[args.length-1] = f.get(t);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        //去除 最后 逗号
        sb.setLength(sb.length()-1);

        String sql = "update "+tableName+" set "+sb.toString()+" where id=?";

        int rows = DBUtil.update(sql,args);
        return rows>0;
    }

    @Override
    public List<T> selectAll(Map<String, String> where) {
        StringBuilder sql = new StringBuilder("select * from `"+tableName+"` where 1=1");
        Object[] args = null;

        if(where !=null){
            args = new Object[where.size()];
            Set<String> keySet = where.keySet();
            int index = 0;
            for(String key : keySet){
                sql.append(" and `"+key+"`=?");
                args[index++] = where.get(key);
            }
        }

        List<T> list = DBUtil.query(sql.toString(),args,clazz);
        return list.size()>0 ? list : null;
    }

    @Override
    public List<T> selectAllPage(Map<String, String> where, int offSet, int PageSize) {
        return null;
    }

    @Override
    public T selectOne(Map<String, String> where) {
        StringBuilder sql = new StringBuilder("select * from `"+tableName+"` where 1=1");
        Object[] args = new Object[where.size()];

        Set<String> keySet = where.keySet();
        int index = 0;
        for(String key : keySet){
            sql.append(" and `"+key+"`=?");
            args[index++] = where.get(key);
        }
        //System.out.println(sql);

        List<T> list = DBUtil.query(sql.toString(),args,clazz);
        return list.size()==1 ? list.get(0) : null;
    }

    @Override
    public T selectOne(int id) {
        String sql = "select * from "+tableName+" where id=?";
        Object[] args = {id};
        List<T> list = DBUtil.query(sql,args,clazz);
        return list.size()==1 ? list.get(0) : null;

    }
}
