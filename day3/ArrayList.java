package homework.day3;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

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
    @Override
    public int size(){
        return size;
    }
    /**
     * 如果列表没有任何元素返回true
     */
    @Override
    public boolean isEmpty(){
        return size == 0;
    }
    /**
     * 如果列表中包含至少一个指定的元素时，返回true
     */
    @Override
    public boolean contains(Object o){
        return indexOf(o) >= 0;
    }
    /**
     * 如果列表中包含指定元素，返回第一次出现时的索引，不包含返回-1
     */
    @Override
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
    @Override
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
    @Override
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
    @Override
    public Object[] toArray(){
        return Arrays.copyOf(elementData, size);
    }
    /**
     * 返回ArrayList的模板数组。所谓模板数组，即可以将T设为任意的数据类型
     * 此处对两处比较不是特别理解
     */
    @Override
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
     * 这里是将数组中的元素指定为特定类型
     * ********************************
     */
    @SuppressWarnings("unchecked")
    E elementData(int index){
        return (E) elementData[index];
    }
    /**
     * 返回ArrayList中指定位置上的元素
     * elementData(index)  index用（）？？？不是应该用[]吗
     */
    @Override
    public E get(int index){
        rangeCheck(index);
        return elementData(index);
    }
    /**
     * 用指定的元素替代ArrayList中指定位置上的元素，并返回替代前的元素
     */
    @Override
    public E set(int index,E element){
        rangeCheck(index);

        E oldValue = elementData(index);
        elementData[index] = element;
        return oldValue;
    }
    /**
     * 将指定的元素添加到ArrayList的尾部
     */
    @Override
    public boolean add(E e){
        ensureCapacityInternal(size + 1);
        elementData[size++] = e;
        return true;
    }
    /**
     * 将指定的元素插入ArrayList中的指定位置
     */
    @Override
    public void add(int index, E element){
        rangeCheckForAdd(index);
        //copyOf()的实现是用arraycopy(),arraycopy需要目标数组，对两个数组的内容进行可能不完全的合并
        //copyOf在内部新建一个数组
        ensureCapacityInternal(size + 1);
        System.arraycopy(elementData, index, elementData, index+1, size - index);
        elementData[index] = element;
        size++;
    }
    /**
     * 移除ArrayList中指定位置上的元素，并返回该位置上的元素
     */
    @Override
    public E remove(int index){
        rangeCheck(index);
        modCount++;
        E oldValue = elementData(index);
        int numMoved = size - index - 1;
        if (numMoved > 0){
            System.arraycopy(elementData, index+1, elementData, index, numMoved);
        }
        //clear to let GC do its work
        elementData[--size] = null;
        return oldValue;
    }
    /**
     * 移除ArrayList中首次出现的指定元素（如果存在则移除并返回true，否则返回false）
     */
    @Override
    public boolean remove(Object o){
        if (o == null){
            for (int index = 0 ; index < size; index++){
                if (elementData[index] == null){
                    fastRemove(index);
                    return true;
                }
            }
        }else {
            for (int index = 0;index < size; index++){
                if (o.equals(null)) {
                    fastRemove(index);
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * 私有方法，快速移除
     */
    private void fastRemove(int index){
        modCount++;
        int numMoved = size - index - 1;
        if (numMoved > 0){
            System.arraycopy(elementData, index + 1, elementData, index, numMoved);
        }
        elementData[--size] = null;
    }
    /**
     * 移除ArrayList中的所有元素
     */
    @Override
    public void clear(){
        modCount++;
        for (int i = 0 ; i < size ; i++){
            elementData[i] = null;
        }
        size = 0;
    }
    /**
     * 按照指定collection的迭代器返回的元素顺序，将
     * 该collection中的所有元素添加到ArrayList的尾部
     */
    @Override
    public boolean addAll(Collection<? extends E> c){
        Object[] a = c.toArray();
        int numNew = a.length;
        //increments modCount
        ensureCapacityInternal(size + numNew);
        System.arraycopy(a, 0, elementData, size, numNew);
        size += numNew;
        return numNew != 0;
    }
    /**
     * 从指定的位置开始，将指定 collection 中的所有元素插入到ArrayList中
     */
    @Override
    public boolean addAll(int index, Collection<? extends E> c){
        rangeCheckForAdd(index);

        Object[] a = c.toArray();
        int numNew = a.length;
        ensureCapacityInternal(size + numNew);

        int numMoved = size - index;
        if (numMoved > 0){
            System.arraycopy(elementData, size, elementData, size+numMoved, numMoved);
        }
        System.arraycopy(a, 0, elementData, index, numNew);
        size += numNew;
        return numNew != 0;
    }
    /**
     * 移除列表中索引在 fromIndex（包括）和 toIndex（不包括）之间的所有元素
     */
    @Override
    protected void removeRange(int fromIndex, int toIndex){
        modCount++;
        int numMoved = size - toIndex;
        System.arraycopy(elementData, toIndex, elementData, fromIndex, numMoved);
        int newSize = size - (toIndex - fromIndex);
        for(int i = newSize; i < size ; i++){
            elementData[i] = null;
        }
        size = newSize;
    }
    /**
     * 私有方法，用于范围检测
     */
    private void rangeCheck(int index){
        if (index >= size){
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }
    /**
     * 私有方法，用于add和addAll
     */
    private void rangeCheckForAdd(int index){
        if (index > size || index < 0){
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }
    /**
     * 构造异常的详细信息消息
     */
    private String outOfBoundsMsg(int index){
        return "Index: "+ index + ",Size: "+ size;
    }
    /**
     * 移除ArrayList中Collection所包含的所有元素
     */
    @Override
    public boolean removeAll(Collection<?> c){
        return batchRemove(c, false);
    }
    /**
     * 保留所有ArrayList和Collection共有的元素
     */
    @Override
    public boolean retainAll(Collection<?> c){
        return batchRemove(c, true);
    }
    /**
     * 转不过来弯，想不明白
     * removeAll complement == false; 所以参数数组中不包含原数组指定位置的数据时，就将原数组的r位置的数据覆盖掉
     * w位置的数据，r位置的数据不变，并其w自增，r自增。否则，r自增，w不自增
     * https://blog.csdn.net/weixin_40841731/article/details/85263889
     */
    private boolean batchRemove(Collection<?> c,boolean complement){
        final Object[] elementData = this.elementData;
        int r = 0, w = 0;
        boolean modified = false;
        try{
            for (; r < size; r++){
                if (c.contains(elementData[r]) == complement){
                    elementData[w++] = elementData[r];
                }
            }
        }finally {
            if (r != size){
                System.arraycopy(elementData, r, elementData, w, size - r);
                w += size - r;
            }
            if (w != size){
                for (int i = w; i < size; i++){
                    elementData[i] = null;
                }
                modCount += size - w;
                size = w;
                modified = true;
            }
        }
        return modified;
    }
    /**
     *  java.io.Serializable的写入函数
     *  将ArrayList的“容量，所有的元素值”都写入到输出流中
     *  java.util.ArrayList不是线程安全的，对其内容的修改都会增加modCount这个值
     *  在迭代器初始过程中把modCount赋给expectedModCount，迭代过程中，判断modCount跟
     *  expectedModCount是否相等，如果不相等说明已经有其他线程修改了ArrayList
     *  Fail-Fast机制
     */
    private void writeObject(java.io.ObjectOutputStream s) throws java.io.IOException{
        //Write out element count, and any hidden stuff
        int expectedModCount = modCount;
        //默认的序列化方式 此方法写入当前类的非静态和非瞬态字段写入此流
        s.defaultWriteObject();
        //Write out size as capacity for behavioural compatibility with clone()
        //writeInt() 把整个32位都写入输出流
        s.writeInt(size);
        // Write out all elements in the proper order.
        for (int i=0; i<size; i++){
            s.writeObject(elementData[i]);
        }
        if (modCount != expectedModCount){
            throw new ConcurrentModificationException();
        }
    }
    /**
     * java.io.Serializable的读取函数：根据写入方式读出
     * 先将ArrayList的“容量”读出，然后将“所有的元素值”读出
     */
    private void readObject(java.io.ObjectInputStream s) throws java.io.IOException, ClassNotFoundException{
        elementData = EMPTY_ELEMENTDATA;
        // Read in size, and any hidden stuff
        //此方法从该流中读取当前类的非静态和非瞬态字段。这也许只能称为从类反序列化readObject方法。
        s.defaultReadObject();
        //Read in capacity
        s.readInt();
        if (size > 0) {
            //与clone（）类似，根据大小而不是容量分配数组
            ensureCapacityInternal(size);

            Object[] a = elementData;
            //Read in all elements in the proper order
            for (int i=0; i<size; i++){
                a[i] = s.readObject();
            }
        }
    }
    /**
     * 返回一个从指定位置开始遍历的ListIterator迭代器
     */
    @Override
    public ListIterator<E> listIterator(int index){
        if (index < 0 || index > size){
            throw new IndexOutOfBoundsException("Index: "+ index);
        }
        return new ListItr(index);
    }
    /**
     * 返回一个ListIterator迭代器
     */
    @Override
    public ListIterator<E> listIterator(){
        return new ListItr(0);
    }
    /**
     * 返回一个迭代器
     */
    @Override
    public Iterator<E> iterator(){
        return new Itr();
    }
    /**
     * AbstractList.Itr的优化版本
     * Itr是AbstractList.Itr的优化版本
     * 为什么会报ConcurrentModificationException异常?
     * 1. Iterator 是工作在一个独立的线程中，并且拥有一个 mutex 锁。(互斥锁)
     * 2. Iterator 被创建之后会建立一个指向原来对象的单链索引表，当原来的对象数量发生变化时，
     * 这个索引表的内容不会同步改变，所以当索引指针往后移动的时候就找不到要迭代的对象，
     * 3. 所以按照 fail-fast 原则 Iterator 会马上抛出 java.util.ConcurrentModificationException 异常。
     * 4. 所以 Iterator 在工作的时候是不允许被迭代的对象被改变的。
     * 但你可以使用 Iterator 本身的方法 remove() 来删除对象，
     * 5. Iterator.remove() 方法会在删除当前迭代对象的同时维护索引的一致性。
     */
    private class Itr implements Iterator<E>{
        /**
         * 下一个要返回的元素的索引
         * 返回最后一个元素的索引；如果没有，则为-1
         * 用于检查线程是否同步，如果线程不同步，它们两个的值不一样
         */
        int cursor;
        int lastRet = -1;
        int expectedModCount = modCount;
        @Override
        public boolean hasNext(){
            return cursor != size;
        }
        @Override
        public E next(){
            checkForComodification();
            /**
             *  i当前元素的索引
             *  第一次检查，角标是否越界
             *  第二次检查，集合中的数量是否发生变化
             */
            int i = cursor;
            if (i >= size){
                throw new NoSuchElementException();
            }
            Object[] elementData = ArrayList.this.elementData;
            if (i>= elementData.length){
                throw new ConcurrentModificationException();
            }
            cursor =i + 1;
            //为什么要把i赋值给最后一个元素的索引
            return (E) elementData[lastRet = i];
        }
        @Override
        public void remove(){
            if (lastRet < 0){
                throw new IllegalStateException();
            }
            checkForComodification();
            //不理解
            //移除集合中的元素
            //把指针往回移动一位
            //最后一个元素返回的索引置为-1
            try {
                ArrayList.this.remove(lastRet);
                cursor = lastRet;
                lastRet = -1;
                expectedModCount = modCount;
            } catch (IndexOutOfBoundsException ex){
                throw new ConcurrentModificationException();
            }
        }
        /**
         *  这是Java 8为Iterator新增的默认方法，该方法可使用Lambda表达式来遍历集合元素。
         */
        @Override
        public void forEachRemaining(Consumer<? super E> consumer){
            //如果当前对象为空 会抛出一个空指针异常
            //否则就会返回当前对象
            Objects.requireNonNull(consumer);
            final int size = ArrayList.this.size;
            int i = cursor;
            if (i >= size){
                return;
            }
            final Object[] elementData = ArrayList.this.elementData;
            if (i >= elementData.length){
                throw new ConcurrentModificationException();
            }
            while (i != size && modCount == expectedModCount){
                consumer.accept((E) elementData[i++]);
            }
            //update once at end of iteration to reduce heap write traffic
            cursor = i;
            lastRet = i - 1;
            checkForComodification();
        }
        /**
         * 主要用来实现fail-fast机制，抛出异常
         */
        final void checkForComodification(){
            if (modCount != expectedModCount){
                throw new ConcurrentModificationException();
            }
        }
    }
    /**
     * 后面的就没特别仔细的研究啦
     * AbstractList.ListItr的优化版本
     * ListIterator 与普通的 Iterator 的区别：
     * 它可以进行双向移动，而普通的迭代器只能单向移动
     * 它可以添加元素(有 add() 方法)，而后者不行
     */
    public class ListItr extends Itr implements ListIterator<E>{
        ListItr(int index){
            super();
            cursor = index;
        }
        @Override
        public boolean hasPrevious() {return cursor != 0;}
        @Override
        public int nextIndex() {return cursor;}
        @Override
        public int previousIndex() {return cursor -1;}
        /**
         * 获取cursor前一个元素
         * 两次检查
         * cursor回移
         * 最终返回前一个元素
         */
        @Override
        public E previous(){
            checkForComodification();
            int i = cursor -1;
            if (i < 0){
                throw new NoSuchElementException();
            }
            Object[] elementData = ArrayList.this.elementData;
            if (i >= elementData.length){
                throw new ConcurrentModificationException();
            }
            cursor = i;
            return (E) elementData[lastRet = i];
        }

        @Override
        public void set(E e){
            if (lastRet < 0){
                throw new IllegalStateException();
            }
            checkForComodification();
            try{
                ArrayList.this.set(lastRet, e);
            }catch (IndexOutOfBoundsException ex){
                throw new ConcurrentModificationException();
            }
        }
        /**
         * 当前元素的索引后移一位
         * 在i位置上添加元素
         * cursor后移一位
         */
        @Override
        public void add(E e){
            checkForComodification();

            try{
                int i = cursor;
                ArrayList.this.add(i, e);
                cursor = i + 1;
                lastRet = -1;
                expectedModCount = modCount;
            }catch (IndexOutOfBoundsException ex){
                throw new ConcurrentModificationException();
            }
        }
    }
    /**
    * 获取从 fromIndex 到 toIndex 之间的子集合(左闭右开区间)
    * - 若 fromIndex == toIndex，则返回的空集合
     * - 对该子集合的操作，会影响原有集合
    * - 当调用了 subList() 后，若对原有集合进行删除操作(删除subList 中的首个元素)时，会抛出异常 java.util.ConcurrentModificationException
    *  这个和Itr的原因差不多由于modCount发生了改变，对集合的操作需要用子集合提供的方法
    * - 该子集合支持所有的集合操作
    */
    public List<E> sublist(int fromIndex, int toIndex) {
        subListRangeCheck(fromIndex, toIndex, size);
        return new SubList(this, 0, fromIndex, toIndex);
    }

    static void subListRangeCheck(int fromIndex, int toIndex, int size){
        if (fromIndex < 0){
            throw new IndexOutOfBoundsException("fromIndex = " + fromIndex);
        }
        if (toIndex > size){
            throw new IndexOutOfBoundsException("toIndex = " + toIndex);
        }
        if (fromIndex > toIndex){
            throw new IllegalArgumentException("fromIndex(" + fromIndex + ") > toIndex(" + toIndex + ")");
        }
    }

    private class SubList extends AbstractList<E> implements RandomAccess{
        private final AbstractList<E> parent;
        private final int parentOffset;
        private final int offset;
        int size;

        SubList(AbstractList<E> parent, int offset, int fromIndex, int toIndex){
            this.parent = parent;
            this.parentOffset = fromIndex;
            this.offset = offset + fromIndex;
            this.size = toIndex - fromIndex;
            this.modCount = ArrayList.this.modCount;
        }
        /**
         * 设置新值，返回旧值
         */
        @Override
        public E set(int index, E e) {
            rangeCheck(index);//越界检查
            checkForComodification();//检查
            //从这一条语句可以看出：对子类添加元素，是直接操作父类添加的
            E oldValue = ArrayList.this.elementData(offset + index);
            ArrayList.this.elementData[offset + index] = e;
            return oldValue;
        }

        /**
         * 获取指定索引的元素
         */
        @Override
        public E get(int index) {
            rangeCheck(index);
            checkForComodification();
            return ArrayList.this.elementData(offset + index);
        }

        /**
         * 返回元素的数量
         */
        @Override
        public int size() {
            checkForComodification();
            return this.size;
        }

        /**
         * 指定位置添加元素
         */
        @Override
        public void add(int index, E e) {
            rangeCheckForAdd(index);
            checkForComodification();
            //从这里可以看出，先通过index拿到在原来数组上的索引，再调用父类的添加方法实现添加
            parent.add(parentOffset + index, e);
            this.modCount = parent.modCount;
            this.size++;
        }
        /**
         * 移除指定位置的元素
         */
        @Override
        public E remove(int index) {
            rangeCheck(index);
            checkForComodification();
            E result = parent.remove(parentOffset + index);
            this.modCount = parent.modCount;
            this.size--;
            return result;
        }

        /**
         * 移除subList中的[fromIndex,toIndex)之间的元素
         */
        @Override
        protected void removeRange(int fromIndex, int toIndex) {
            checkForComodification();
            parent.removeRange(parentOffset + fromIndex,
                    parentOffset + toIndex);
            this.modCount = parent.modCount;
            this.size -= toIndex - fromIndex;
        }

        /**
         * 添加集合中的元素到subList结尾
         */
        @Override
        public boolean addAll(Collection<? extends E> c) {
            //调用父类的方法添加集合元素
            return addAll(this.size, c);
        }
        /**
         * 在subList指定位置,添加集合中的元素
         */
        @Override
        public boolean addAll(int index, Collection<? extends E> c) {
            rangeCheckForAdd(index);
            int cSize = c.size();
            if (cSize==0){
                return false;
            }
            checkForComodification();
            //调用父类的方法添加
            parent.addAll(parentOffset + index, c);
            this.modCount = parent.modCount;
            this.size += cSize;
            return true;
        }

        /**
         * subList中的迭代器
         */
        @Override
        public Iterator<E> iterator() {
            return listIterator();
        }

        /**
         * 返回从指定索引开始到结束的带有元素的list迭代器
         */
        @Override
        public ListIterator<E> listIterator(final int index) {
            checkForComodification();
            rangeCheckForAdd(index);
            final int offset = this.offset;

            return new ListIterator<E>() {
                int cursor = index;
                int lastRet = -1;
                int expectedModCount = ArrayList.this.modCount;
                @Override
                public boolean hasNext() {
                    return cursor != SubList.this.size;
                }
                @Override
                @SuppressWarnings("unchecked")
                public E next() {
                    checkForComodification();
                    int i = cursor;
                    if (i >= SubList.this.size){
                        throw new NoSuchElementException();
                    }
                    Object[] elementData = ArrayList.this.elementData;
                    if (offset + i >= elementData.length){
                        throw new ConcurrentModificationException();
                    }
                    cursor = i + 1;
                    return (E) elementData[offset + (lastRet = i)];
                }
                @Override
                public boolean hasPrevious() {
                    return cursor != 0;
                }
                @Override
                @SuppressWarnings("unchecked")
                public E previous() {
                    checkForComodification();
                    int i = cursor - 1;
                    if (i < 0){
                        throw new NoSuchElementException();
                    }
                    Object[] elementData = ArrayList.this.elementData;
                    if (offset + i >= elementData.length){
                        throw new ConcurrentModificationException();
                    }
                    cursor = i;
                    return (E) elementData[offset + (lastRet = i)];
                }
                //jdk8的方法
                @Override
                @SuppressWarnings("unchecked")
                public void forEachRemaining(Consumer<? super E> consumer) {
                    Objects.requireNonNull(consumer);
                    final int size = SubList.this.size;
                    int i = cursor;
                    if (i >= size) {
                        return;
                    }
                    final Object[] elementData = ArrayList.this.elementData;
                    if (offset + i >= elementData.length) {
                        throw new ConcurrentModificationException();
                    }
                    while (i != size && modCount == expectedModCount) {
                        consumer.accept((E) elementData[offset + (i++)]);
                    }
                    // update once at end of iteration to reduce heap write traffic
                    lastRet = cursor = i;
                    checkForComodification();
                }
                @Override
                public int nextIndex() {
                    return cursor;
                }
                @Override
                public int previousIndex() {
                    return cursor - 1;
                }
                @Override
                public void remove() {
                    if (lastRet < 0){
                        throw new IllegalStateException();
                    }
                    checkForComodification();

                    try {
                        SubList.this.remove(lastRet);
                        cursor = lastRet;
                        lastRet = -1;
                        expectedModCount = ArrayList.this.modCount;
                    } catch (IndexOutOfBoundsException ex) {
                        throw new ConcurrentModificationException();
                    }
                }
                @Override
                public void set(E e) {
                    if (lastRet < 0){
                        throw new IllegalStateException();
                    }
                    checkForComodification();

                    try {
                        ArrayList.this.set(offset + lastRet, e);
                    } catch (IndexOutOfBoundsException ex) {
                        throw new ConcurrentModificationException();
                    }
                }
                @Override
                public void add(E e) {
                    checkForComodification();

                    try {
                        int i = cursor;
                        SubList.this.add(i, e);
                        cursor = i + 1;
                        lastRet = -1;
                        expectedModCount = ArrayList.this.modCount;
                    } catch (IndexOutOfBoundsException ex) {
                        throw new ConcurrentModificationException();
                    }
                }

                final void checkForComodification() {
                    if (expectedModCount != ArrayList.this.modCount){
                        throw new ConcurrentModificationException();
                    }
                }
            };
        }
        /**
         * subList的方法，同样可以再次截取List同样是使用映射方式
         */
        @Override
        public List<E> subList(int fromIndex, int toIndex) {
            subListRangeCheck(fromIndex, toIndex, size);
            return new SubList(this, offset, fromIndex, toIndex);
        }

        private void rangeCheck(int index) {
            if (index < 0 || index >= this.size){
                throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
            }
        }

        private void rangeCheckForAdd(int index) {
            if (index < 0 || index > this.size){
                throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
            }
        }

        private String outOfBoundsMsg(int index) {
            return "Index: "+index+", Size: "+this.size;
        }

        private void checkForComodification() {
            if (ArrayList.this.modCount != this.modCount){
                throw new ConcurrentModificationException();
            }
        }
        /**
         * 这后面的一部分是真的没看啦。。。。
         * subList方法：获取一个分割器
         * - fail-fast
         * - late-binding：后期绑定
         * - java8 开始提供
         */
        @Override
        public Spliterator<E> spliterator() {
            checkForComodification();
            return new ArrayListSpliterator<E>(ArrayList.this, offset,
                    offset + this.size, this.modCount);
        }
    }
    @Override
    public void forEach(Consumer<? super E> action){
        Objects.requireNonNull(action);
        final int expectedMoodCount = modCount;

        final E[] elementData = (E[]) this.elementData;
        final int size = this.size;
        for (int i = 0; modCount == expectedMoodCount && i < size; i++){
            action.accept(elementData[i]);
        }
        if (modCount!=expectedMoodCount){
            throw new ConcurrentModificationException();
        }
    }
    @Override
    public Spliterator<E> spliterator(){
        return new ArrayListSpliterator<>(this, 0, -1, 0);
    }

    static final class ArrayListSpliterator<E> implements Spliterator<E>{
        private final ArrayList<E> list;
        private int index;
        private int fence;
        private int expectedModCount;
        ArrayListSpliterator(ArrayList<E> list, int origin, int fence, int expectedModCount){
            this.list =list;
            this.index = origin;
            this.fence = fence;
            this.expectedModCount = expectedModCount;
        }

        private int getFence(){
            int hi;
            ArrayList<E> lst;
            if ((hi = fence) < 0){
                if ((lst = list) == null){
                    hi = fence =0;
                }else {
                    expectedModCount = lst.modCount;
                    hi = fence = lst.size;
                }
            }
            return hi;
        }
        @Override
        public ArrayListSpliterator<E> trySplit(){
            int hi = getFence(), lo = index, mid = (lo + hi) >>> 1;
            return (lo >= mid) ? null: new ArrayListSpliterator<E>(list, lo,mid,expectedModCount);
        }
        @Override
        public boolean tryAdvance(Consumer<? super E> action) {
            if (action == null){
                throw new NullPointerException();
            }
            int hi = getFence(), i = index;
            if (i < hi) {
                index = i + 1;
                @SuppressWarnings("unchecked") E e = (E)list.elementData[i];
                action.accept(e);
                if (list.modCount != expectedModCount){
                    throw new ConcurrentModificationException();
                }
                return true;
            }
            return false;
        }
        @Override
        public void forEachRemaining(Consumer<? super E> action) {
            int i, hi, mc; // hoist accesses and checks from loop
            ArrayList<E> lst; Object[] a;
            if (action == null){
                throw new NullPointerException();
            }
            if ((lst = list) != null && (a = lst.elementData) != null) {
                if ((hi = fence) < 0) {
                    mc = lst.modCount;
                    hi = lst.size;
                } else{
                    mc = expectedModCount;
                }
                if ((i = index) >= 0 && (index = hi) <= a.length) {
                    for (; i < hi; ++i) {
                        @SuppressWarnings("unchecked") E e = (E) a[i];
                        action.accept(e);
                    }
                    if (lst.modCount == mc){
                        return;
                    }
                }
            }
            throw new ConcurrentModificationException();
        }
        @Override
        public long estimateSize() {
            return (long) (getFence() - index);
        }
        @Override
        public int characteristics() {
            return Spliterator.ORDERED | Spliterator.SIZED | Spliterator.SUBSIZED;
        }
    }
    @Override
    public boolean removeIf(Predicate<? super E> filter) {
        Objects.requireNonNull(filter);
        // figure out which elements are to be removed
        // any exception thrown from the filter predicate at this stage
        // will leave the collection unmodified
        int removeCount = 0;
        final BitSet removeSet = new BitSet(size);
        final int expectedModCount = modCount;
        final int size = this.size;
        for (int i=0; modCount == expectedModCount && i < size; i++) {
            @SuppressWarnings("unchecked")
            final E element = (E) elementData[i];
            if (filter.test(element)) {
                removeSet.set(i);
                removeCount++;
            }
        }
        if (modCount != expectedModCount) {
            throw new ConcurrentModificationException();
        }

        // shift surviving elements left over the spaces left by removed elements
        final boolean anyToRemove = removeCount > 0;
        if (anyToRemove) {
            final int newSize = size - removeCount;
            for (int i=0, j=0; (i < size) && (j < newSize); i++, j++) {
                i = removeSet.nextClearBit(i);
                elementData[j] = elementData[i];
            }
            for (int k=newSize; k < size; k++) {
                elementData[k] = null;
            }
            this.size = newSize;
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
            modCount++;
        }

        return anyToRemove;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void replaceAll(UnaryOperator<E> operator) {
        Objects.requireNonNull(operator);
        final int expectedModCount = modCount;
        final int size = this.size;
        for (int i=0; modCount == expectedModCount && i < size; i++) {
            elementData[i] = operator.apply((E) elementData[i]);
        }
        if (modCount != expectedModCount) {
            throw new ConcurrentModificationException();
        }
        modCount++;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void sort(Comparator<? super E> c) {
        final int expectedModCount = modCount;
        Arrays.sort((E[]) elementData, 0, size, c);
        if (modCount != expectedModCount) {
            throw new ConcurrentModificationException();
        }
        modCount++;
    }
}
