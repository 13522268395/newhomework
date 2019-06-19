package homework;

/**
 * 作业7: 老师在黑板画圆
 *
 * 要求: 按照面向对象的思想设计
 *
 * - 老师不一定只会画圆,还可能会画正方形
 *
 * - 老师也可能会画大脸猫
 *
 * - 老师不见得只会在黑板上画,也可以在纸上画
 *
 * - 黑板只能用来画圆吗? 黑板还可以做题的
 *
 * - 圆形,也不是随便画的, 它还有圆心,有半径
 *
 * ----- 思考以上问题,在来设计, 看不懂的就多想想,让自己想5遍以上,如果还是想不明白,再来问老师
 *
 * @author haoc
 */
public class Topic7 {
    /**
     *定义一个人类，定义一个画的方法
     */
    class Person {
        String job;
        String name;
        int age;
        void drawing(){
            System.out.println("画画");
        }
        void doTitle(){
            System.out.println("做题");
        }
    }
    /**
     * 定义一个图形类
     */
    class Grap{
        String shape;
        double radius;
        void formPattern(){
            System.out.println("有各种图形构成的一种图案");
        }
    }
    /**
     * 定义一个物品类（在哪里画）
     */
    class Item{
        double length;
        double width;
        String components;
        void bePaint(){
            System.out.println("被画上了画");
        }
        void beDoTitle(){
            System.out.println("被做上了题");
        }
    }

}