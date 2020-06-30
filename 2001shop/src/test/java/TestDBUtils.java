import com.shop.util.DBUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;

import java.sql.SQLException;

public class TestDBUtils {

    @Test
    public void testInsert(){

       /* QueryRunner runner = new QueryRunner(DBUtil.getDataSource());
        String sql = "insert into admin(account,password) values(?,?)";
        Object[] args = {"admin333","admin"};
        try {
            //返回的值 是 新增的ID的值
            long rows = runner.insert(sql,new ScalarHandler<Long>(),args);
            System.out.println("rows = " + rows);
        } catch (SQLException e) {
            e.printStackTrace();
        }*/

    }

    @Test
    public void testSelect(){
        /*QueryRunner runner = new QueryRunner(JDBCUtil.getDataSource());
        String sql = "select * from admin";
        Object[] args = {};
        try {
            List<Admin> list = runner.query(sql,new BeanListHandler<>(Admin.class),args);
            System.out.println("list = " + list);
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
    }
}
