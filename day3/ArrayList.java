package homework.day3;

import java.util.*;

/**
 * @Author myy
 * @create 2019/6/17 22:07
 */
/**ArrayList是一个容量能够动态增长的动态数组。它继承了AbstractList，实现了List,
 * RandomAccess,Cloneable,java.io.Serializable。
 */
public class ArrayList<E> extends AbstractList<E>
        implements List<E>, RandomAccess, Cloneable, java.io.Serializable
{
    /**
     * 序列版本号
     */
    private static final long serialVersionUID = 8683452581122892189L;
    /**
     * 默认容量大小
     */
    private static final int DEFAULT_CAPACITY = 10;
    /**
     * 空数组
     */
    private static final Object[] EMPTY_ELEMENTDATA = {};
    /**
     * 与空数组区分开来的一个用来了解添加第一个元素时要inflate多少
     */
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};
    /**
     * 用于保存ArrayList中数据的数组
     * transient是类型修饰符，只能用来修饰字段。在对象序列化的过程中，
     * 标记为transient的变量不会被序列化
     * 非私有以简化嵌套类访问
     */
    transient Object[] elementData;
    /**
     * ArrayList中所包含元素的个数
     */
    private int size;
    /**
     * 带初始容量参数的构造函数
     */
    public ArrayList(int initialCapacity){
        if (initialCapacity > 0){
            this.elementData = new Object[initialCapacity];
        }else if (initialCapacity == 0){
            this.elementData = EMPTY_ELEMENTDATA;
        }else {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
    }
    /**
     * 构造初始容量为10的空列表。
     */
    public ArrayList(){
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }
    /**
     * 带Collection参数的构造方法
     * 构造一个包含指定collection的元素的列表，这些元素是按照该collection
     * 的迭代器返回他们的顺序排列的
     */
    public ArrayList(Collection<? extends E> c){
        elementData = c.toArray();
        if ((size = elementData.length) != 0){
            // c.toArray might (incorrectly) not return Object[] (see 6260652)
            //List<String> list = new ArrayList<>(Arrays.asList("list"));
            //Object[] listArray = list.toArray();
            // 通过ArrayList的toArray()传过来的数组一定是Object[]类型的
            //但是构造函数接受的参数是Collection<? extends E> c，List<String> asList = Arrays.asList("asList");
            //其中Arrays.asList（）是将数组转换成list集合的一种方法，其满足形参的要求，
            // 但是其内部的toArray()方法却与ArrayList的不同，其没有保证传递出的数组
            // 一定是Object[]类型,这样就导致了Object[]的引用指向了String[]的情况,会引起某些问题
            //虽然elementData 是object[]类型的，但是它指向的类型不一定是Object[]，所以还要进行判断。
            if (elementData.getClass() != Object[].class){
                elementData = Arrays.copyOf(elementData, size, Object[].class);
            }
        }else {
            //replace with empty array
            this.elementData = EMPTY_ELEMENTDATA;
        }
    }
    /**
     * 将此 ArrayList 实例的容量调整为列表的当前大小(实际元素个数)
     */
    public void trimToSize(){
        modCount++;
        if (size < elementData.length) {
            elementData = (size == 0) ? EMPTY_ELEMENTDATA : Arrays.copyOf(elementData, size);
        }
    }
    /**
     * 如有此必要，增加此ArrayList实例的容量，以确保它至少能够容纳最小容量参数所指定的元素数
     * 此处不是特别理解？？？？？？？？？？？？？？？？？
     */
    public void ensureCapacity(int minCapacity){
        int minExpand = (elementData != DEFAULTCAPACITY_EMPTY_ELEMENTDATA) ? 0 : DEFAULT_CAPACITY;
        if (minCapacity > minExpand){
            ensureExplicitCapacity(minCapacity);
        }
    }

    private void ensureCapacityInternal(int minCapacity){
        if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA){
            minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);
        }
        ensureExplicitCapacity(minCapacity);
    }

    private void ensureExplicitCapacity(int minCapacity){
        modCount++;
        if (minCapacity - elementData.length > 0){
            grow(minCapacity);
        }
    }
    /**
     * 要分配的数组的最大大小。
     * 一些虚拟机在数组中保留一些头字。
     * 尝试分配较大的数组可能会导致utOfMemoryError:
     * 请求的数组大小超过了VM限制
     */
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
    /**
     * 增加容量以确保它至少能容纳最小容量参数指定的元素数。
     * 位运算符 有时间练习一下
     * Arrays 类 记得熟悉一下相关方法
     */
    private void grow(int minCapacity){
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - minCapacity < 0){
            newCapacity = minCapacity;
        }
        if (newCapacity - MAX_ARRAY_SIZE > 0){
            newCapacity = hugeCapacity(minCapacity);
        }
        elementData = Arrays.copyOf(elementData, newCapacity);
    }
    /**
     * 最大容量
     */
    private static int hugeCapacity(int minCapacity){
        if (minCapacity < 0){
            throw new OutOfMemoryError();
        }
        return (minCapacity > MAX_ARRAY_SIZE) ? Integer.MAX_VALUE : MAX_ARRAY_SIZE;
    }
    /**
     * 返回列表中元素的个数
     */
    public int size(){
        return size;
    }
    /**
     * 如果列表没有任何元素返回true
     */
    public boolean isEmpty(){
        return size == 0;
    }
    /**
     * 如果列表中包含至少一个指定的元素时，返回true
     */
    public boolean contains(Object o){
        return indexOf(o) >= 0;
    }
    /**
     * 如果列表中包含指定元素，返回第一次出现时的索引，不包含返回-1
     */
    public int indexOf(Object o){
        if (o == null){
            for (int i = 0; i < size; i++){
                if (elementData[i] == null){
                    return i;
                }
            }
        }else {
            for (int i = 0; i < size; i++){
                if (o.equals(elementData[i])){
                    return i;
                }
            }
        }
        return -1;
    }
    /**
     * 如果列表中包含指定元素，返回最后一次出现时的索引，不包含返回-1
     */
    public int lastIndexOf(Object o){
        if (o == null){
            for (int i = size - 1; i >= 0 ; i--){
                if (elementData[i] == null){
                    return i;
                }
            }
        }else {
            for (int i = size -1; i >= 0; i--){
                if (o.equals(elementData[i])){
                    return i;
                }
            }
        }
        return -1;
    }
    /**
     * 返回实例的浅copy，不会复制元素本身
     * v???  v.elementData???
     */
    public Object clone(){
        try{
            ArrayList<?> v = (ArrayList<?>) super.clone();
            v.elementData = Arrays.copyOf(elementData, size);
            v.modCount = 0;
            return v;
        }catch (CloneNotSupportedException e){
            //这不应该发生，因为我们是克隆的
            throw new InternalError(e);
        }
    }
    /**
     * 按适当顺序（从第一个到最后一个元素）返回包含此列表中所有元素的数组
     */
    public Object[] toArray(){
        return Arrays.copyOf(elementData, size);
    }
    /**
     * 返回ArrayList的模板数组。所谓模板数组，即可以将T设为任意的数据类型
     * 此处对两处比较不是特别理解
     */
    public <T> T[] toArray(T[] a){
        if (a.length < size){
            //native静态方法arraycopy(),可以使用这个方法来实现数组之间的复制。对于一维数组来说，这种复制属性值传递，
            // 修改副本不会影响原来的值。对于二维或者一维数组中存放的是对象时，复制结果是一维的引用变量传递给副本的
            // 一维数组，修改副本时，会影响原来的数组。
            return (T[]) Arrays.copyOf(elementData, size, a.getClass());
        }
        System.arraycopy(elementData,0, a, 0,size);
        if (a.length > size){
            a[size] = null;
        }
        return a;
    }

    /**
     * 位置访问操作
     */
    @SuppressWarnings("unchecked")
    E elementData(int index){
        return (E) elementData[index];
    }
    /**
     * 返回ArrayList中指定位置上的元素
     * elementData(index)  index用（）？？？不是应该用[]吗
     */
    public E get(int index){
        rangeCheck(index);
        return elementData(index);
    }
    /**
     * 用指定的元素替代ArrayList中指定位置上的元素，并返回替代前的元素
     */
    public E set(int index,E element){
        rangeCheck(index);

        E oldValue = elementData(index);
        elementData[index] = element;
        return oldValue;
    }
    /**
     * 将指定的元素添加到ArrayList的尾部
     */
    public boolean add(E e){
        ensureCapacityInternal(size + 1);
        elementData[size++] = e;
        return true;
    }
    /**
     * 将指定的元素插入ArrayList中的指定位置
     */
    public void add(int index, E element){
        rangeCheckForAdd(index);

        ensureCapacityInternal(size + 1);
        System.arraycopy(elementData, index, elementData, index+1, size - index);
        elementData[index] = element;
        size++;
    }
}
