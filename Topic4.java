package homework;


import java.util.Arrays;

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
public class Topic4{
    private int[] myarray;
    /**
     自定义数组类的元素个数
     */
    private int cnt;
    /**
     使用自定义类封装数组，添加类方法实现数据操作
     */
    public Topic4(){
        myarray = new int[50];
    }
    public Topic4(int size){
        myarray = new int[size];
    }
    public void insertnum(int innum){
        myarray[cnt++] = innum;
    }
    public int deleteByNum(int delnum){
        int deleteResult;
        int i;
        for(i = 0; i<cnt;i++){
            if(myarray[i] == delnum){
                int j;
                for(j = i;j < cnt-1;++j){
                    myarray[j] = myarray[j+1];
                }
                myarray[j] = myarray[--cnt];
                break;
            }
        }
        if (cnt ==i){
            deleteResult = -1;
        }else {
            deleteResult = i;
        }
        return deleteResult;
    }
    /**
     *按值查找数据，返回索引值
     *算法：线性查找
     */
    public int searchNum(int targetNum){
        int i;
        int searchResult;
        for(i=0;i<cnt;++i){
            if (targetNum==myarray[i]){
                break;
            }
        }
        if (i == cnt){
            searchResult = -1;
        }else {
            searchResult = i;
        }
        return searchResult;
    }
    public static void main(String[] args) {
        Topic4 t4 = new Topic4();
        t4.myarray = new int[]{1,3,5,7,9};
        int cnt = 5;
        System.out.println(Arrays.toString(t4.myarray));
        t4.insertnum(10);
        System.out.println(t4.myarray[5]);

    }
}
