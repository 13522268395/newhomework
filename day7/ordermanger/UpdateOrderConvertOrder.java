package homework.day7.ordermanger;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @Author myy
 * @create 2019/7/17 23:28
 */
public class UpdateOrderConvertOrder implements OrderModifyConvert{
    @Override
    public void convert(PreparedStatement statement, Order order) throws SQLException {
        //基于order_id进行修改，修改金额字段，字段不确定再想把。。
        statement.setLong(1,order.getOrder_amount());
        statement.setInt(2,order.getOrder_id());
    }
}
