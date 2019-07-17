package homework.day7.ordermanger;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @Author myy
 * @create 2019/7/17 23:53
 */
public class DeleteOrderConvertOrder implements OrderModifyConvert {
    @Override
    public void convert(PreparedStatement statement, Order order) throws SQLException {
        statement.setInt(1,order.getOrder_id());
    }
}
