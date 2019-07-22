package homework.day8.topic4;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @Author myy
 * @create 2019/7/22 22:04
 */
public class TradeFactory {
    public static List<Trade> getSimpleTrades(boolean type){

        List<Trade> trades = Lists.newArrayList();

        if (type){
            trades.add(Trade.of("支付宝支付",1,10));
            trades.add(Trade.of("微信支付",2,5));
            trades.add(Trade.of("优惠券支付",3,2));

        }else {
            trades.add(Trade.of("拉卡拉支付",4,20));
            trades.add(Trade.of("银联支付",5,15));
            trades.add(Trade.of("京东白条支付",6,10));
        }


        return trades;
    }
}
