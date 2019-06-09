package homework;
/**
 * 作业5:实现一个杨辉三角
 *
 * 要求: 打印多少行由方法入参传入
 *
 * 杨辉三角的样式:
 *
 *               1
 *              1 1
 *             1 2 1
 *            1 2 3 2 1
 *           1 2 3 5 3 2 1
 * @author haoc
 */
//public class Topic5 {
//    public void triangle(int row){
//        int prev = 1;
//        int[] arr = new int[row];
//        for( int i = 0; i < row; i++) {
//            for( int j = 0; j <= i; j++) {
//                // 将上一行当前下标元素的值保存
//                int cur = arr[j];
//                // 当前元素等于上一行当前元素和上一行当前元素前一个元素之和
//                arr[j] = prev+cur;
//                // 对于下一圈循环，prev保存了当前行上一行元素前一个元素，即prev与cur保存的值一前一后
//                prev = cur;
//                System.out.print(arr[j]+" ");
//            }
//            System.out.println();
//        }
//    }
//
//    public static void main(String[] args) {
//        Topic5 t5 = new Topic5();
//        int row = 20;
//        t5.triangle(row);
//    }
//}
//public class Topic5 {
//    public static void main(String args[]) {
//        final int NMAX = 10;
//
//        int[][] odds = new int[NMAX][];
//        for (int k = 0; k < odds.length; k++) {
//            odds[k] = new int[k+1];
//        }
//
//        for (int i = 0; i < odds.length; i++) {
//            System.out.printf("%" + (odds.length - i)*2 + "s", "");
//            for (int j = 0; j < i + 1; j++) {
//                if (j==0||j==i) {
//                    odds[i][j] = 1;
//                }else {
//                    odds[i][j] = odds[i-1][j-1] + odds[i-1][j];
//                }
//                System.out.printf("%4d", odds[i][j]);
//            }
//            System.out.println();
//        }
//    }
//}
public class Topic5{
    public static void triangle(int NMAX){
        int[][] odds = new int[NMAX][];
        for (int k = 0; k < odds.length; k++) {
            odds[k] = new int[k+1];
        }

        for (int i = 0; i < odds.length; i++) {
            System.out.printf("%" + (odds.length - i)*2 + "s", "");
            for (int j = 0; j < i + 1; j++) {
                //首尾都是1
                if (j==0||j==i) {
                    odds[i][j] = 1;
                }else {
                    //当前元素等于上一行当前元素和上一行当前元素前一个元素之和
                    odds[i][j] = odds[i-1][j-1] + odds[i-1][j];
                }
                System.out.printf("%4d", odds[i][j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Topic5.triangle(5);
    }
}