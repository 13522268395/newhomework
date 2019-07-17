package homework.day7.ordermanger;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author myy
 * @create 2019/7/18 0:18
 */
public class OrderMapper implements RowMapper<Order>{
    @Override
    public Order mapper(ResultSet rs) throws SQLException {
        Order o = Order.of();
        o.setOrder_id(rs.getInt("order_id"));
        o.setOrder_amount(rs.getLong("order_amount"));
        o.setOrder_user_id(rs.getInt("order_user_id"));
        o.setOrder_user_name(rs.getString("order_user_name"));
        o.setOrder_merchant_id(rs.getInt("order_merchant_id"));
        o.setOrder_merchant_name(rs.getString("order_merchant_name"));
        return o;
    }
}
