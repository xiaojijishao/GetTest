package com.shop.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.List;
import java.util.Properties;

public class DBUtil {

   /* //数据源（连接池）
    private static DataSource dataSource = null;

    static {
        //新建一个配置文件对象
        Properties properties = new Properties();
        //通过类加载器找到文件路径，读配置文件
        InputStream inputStream = DBUtil.class.getResourceAsStream("/db.properties");
        //加载属性文件
        try {
            properties.load(inputStream);
            //创建连接池对象
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    public static <T> List<T> query(String sql, Object[] args,Class<T> t){
//        QueryRunner runner = new QueryRunner(dataSource);
        TXQueryRunner runner = new TXQueryRunner();
        List<T> list  = null;
        try {
            list = runner.query(sql,new BeanListHandler<>(t),args);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
        }
        return list;
    }

    //增删改
    public static int update(String sql,Object[] args){
//        QueryRunner runner = new QueryRunner(dataSource);
        TXQueryRunner runner = new TXQueryRunner();
        int rows = 0;
        try {
            rows =  runner.update(sql,args);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
        }
        return rows;
    }

   /* public static DataSource getDataSource() {
        return dataSource;
    }

    //获得连接
    public static Connection getConnection(){
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }*/

    //释放资源
    public static void release(Connection connection,Statement statement,ResultSet resultSet){
        if(connection!=null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(statement!=null){
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(resultSet!=null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
