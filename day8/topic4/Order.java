package homework.day8.topic4;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @Author myy
 * @create 2019/7/22 21:46
 */
@Setter
@Getter
@ToString
public class Order {
    private int order_id;
    private String type;
    List<Trade> trades;

    private Order(){

    }
    public static Order of(){
        return new Order();
    }
    private Order(int order_id,String type,List<Trade> trades){
        this.order_id = order_id;
        this.type = type;
        this.trades = trades;
    }
    public static Order of(int order_id,String type,List<Trade> trades){
        return new Order(order_id,type,trades);
    }
}
