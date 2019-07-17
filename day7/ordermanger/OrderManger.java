package homework.day7.ordermanger;

import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @Author myy
 * @create 2019/7/17 0:45
 */
public class OrderManger<T> {

    private OrderManger(){}

    public static <T> OrderManger<T> of(){
        return new OrderManger<>();
    }

    public static void main(String[] args) {
        Order order = Order.of();
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入您计划进行的操作序号：1-生成订单、2-修改订单、3-删除订单、4-查询订单、exit-退出程序");
        while (scanner.hasNext()){
            int orderType = scanner.nextInt();
            switch (orderType){
                case 1:
                    System.out.println("生成订单");
                    System.out.println("请依此输入订单编号、金额、付款人id、付款人姓名、商家id、商家名称");
                    order.setOrder_id(scanner.nextInt());
                    order.setOrder_amount(scanner.nextLong());
                    order.setOrder_user_id(scanner.nextInt());
                    order.setOrder_user_name(scanner.next());
                    order.setOrder_merchant_id(scanner.nextInt());
                    order.setOrder_merchant_name(scanner.next());
                    String sql = "insert into ordermanger(order_id,order_amount,order_user_id,order_user_name,order_merchant_id,order_merchant_name)" +
                            "values(?,?,?,?,?,?)";
                    handleOrder(sql,order,new InsertOrderConvertOrder());
                    System.out.println("订单生成成功");
                    break;
                case 2:
                    System.out.println("修改订单");
                    System.out.println("请依此输入订单编号、订单金额");
                    order.setOrder_id(scanner.nextInt());
                    order.setOrder_amount(scanner.nextLong());
                    String sqlUpdate = "update ordermanger set order_amount = ? where order_id = ?";
                    handleOrder(sqlUpdate,order,new UpdateOrderConvertOrder());
                    System.out.println("订单修改成功");
                    break;
                case 3:
                    System.out.println("删除订单");
                    System.out.println("请输入订单编号");
                    order.setOrder_id(scanner.nextInt());
                    String sqlDetele = "delete from ordermanger where order_id = ?";
                    handleOrder(sqlDetele,order,new DeleteOrderConvertOrder());
                    System.out.println("订单删除成功");
                    break;
                case 4:
                    System.out.println("查询订单");
                    System.out.println("请输入订单编号");
                    order.setOrder_id(scanner.nextInt());
                    String sqlQuery = "select * from ordermanger where order_id = ?";
                    List<Order> orders = OrderManger.<Order>of().select(sqlQuery, order, new ModifyConvert<Order>() {
                        @Override
                        public void convert(PreparedStatement statement, Order order) throws SQLException {
                            statement.setInt(1,order.getOrder_id());
                        }
                    }, new OrderMapper());
                    System.out.println("查询信息如下");
                    System.out.println("order = " + orders);
                    break;
                default:
                    System.out.println("操作类型错误");
            }
            if (scanner.next().equals("exit")){
                System.out.println("退出程序");
                break;
            }
        }
    }

    public static int handleOrder(String sql, Order order, OrderModifyConvert convertr) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionHandler.of().getConnection();
            statement = connection.prepareStatement(sql);
            convertr.convert(statement,order);
            int effectRows = statement.executeUpdate();
            return effectRows;
        }catch (SQLException e){
            throw new IllegalStateException(e);
        }finally {
            release(null,statement,connection);
        }
    }

    public List<T> select(String sql,T t,ModifyConvert<T> convert,RowMapper<T> mapper){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionHandler.of().getConnection();
            statement = connection.prepareStatement(sql);
            convert.convert(statement,t);
            resultSet = statement.executeQuery();
            List<T> result = new ArrayList<>();
            while (resultSet.next()){
                T mapperResult = mapper.mapper(resultSet);
                result.add(mapperResult);
            }
            return result;
        }catch (SQLException e){
            throw new IllegalStateException(e);
        }finally {
            release(resultSet,statement,connection);
        }
    }

    private static void release(ResultSet resultSet, Statement statement, Connection connection) {
        try {
            if (null != resultSet) {
                resultSet.close();
            }
            if (null != statement) {
                statement.close();
            }
            if (null != connection) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
