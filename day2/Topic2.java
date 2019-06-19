package homework;

/**
 * 输出1到100以内的所有质数
 *
 * 质数: 只能被1和自身整除的数,如3,7,11,13等
 *
 * @author haoc
 */
public class Topic2 {
    public static void main(String[] args) {
        boolean bool ;
        //除了2，所有偶数都不是质数，所以步长设为2，只遍历奇数
        for (int i = 3; i <= 100; i = i + 2) {
            bool = true;
            //奇数肯定不能整除偶数，所以步长也设为2
            //通过规律发现除数只需要从3 到 除数的开平方根数 就行了
            for (int j = 3; j <= Math.sqrt(i); j = j + 2) {
                if (i % j == 0) {
                    bool = false;
                }
                break;
            }
            if (bool){
                System.out.print(i + " ");
            }
        }
    }
}
