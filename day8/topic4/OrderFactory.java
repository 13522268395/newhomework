package homework.day8.topic4;



import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author myy
 * @create 2019/7/22 22:02
 */
public class OrderFactory {
    public static List<Order> getSimpleOrders(){
        List<Order> orders = new ArrayList<>();

        orders.add(Order.of(1,"已付清",TradeFactory.getSimpleTrades(true)));
        orders.add(Order.of(2,"部分支付",TradeFactory.getSimpleTrades(false)));
        orders.add(Order.of(3,"已付清",TradeFactory.getSimpleTrades(false)));
        orders.add(Order.of(4,"已付清",TradeFactory.getSimpleTrades(false)));
        orders.add(Order.of(5,"部分支付",TradeFactory.getSimpleTrades(true)));

        return orders;
    }
    public static void printOrder(List<Order> orders){
        for (Order order : orders) {
            System.out.println("order = " + order);
        }
    }

    public static void printOrder(Map<String, List<Order>> orders){
        orders.forEach((key,val)->{
            System.out.println("groupName: " + key);
            for (Order order : val) {
                System.out.println("\t\t order " + order);
            }
        });
    }

}
