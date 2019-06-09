package homework;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * 作业4: 封装一个数组对象(暂时用int型即可)
 *
 * 可先定义个数组看下,比如int[],有哪些方法提供,太少了
 *
 * 要求实现: 数组类
 *
 * - new 时传入指定大小
 *
 * - 可以添加元素
 *
 * - 可以删除元素
 *
 * - 可以根据给定元素,找到索引位置
 *
 * - 并支持跨度寻找,如find(1234,5),即夸过5个索引之后寻找
 *
 * - 实现对数组的遍历功能
 *
 * - 实现判断数组是否为空
 *
 * - 不理解,看下Topic6,栈的设计
 *
 * @author haoc
 */
//public class Topic4 {
//    public String arr1(int[] arr,int a){
//        int[] arr1 = Arrays.copyOf(arr,arr.length+1);
//        arr1[-1] = a;
//        String narr1 = Arrays.toString(arr1);
//        ArrayList.add
//        return narr1;
//    }
//
//    public static void main(String[] args) {
//        Topic4 t4 = new Topic4();
//        int[] arr = {1,4,9,5};
//        System.out.println(t4.arr1(arr,10));
//    }
//}
//public class Topic4{
//    private int[] myarray;
//    /**
//     自定义数组类的元素个数
//     */
//    private int cnt;
//    /**
//     使用自定义类封装数组，添加类方法实现数据操作
//     */
//    public Topic4(){
//        myarray = new int[50];
//    }
//    public Topic4(int size){
//        myarray = new int[size];
//    }
//    /**
//     插入数据，返回值为空
//     */
//    public void insertnum(int innum){
//        myarray[cnt++] = innum;
//    }
//    /**
//     删除数据，返回索引
//    */
//    public int deleteByNum(int delnum){
//        int deleteResult;
//        int i;
//        for(i = 0; i<cnt;i++){
//            if(myarray[i] == delnum){
//                int j;
//                for(j = i;j < cnt-1;++j){
//                    myarray[j] = myarray[j+1];
//                }
//                myarray[j] = myarray[--cnt];
//                break;
//            }
//        }
//        if (cnt ==i){
//            deleteResult = -1;
//        }else {
//            deleteResult = i;
//        }
//        return deleteResult;
//    }
//    /**
//     *按值查找数据，返回索引值
//     *算法：线性查找
//     */
//    public int searchNum(int targetNum){
//        int i;
//        int searchResult;
//        for(i=0;i<cnt;++i){
//            if (targetNum==myarray[i]){
//                break;
//            }
//        }
//        if (i == cnt){
//            searchResult = -1;
//        }else {
//            searchResult = i;
//        }
//        return searchResult;
//    }
//    public static void main(String[] args) {
//        Topic4 t4 = new Topic4();
//        t4.myarray = new int[]{1,3,5,7,9};
//        int cnt = 5;
//        System.out.println(Arrays.toString(t4.myarray));
//        t4.insertnum(10);
//        System.out.println(t4.myarray[5]);
//
//    }
//}
//public class Topic4{
////    private static final Object[] EMPTY_ELEMENTDATA = {};
////    transient Object[] elementData;
////    public Topic4(int initialCapacity){
////        if (initialCapacity >0){
////            this.elementData = new Object[initialCapacity];
////        }else if (initialCapacity == 0){
////            this.elementData = EMPTY_ELEMENTDATA;
////        }else {
////            throw new IllegalArgumentException("Illegal Capacity" + initialCapacity);
////        }
////    }
////}
public class Topic4{
    /**
     * 定义一个初始化为空的整型数据
     */
    private static final int[] EMPTY_ELEMENTDATA = {};
    /**
    * transient在采用Java默认的序列化机制的时候，被该关键字修饰的属性不会被序列化。
    */
    transient int[] elementData;
    /**
     * elementData中已存放的元素的个数，注意：不是elementData的容量
     */
    private int size;
    //自带数组长度的构造方式
    public Topic4(int initialCapacity){
        if (initialCapacity > 0){
            this.elementData = new int[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementData = EMPTY_ELEMENTDATA;
        }else {
            throw new IllegalArgumentException("Illegal Capacity" + initialCapacity);
        }
    }
    /**
     * 向elementData中添加元素
     * ensureCapacity 确保对象数组有足够的容量，可以将新元素加进去
     * 加入新元素i，size加1
     */
    public boolean add(int i){
        ensureCapacity(size + 1);
        elementData[size++] = i;
        return true;
    }
    /**
     * 确保数组的容量足够存放新加入的元素，若不够，要扩容
     *
     */
    public void ensureCapacity(int minCapacity){
        //获取数组大小
        int oldCapacity = elementData.length;
        if(minCapacity>oldCapacity){
             int[] aint = elementData;
             //新容量为旧容量的1.5倍+1
             int newCapacity = (oldCapacity*3)/2 + 1;
             //扩容后的新容量还是没有传入的所需的最小容量大或等于
             if (newCapacity < minCapacity){
                 //新容量设为最小容量
                 newCapacity = minCapacity;
                 //复制新容量
                 elementData = Arrays.copyOf(elementData,newCapacity);
             }
        }
    }
    /**
     * 删除指定索引index下的元素，返回被删除的元素
     */
    public int remove(int index){
        //检查索引范围
        RangeCheck(index);
        //被删除的元素
        int oldValue = elementData[index];
        fastRemove(index);
        return oldValue;
    }
    /**
     *检查索引index是否超出size-1
     */
    private void RangeCheck(int index){
        if (index>=size){
            throw new IndexOutOfBoundsException("Index:"+index+",Size:"+size);
        }
    }
    /**
     *删除单个位置的元素，是ArrayList的私有方法
     */
    private void fastRemove(int index){
        int numMoved = size - index - 1;
        if (numMoved>0){
            //删除的元素到最后的元素整块前移
            System.arraycopy(elementData, index+1,elementData,index,numMoved);
            //把最后一个元素设为null
        }
    }
    /**
     * 判断动态数组是否包含元素，并返回第一个出现的索引位置，最后一个出现的索引
     */
    public boolean contains(int target){
        return indexOf(target) >= 0;
    }
    /**
     * 返回第一个出现的元素的索引位置
     */
    public int indexOf(int target){
        for (int i = 0;i<size;i++){
            if (target == elementData[i]){
                return i;
            }
        }
        return -1;
    }
    /**
     * 返回最后一个出现的元素o的索引位置    https://www.cnblogs.com/java-zhao/p/5102342.html
     */
    /**
     * 遍历ArrayList中的对象
     */
//    ArrayList list = new ArrayList();
//    for(Object obj:list){
//        System.out.println(obj);
//    }
//    void catList(List<Integer> list){
//        List<Integer> list1 = new ArrayList<>();
//        for(int i=0;i<5;i++){
//            list1.add(i);
//        }
//        Iterator<Integer> iterator = list1.iterator();
//        while (iterator.hasNext()){
//            int i = (int) iterator.next();
//            System.out.println(i+", ");
//        }
//        System.out.println("\n" + list1);
//    }
    public static void main(String[] args) {
        Topic4 tp4 = new Topic4(5);
        tp4.add(5);
        tp4.add(1);
        tp4.add(2);
        tp4.add(3);
        tp4.add(4);
        tp4.remove(4);
        tp4.contains(5);
    }
}