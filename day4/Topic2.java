package homework.day4;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author myy
 * @create 2019/6/25 23:23
 *
 * case 6: 定义一个类,实现一个key对应一个列表.数据类型是泛型
 *
 * 具备最基本的数据添加,遍历等基本常用方法
 *
 * 作业: 实现与Map类似的功能
 */
public class Topic2<K,V> extends AbstractCase<K,V>{
    private K key;
    private List<V> valList;

    public Topic2() {
        this.valList = new ArrayList<V>();
    }

    public static <K,V> Topic2<K,V> of(){
        return new Topic2<>();
    }

    public void add(K key, V val){
        if (null != this.key){
            this.valList.add(val);
        }else {
            this.key = key;
            this.valList.add(val);
        }
    }

    public void ergodic(K key,V val){
        if (null != this.key){
            for (V val2 : valList){
                System.out.println(val2);
            }
            System.out.println(this.key);
        }
    }
}
