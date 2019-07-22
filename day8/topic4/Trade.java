package homework.day8.topic4;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author myy
 * @create 2019/7/22 21:47
 */
@Setter
@Getter
@ToString
public class Trade {
    private String name;
    private int type;
    private int trade_amount;
    private Trade(){

    }
    public static Trade of(){
        return new Trade();
    }
    private Trade(String name,int type,int trade_amount){
        this.name = name;
        this.type = type;
        this.trade_amount = trade_amount;
    }
    public static Trade of(String name, int type,int trade_amount){
        return new Trade(name,type,trade_amount);
    }
}
