package homework.day8.topic4;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        System.out.println("以上根据支付情况分组");

        //基于orderId分组
        List<Order> orderList = OrderFactory.getSimpleOrders();
        orderList.stream().collect(Collectors.groupingBy(Order::getOrder_id)).forEach((k,v)-> System.out.println(String.valueOf(k)+v));
        System.out.println("以上根据订单id分组");

        //基于trade的名字 支付宝或微信等进行分组
        List<Order> simpleOrders = OrderFactory.getSimpleOrders();
        Stream<List<Trade>> listStream = simpleOrders.stream().map(Order::getTrades);
        listStream.flatMap(Collection::stream).collect(Collectors.groupingBy(Trade::getName)).forEach((k, v)-> System.out.println(k+v));
        System.out.println("以上根据支付渠道分组");

        //计算每笔订单的总金额
        List<Order> amountSum = OrderFactory.getSimpleOrders();
        Map<Integer, List<Order>> collect = amountSum.stream().collect(Collectors.groupingBy(Order::getOrder_id));
        for (Map.Entry<Integer, List<Order>> integerListEntry : collect.entrySet()) {
            Integer reduce = integerListEntry.getValue().stream()
                    .map(Order::getTrades)
                    .flatMap(Collection::stream)
                    .map(Trade::getTrade_amount)
                    .reduce(0, Integer::sum);
            System.out.println("第" + integerListEntry.getKey() +"订单，总金额为" + reduce + "元");
        }

        //计算每笔订单的金额 并排序
        List<Order> orderList1 = OrderFactory.getSimpleOrders();
        int[] orderAmount = new int[5];
        int i = 0;
        Map<Integer, List<Order>> integerListMap = orderList1.stream().collect(Collectors.groupingBy(Order::getOrder_id));

        Iterator<Map.Entry<Integer, List<Order>>> iterator = integerListMap.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<Integer, List<Order>> next = iterator.next();
            Integer reduce = next.getValue().stream().map(Order::getTrades).flatMap(child -> child.stream()).map(trade -> trade.getTrade_amount()).reduce(0, Integer::sum);
            orderAmount[i++] = reduce;
        }
        Arrays.sort(orderAmount);
        System.out.println(Arrays.toString(orderAmount));

        //        for (Map.Entry<Integer, List<Order>> integerListEntry : integerListMap.entrySet()) {
        //            Integer reduce = integerListEntry.getValue().stream()
        //                   .map(Order::getTrades)
        //                    .flatMap(child -> child.stream())
        //                    .map(trade -> trade.getTrade_amount())
        //                    .reduce(0, Integer::sum);
        //            System.out.println(reduce);
        //        }


        //下面求的是所有订单的总金额
        //List<Integer> collect1 = amountSum.stream().flatMap(Order -> Order.getTrades().stream().map(trade -> trade.getTrade_amount())).collect(Collectors.toList());
        //Stream<List<Trade>> stream = amountSum.stream().map(Order::getTrades);
        //DoubleSummaryStatistics collect = stream.flatMap(child -> child.stream()).map(trade -> trade.getTrade_amount()).collect(Collectors.summarizingDouble(value -> value));
        //System.out.println(collect.getSum());
        //Stream<Trade> tradeStream = stream.flatMap(child -> child.stream());
        //Integer reduce = tradeStream.map(trade -> trade.getTrade_amount()).reduce(0, Integer::sum);
        //System.out.println(reduce);
    }
}
