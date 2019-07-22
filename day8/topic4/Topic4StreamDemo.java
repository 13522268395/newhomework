package homework.day8.topic4;

import com.sun.java.accessibility.util.Translator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 *
 * topic 4: 定义两个对象.Order,Trade,一般来说订单都是包含有多个交易的.且交易分渠道.比如:
 *
 * 订单A(order a): order_id:34567887654345678,
 *      两笔交易, 其中一个是微信支付的,8元,一笔是使用的优惠券2元.
 * 大体结构即:
 * class Oradr{
 *   List<Trade> trades;
 * }
 *
 * 要求: 自行定义order和trade的属性,啥id啊,name那些.
 *
 * 完成:
 * 1. 基于渠道的分组,即找出所有使用了微信的支付, 或者支付宝的,
 * 2. 基于订单维度,计算出没笔订单共多少钱,并排序
 * @author myy
 */
public class Topic4StreamDemo {

    public static void main(String[] args) {
        List<Order> orders = OrderFactory.getSimpleOrders();

        //基于order支付状态 已付清 部分支付分组
        Map<String, List<Order>> group = orders.stream()
                .collect(Collectors.groupingBy(Order::getType));

        OrderFactory.printOrder(group);

        List<Order> orders1 = OrderFactory.getSimpleOrders();

        //基于trade的名字 支付宝或微信等进行分组
        List<Order> orderByPayType = OrderFactory.getSimpleOrders();

        //Map<String, List<Order>> collect = orderByPayType.stream().collect(Collectors.groupingBy(Order::getTrades,Collectors.groupingBy(Trade::getName)));



        //System.out.println(collect);

    }






}
