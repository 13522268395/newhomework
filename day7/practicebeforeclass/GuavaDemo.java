package homework.day7.practicebeforeclass;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.sun.scenario.effect.impl.state.LinearConvolveRenderState;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author myy
 * @create 2019/7/18 23:17
 */
public class GuavaDemo {
    public static void main(String[] args) {

        // Collections2 filter()只保留集合中满足特定要求的元素
        List<String> list = Lists.newArrayList("moon","dad","refer","son");
        Collection<String> palindromeList = Collections2.filter(list,input -> {
            return new StringBuilder(input).reverse().toString().equals(input);
        });
        System.out.println(palindromeList);

        //类型转换
        Set<Long> times = Sets.newHashSet();
        times.add(91299990701L);
        times.add(9320001010L);
        times.add(9920170621L);
        Collection<String> timeStrCol = Collections2.transform(times, new Function<Long, String>() {
            @Nullable
            @Override
            public String apply(@Nullable Long input) {
                return new SimpleDateFormat("yyyy-MM-dd").format(input);
            }
        });
        System.out.println(timeStrCol);

        //Optional
        //Guava用Optional表示可能为null的T类型引用。一个Optional实例可能包含非null的引用，也可能什么也不包括
        //它从不说包含的是null值，而是用存在或缺失来表示
//        Integer value1 = null;
//        Integer value2 = 10;
//        Optional<Integer> a = Optional.fromNullable(value1);
//        Optional<Integer> b = Optional.of(value2);
//        System.out.println(sum(a,b));
//
//        private static Integer sum(Optional<Integer> a, Optional<Integer> b){
//            System.out.println("First param is present: "+a.isPresent());
//            System.out.println("Second param is present: "+b.isPresent());
//            Integer value1 = a.or(0);
//            Integer value2 = b.get();
//            return value1 + value2;
//        }

        //Preconditions
        //前置条件Preconditions提供静态方法来检查方法或构造函数，被调用是否给定适当的参数

        try {
            getValue(5);
        } catch (IndexOutOfBoundsException e){
            System.out.println(e.getMessage());
        }

        try {
            sum(4,null);
        } catch (NullPointerException e){
            System.out.println(e.getMessage());
        }

        try {
            sqrt(-1);
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }

        //joiner提供了各种方法来处理字符串加入操作，对象等
        //joiner的实例不可变的，因此线程安全

        StringBuilder sb = new StringBuilder();
        Joiner.on(",").skipNulls().appendTo(sb,"Hello","guava");
        System.out.println(sb);
        System.out.println(Joiner.on(",").useForNull("none").join(1,null,3));
        System.out.println(Joiner.on(",").skipNulls().join(Arrays.asList(1,2,3,4,null,6)));

        Map<String,String> map = new HashMap<>();
        map.put("key1","value1");
        map.put("key2","value2");
        map.put("key3","value3");
        System.out.println(Joiner.on(",").withKeyValueSeparator("=").join(map));

    }

    private static double sqrt(double input){
        Preconditions.checkArgument(input>0.0,
                "Illegal Argument passed: Negative value %s.",input);
        return Math.sqrt(input);
    }

    private static int sum(Integer a,Integer b){
        a=Preconditions.checkNotNull(a,
                "Illegal Argument passed: First parameter is Null.");
        b=Preconditions.checkNotNull(b,
                "Illegal Argument passed: Second parameter is Null.");
        return a+b;
    }

    private static int getValue(int input){
        int[] data={1,2,3,4,5};
        int index= Preconditions.checkElementIndex(input,data.length,
                "Illegal Argument passed: Invalid index.");
        return data[index];
    }




}
