package homework;

public class DynamicArray<E> {
    private E[] data;
    /**
     * 数组中元素的个数
     */
    private int size;
    /**
     * 有参构造函数，根据传入参数构造数组的容量
     */
    public DynamicArray(int cap){
        this.data = (E[]) new Object[cap];
        this.size = 0;
    }
    /**
     * 无参构造函数，默认数组的容量为10
     */
    public DynamicArray(){
        this(10);
    }
    /**
     * 获取数组的容量
     */
    public int getCapacity(){
        return this.data.length;
    }
    //获取数组中元素的个数
    public int getSize(){
        return this.size;
    }
    //判断数组是否为空
    public boolean isEmpty(){
        return this.size == 0;
    }
    /**
     * 往数组中添加元素
     * @param index:索引
     * @param e:添加的元素
     */
    public void add(int index,E e){
        if (index<0||index>this.size){
            throw new IllegalArgumentException("添加失败，插入的索引出错！");
        }
        //扩容
        if (this.size == this.data.length){
            this.resize(this.getCapacity()*2);
        }
        for (int i=this.size;i>index;i--){
            data[i] = data[i-1];
        }
        this.data[index] = e;
        this.size++;
    }
    //往数组首位置添加元素
    public void addFist(E e){
        this.add(0,e);
    }
    //在所有数组元素后添加元素
    public void addLast(E e){
        this.add(this.size,e);
    }
    //获取index位置的元素
    public E getElement(int index){
        if (index<0 || index >= this.size){
            throw new IllegalArgumentException("getElement-->index出错!");
        }
        return this.data[index];
    }
    //修改index位置的元素
    public void setElement(int index,E e){
        if(index<0 || index>=this.size){
            throw new IllegalArgumentException("setElement-->index出错！");
        }
        this.data[index]=e;
    }
    //查找元素e是否在数组中，返回索引
    public int find(E e){
        for (int i=0;i<this.size;i++){
            if (this.data[i] == e){
                return i;
            }
        }
        return -1;
    }
    //查看数组是否包含该元素
    public boolean contains(E e){
        return this.find(e) != -1;
    }
    //删除index元素，并返回删除元素
    public E remove(int index){
        if (index<0 || index>=this.size){
            throw new IllegalArgumentException("删除失败-->index出错！");
        }
        E res = this.data[index];
        for(int i = index;i<this.size-1;i++){
            this.data[i] = this.data[i+1];
        }
        this.size--;
        this.data[size] = null;
        if (this.size <= this.data.length/2){
            this.resize(this.data.length/2);
        }
        return res;
    }
    //删除数组第一个元素
    public E removeFirst(){
        return this.remove(0);
    }
    //删除数组最后一个元素
    public E removeLast(){
        return this.remove(this.size-1);
    }
    //将数组空间的容量变成newCap大小
    private void resize(int newCap){
        E[] newData = (E[]) new Object[newCap];
        for(int i =0; i < size; i++){
            newData[i] = this.data[i];
        this.data = newData;
        }
    }
    @Override
    public String toString(){
        StringBuffer sb = new StringBuffer();
        sb.append(String.format("Array:size = %d,cap = %d\n",size,data.length));
        sb.append('[');
        for (int i=0;i<this.size;i++){
            sb.append(this.data[i]);
            if (i!=this.size -1){
                sb.append(", ");
            }
        }
        sb.append(']');
        return sb.toString();
    }
}


