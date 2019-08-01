package homework.databasesync;

import homework.databasesync.consts.ConnectConsts;
import homework.databasesync.dao.JdbcUtils;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * @Author myy
 * @create 2019/7/30 22:56
 */
public class TestUnit {

    @Test
    public void testRead(){
        List<Map<String,Object>> response = JdbcUtils.read(ConnectConsts.SRC,"cakes","select * from user");

        response.forEach(System.out::println);
    }

}
