package homework.day4;

import homework.day3.ArrayList;

import javax.security.sasl.SaslClient;
import java.util.*;

/**
 * 自测下ArrayList与LinkedList在你本地的机器上的性能
 * 主要是验证 LinkedList是不是所有数据都是删除和修改最快
 * @Author myy
 * @create 2019/6/24 21:44
 */
public class Topic1 {
    public static void main(String[] args) {
        List<Integer> arrayList = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();
        int num = 50000;
        int replaceCount = 50000;
        int removeCount = 5000;
        int randomAdd = 5000;
        System.out.println("ArrayList首部添加耗时:" + testAddFirst(arrayList,num));
        System.out.println("LinkedList首部添加耗时:" + testAddFirst(linkedList,num));
        System.out.println("ArrayList尾部添加耗时:" + testAddLast(arrayList,num));
        System.out.println("LinkedList尾部添加耗时:" + testAddLast(linkedList,num));
        System.out.println("ArrayList随机替换耗时:" + testReplaceRandom(arrayList,replaceCount,num));
        System.out.println("LinkedList随机替换耗时:" + testReplaceRandom(linkedList,replaceCount,num));
        System.out.println("ArrayList随机删除耗时:" + testRemoveRandom(arrayList,removeCount,num));
        System.out.println("LinkedList随机删除耗时:" + testRemoveRandom(linkedList,removeCount,num));
        System.out.println("ArrayList随机添加耗时:" + testAddRandom(arrayList,randomAdd,num));
        System.out.println("LinkedList随机添加耗时:" + testAddRandom(linkedList,randomAdd,num));
    }
    public static long testAddFirst(List list,int addCount){
        long time1= System.currentTimeMillis();
        for (int i = 0; i < addCount; i++) {
            list.add(0,i);
        }
        long time2 = System.currentTimeMillis();
        long interval = time2 - time1;
        System.out.println("首部添加后元素个数" + list.size());
        list.clear();
        return interval;
    }
    public static long testAddLast(List list,int addCount){
        long time1= System.currentTimeMillis();
        for (int i = 0; i < addCount; i++) {
            list.add(i);
        }
        long time2 = System.currentTimeMillis();
        long interval = time2 - time1;
        System.out.println("尾部添加后元素个数" + list.size());
        list.clear();
        return interval;
    }
    public static long testAddRandom(List list,int addCount,int num){
        int index = (int) (Math.random()*num);
        for (int j = 0; j < num; j++) {
            list.add(j);
        }
        long time1 = System.currentTimeMillis();
        for (int i = 0; i < addCount ; i++) {
            list.add(index,i);
        }
        long time2 = System.currentTimeMillis();
        long interval = time2 - time1;
        System.out.println("随机添加后元素个数" + list.size());
        return interval;
    }
    public static long testReplaceRandom(List list,int replaceCount,int num){
        int index = (int) (Math.random()*num);
        for (int j = 0; j < num; j++) {
            list.add(j);
        }
        long time1 = System.currentTimeMillis();
        for (int i = 0; i < replaceCount ; i++) {
            list.set(index,index+1);
        }
        long time2 = System.currentTimeMillis();
        long interval = time2 - time1;
        System.out.println("随机替换后列表元素个数"+list.size());
        list.clear();
        return interval;
    }
    public static long testRemoveRandom(List list,int removeCount, int num){
        int index = (int) (Math.random()*num);
        for (int j = 0; j < num; j++) {
            list.add(j);
        }
        long time1 = System.currentTimeMillis();
        for (int i = 0; i < removeCount ; i++) {
            list.remove(index);
        }
        long time2 = System.currentTimeMillis();
        long interval = time2 - time1;
        System.out.println("随机删除后列表元素个数"+ list.size());
        list.clear();
        return interval;
    }
}
