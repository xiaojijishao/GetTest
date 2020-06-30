package com.shop.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

//支持事务
public class JdbcUtils {

    //通过druid连接池得到DataSource
    private static DataSource ds;

    static {
        //新建一个配置文件对象
        Properties properties = new Properties();
        //通过类加载器找到文件路径，读配置文件
        InputStream inputStream = DBUtil.class.getResourceAsStream("/db.properties");
        //加载属性文件
        try {
            properties.load(inputStream);
            //创建连接池对象
            ds = DruidDataSourceFactory.createDataSource(properties);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //定义一个Connection来判断是否有事务,将con放在ThreadLocal中
    //那么QueryRunner在使用事务时，就可以不用传con，而交由util控制

    //在JdbcUtils中在开启事务时得到的的Connection存放在ThreadLocal中，这样提交事务，回滚事务时所有的连接就是同一个连接了。
    private static ThreadLocal<Connection> conn = new ThreadLocal<Connection>();

    //返回DataSource
    public static DataSource getDataSource(){
        return ds;
    }

    //通过DataSource得到Connection
    public static Connection getConnection() throws SQLException {
        //得到ThreadLocal中的connection
        Connection con = conn.get();
        //如果开启了事务，则con不为空，应该直接返回con
        if(con != null){
            return con;
        }
        return ds.getConnection();
    }

    // 开启事务
    public static void beginTransaction() throws SQLException {
        //得到ThreadLocal中的connection
        Connection con = conn.get();
        //判断con是否为空，如果不为空，则说明事务已经开启
        if(con != null){
            throw new SQLException("事务已经开启了,不能重复开启事务");
        }
        //如果不为空，则开启事务
        con = getConnection();
        //设置事务提交为手动
        con.setAutoCommit(false);
        //把当前开启的事务放入ThreadLocal中
        conn.set(con);
    }

    // 提交事务
    public static void commitTransaction() throws SQLException {
        //得到ThreadLocal中的connection
        Connection con = conn.get();
        //判断con是否为空，如果为空，则说明没有开启事务
        if(con == null){
            throw new SQLException("没有开启事务,不能提交事务");
        }
        //如果con不为空,提交事务
        con.commit();
        //事务提交后，关闭连接
        con.close();
        //将连接移出ThreadLocal
        conn.remove();
    }

    // 回滚事务
    public static void rollbackTransaction() throws SQLException {
        //得到ThreadLocal中的connection
        Connection con = conn.get();
        //判断con是否为空，如果为空，则说明没有开启事务，也就不能回滚事务
        if(con == null){
            throw new SQLException("没有开启事务,不能回滚事务");
        }
        //事务回滚
        con.rollback();
        //事务回滚后，关闭连接
        con.close();
        //将连接移出ThreadLocal
        conn.remove();
    }

    // 关闭事务
    public static void releaseConnection(Connection connection) throws SQLException {
        //得到ThreadLocal中的connection
        Connection con = conn.get();
        //如果参数连接与当前事务连接不相等，则说明参数连接不是事务连接，可以关闭，否则交由事务关闭
        if(connection != null && con != connection){
            //如果连接没有被关闭，关闭之
            if(!connection.isClosed()){
                connection.close();
            }
        }
    }


}
