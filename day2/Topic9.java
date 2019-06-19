package homework;

/**
 * 作业9: 请用面向对象的思路设计: 马路上跑汽车
 *
 * - 马路: 可能是告诉, 也可能是辅路,还可能是乡村公路
 *
 * - 汽车: 可能是宝马, 可能是奔驰, 还可能是奥拓
 *
 * - 请用多态来进行设计
 *
 * @author haoc
 */
    /**
     * Topic9是路类
     */
class Road {
    String name;
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    /**
     * 路类有一个方法
     */
    public void showName(){

    }
}
/**
 * 高速公路类继承路类
 */
class Highway extends Road{
    @Override
    public void showName(){
        System.out.println("行驶于高速公路");
    }
}
/**
 * 辅路类继承路类
 */
class Siderd extends Road{
    @Override
    public void showName(){
        System.out.println("行驶于辅路");
    }
}
/**
 * 乡村公路类继承路类
 */
class Countryrd extends Road{
    @Override
    public void showName(){
        System.out.println("行驶于乡村公路");
    }
}
/**
 * 定义一个汽车类
 */
class Car{
    String name;
    public String getName(){
        return name;
    }
    public String setName(String name){
        this.name = name;
        return name;
    }
    public void run(){
    }
}
/**
 * 奔驰类继承于汽车类
 */
class Benz extends Car{
    @Override
    public void run(){
        System.out.println("奔驰时速120km/h");
    }
}
/**
 * 宝马类继承于汽车类
 */
class Bmw extends Car{
    @Override
    public void run(){
        System.out.println("宝马时速100km/h");
    }
}
/**
 * 奥拓类继承于汽车类
 */
class Alto extends Car{
    @Override
    public void run(){
        System.out.println("奥拓时速50km/h");
    }
}
class Traffic{
    public void travel(Car acar,Road aroad){
        acar.run();
        aroad.showName();
    }
}
/**
 * @author myy
 */
public class Topic9{
    public static void main(String[] args) {
        Traffic traffic = new Traffic();
        traffic.travel(new Benz(),new Highway());
        System.out.println("===================");
        traffic.travel(new Bmw(),new Countryrd());
        System.out.println("===================");
        traffic.travel(new Alto(),new Siderd());
    }
}