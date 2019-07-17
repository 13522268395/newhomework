package homework.day7.ordermanger;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @Author myy
 * @create 2019/7/17 22:05
 */
public class InsertOrderConvertOrder implements OrderModifyConvert{

    @Override
    public void convert(PreparedStatement statement, Order order) throws SQLException {
        statement.setInt(1,order.getOrder_id());
        statement.setLong(2,order.getOrder_amount());
        statement.setInt(3,order.getOrder_user_id());
        statement.setString(4,order.getOrder_user_name());
        statement.setInt(5,order.getOrder_merchant_id());
        statement.setString(6,order.getOrder_merchant_name());
    }
}
