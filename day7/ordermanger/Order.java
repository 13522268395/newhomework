package homework.day7.ordermanger;

/**
 * @Author myy
 * @create 2019/7/17 21:48
 */
public class Order {

    private int order_id;
    private long order_amount;
    private int order_user_id;
    private String order_user_name;
    private int order_merchant_id;
    private String order_merchant_name;

    private Order() {
    }

    public static Order of(){
        return new Order();
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public long getOrder_amount() {
        return order_amount;
    }

    public void setOrder_amount(long order_amount) {
        this.order_amount = order_amount;
    }

    public int getOrder_user_id() {
        return order_user_id;
    }

    public void setOrder_user_id(int order_user_id) {
        this.order_user_id = order_user_id;
    }

    public String getOrder_user_name() {
        return order_user_name;
    }

    public void setOrder_user_name(String order_user_name) {
        this.order_user_name = order_user_name;
    }

    public int getOrder_merchant_id() {
        return order_merchant_id;
    }

    public void setOrder_merchant_id(int order_merchant_id) {
        this.order_merchant_id = order_merchant_id;
    }

    public String getOrder_merchant_name() {
        return order_merchant_name;
    }

    public void setOrder_merchant_name(String order_merchant_name) {
        this.order_merchant_name = order_merchant_name;
    }

    @Override
    public String toString() {
        return "Order{" +
                "order_id=" + order_id +
                ", order_amount=" + order_amount +
                ", order_user_id=" + order_user_id +
                ", order_user_name='" + order_user_name + '\'' +
                ", order_merchant_id=" + order_merchant_id +
                ", order_merchant_name='" + order_merchant_name + '\'' +
                '}';
    }
}
