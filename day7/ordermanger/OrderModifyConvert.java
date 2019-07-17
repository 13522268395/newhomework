package homework.day7.ordermanger;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @Author myy
 * @create 2019/7/17 22:04
 */
public interface OrderModifyConvert {

    void convert(PreparedStatement statement,Order order) throws SQLException;

}
