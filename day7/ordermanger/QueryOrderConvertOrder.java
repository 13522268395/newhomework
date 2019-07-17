package homework.day7.ordermanger;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @Author myy
 * @create 2019/7/18 0:06
 */
public class QueryOrderConvertOrder implements OrderModifyConvert {
    @Override
    public void convert(PreparedStatement statement, Order order) throws SQLException {
        //基于订单id 或者 订单商家名称查询
        statement.setInt(1,order.getOrder_id());
        //statement.setString(2,order.getOrder_merchant_name());
    }
}
