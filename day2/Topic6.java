package homework;
/**
 * 作业6: 使用数组实现一个栈,元素整型即可
 *
 * 要求:
 *
 * - 实现入栈,enqueue
 *
 * - 实现出栈,dequeue
 *
 * - 实现获取栈顶元素值,getFront
 *
 * - 实现获取栈的大小
 *
 * - 实现判断栈是否为空
 *
 * @author haoc
 */
//public class Topic6 {
//
//}
//
///**
// * 例如
// */
//class IntStack {
//
//    void enqueue(int element) {
//
//    }
//
//    /**
//     * 这个是取出栈顶元素,栈中就没有这个元素了
//     */
//    int dequeue() {
//        return -1;
//    }
//
//    /**
//     * 获取栈顶元素,意味着只是读取,这个元素还还会存储在栈中
//     */
//    int getFront() {
//        return -1;
//    }
//
//    int length() {
//        return -1;
//    }
//
//    boolean isEmpty() {
//        return false;
//    }
//
//public class Topic6{
//    private int[] arrInt;
//    private int top;
//    private int size;
//    public Topic6(int size){
//        this.arrInt = new int[size];
//        this.top = -1;
//    }
//    /**
//     * 判断栈是否为空
//     */
//    public boolean isEmpty(){
//        return -1 == top;
//    }
//    /**
//     * 判断栈是否已满
//     */
//    public boolean isFull(){
//        return arrInt.length -1 == top;
//    }
//    /**
//     * 实现入栈
//     */
//    public void enqueue(int element){
//        if (isFull()){
//            throw new RuntimeException("栈已满");
//        }
//        arrInt[++top] = element;
//    }
//    /**
//     * 获取栈顶元素
//     */
//    public int getFront(){
//        if (isEmpty()){
//            throw new RuntimeException("栈为空");
//        }
//        return arrInt[top--];
//    }
//    /**
//     * 实现获取栈的大小
//     */
//    public int length(){
//        return this.size;
//    }
//    /**
//     * 出栈
//     */
//    public int pop(){
//        if (isEmpty()){
//            throw new RuntimeException("栈为空");
//        }
//        //整数数组的值怎么赋值为null？
//        Integer i= null;
//        arrInt[top] = i;
//        return getFront();
//    }
//}
public class Topic6{
    /**
     *定义栈的长度
     */
    private int length;
    /**
     *定义一个整型数组
     */
    private int[] array;
    /**
     * 指向栈顶的指针(下标)
     */
    private int point;
    //初始化
    public Topic6(int length){
        this.length = length;
        //默认为-1，空栈
        point = -1;
        array = new int[length];
    }
    //入栈
    public void equeue(int eq) throws Exception{
        if(point == array.length -1){
            throw new Exception("栈满了");
        }
        array[++point] = eq;
    }
    //获取栈顶项
    public int getFront() throws Exception{
        if (point == -1){
            throw new Exception("栈空了");
        }
        return array[point--];
    }
    //出栈
    public int pop() throws Exception{
        if (isEmpty()){
            throw new RuntimeException("栈为空");
        }
        //整数数组的值怎么赋值为null？
        Integer i= null;
        array[point] = i;
        return getFront();
    }
    /**
     * 判断是否为空
     */
    public boolean isEmpty(){
        return -1 == point;
    }
    /**
     * 打印栈中数据
     */
    public void out(){
        for (int e: array){
            System.out.println(e);
        }
    }
    public int getLength(){
        return this.length;
    }
}