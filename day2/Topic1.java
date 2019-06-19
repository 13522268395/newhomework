package homework;

import java.sql.SQLOutput;

/**
 * 作业1: 实现99乘法表
 *
 * 输出:
 *
 * 1*1=1
 *
 * 2*1=2 2*2=4
 *
 * 3*1=3 3*2=6 3*3=9
 *
 * ....
 *
 * 9*1=9 .................. 9*9=81
 *
 * @author haoc
 */
public class Topic1 {
    public static void main(String[] args) {
        //左下三角
       for (int i = 1; i <= 9; i++) {
         for (int j = 1; j <= i; j++) {
             //System.out.println 加上ln就换行了
             System.out.print(i + "*" + j + "=" + i * j + " ");
         }
         System.out.println();
        }
    }
}
//左上三角
//for (int i = 9; i >= 1; i--) {
//      for (int j = 1; j <= i; j++) {
//          System.out.print(i + "*" + j + "=" + i * j + " ");
//      }
//}