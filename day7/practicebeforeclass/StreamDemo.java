package homework.day7.practicebeforeclass;

/**
 * @Author myy
 * @create 2019/7/18 21:56
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 预习:
 *
 * java8, lambda, stream
 *
 * guava, commons-lang3
 *
 * @author haoc
 */
public class StreamDemo {

/**
 * Java 8 中的 Stream 是对集合（Collection）对象功能的增强，它专注于对集合对象进行各种非常便利、
 * 高效的聚合操作（aggregate operation），或者大批量数据操作 (bulk data operation)。
 * Stream API 借助于同样新出现的 Lambda 表达式，极大的提高编程效率和程序可读性。
 * java.util.stream 是一个函数式语言+多核时代综合影响的产物
 *
 * Stream不是集合元素，它不是数据结构并不保存数据
 * 单向、不可往复、数据只能遍历一次
 * 可以并行化操作
 *
 * 获取一个数据源（source）→ 数据转换→执行操作获取想要的结果，每次转换原有 Stream 对象不改变，
 * 返回一个新的 Stream 对象（可以有多次转换），这就允许对其操作可以像链条一样排列，变成一个管道
 *
 * 生成数据源 Collection.stream()    Collection.parallelStream() Arrays.stream(T array)  Stream.of()
 * java.io.BufferedReader.lines()   自己构建  静态工厂
 *
 * 特性归纳
 * 不是数据结构
 * 没有内部存储，至少用操作管道从source抓取数据
 * stream以lambda表达式为参数
 * 不支持索引访问
 * 容易生成数组或list
 * 惰性化
 * 很多stream操作是向后延迟的
 * 并行能力
 * 可以是无限的
 */


    public static void main(String[] args) throws IOException {

        //1.individual values
        Stream stream = Stream.of("a","b","c");

        //2.Arrays
        String[] strArray = new String[]{"a","b","c"};
        Stream stream1 = Stream.of(strArray);
        Stream stream2 = Arrays.stream(strArray);

        //3.Collections
        List<String> list = Arrays.asList(strArray);
        Stream stream3 = list.stream();

        //4.对于基本数值型，IntStream，LongStream，DoubleStream三种

        //5、流转换为其他数据结构
        String[] strArray1 = (String[]) stream.toArray(String[]::new);

        //6.转换为大写
        List<String> wordList = new ArrayList<>();
        wordList.add("stream");
        wordList.add("lambda");
        wordList.add("guava");
        wordList.stream().map(String::toUpperCase).collect(Collectors.toList()).forEach(System.out::println);

        //7.平方数
        List<Integer> nums = Arrays.asList(1,2,3,4);
        nums.stream().map(n->n*n).collect(Collectors.toList()).forEach(System.out::println);

        //8.一对多
        Stream<List<Integer>> inputStream = Stream.of(Arrays.asList(1),Arrays.asList(2,3),Arrays.asList(4,5,6));
        inputStream.flatMap((childlist)->(childlist.stream())).forEach(System.out::println);

        //9.留下偶数
        Integer[] sixNums = {1,2,3,4,5,6};
        Integer[] evens = Stream.of(sixNums).filter(n->n%2==0).toArray(Integer[]::new);
        for (Integer even : evens) {
            System.out.println(even);
        }

        //10.peek
        Stream.of("one","two","three","four").filter(e->e.length()>3).peek(e-> System.out.println("filtered value: " + e)).
                map(String::toUpperCase).peek(e-> System.out.println("mapped value: " + e)).collect(Collectors.toList())
                .forEach(System.out::println);

        //11.reduce
        String concat = Stream.of("A","B","C","D").reduce("",String::concat);
        double minValue = Stream.of(-1.5,1.0,-3.0,-2.0).reduce(Double.MAX_VALUE,Double::min);
        int sumValue = Stream.of(1,2,3,4).reduce(0,Integer::sum);
        sumValue = Stream.of(1,2,3,4).reduce(Integer::sum).get();
        concat = Stream.of("a","B","c","D","e","F").filter(x->x.compareTo("Z")>0).reduce("",String::concat);

        //12.limit/skip

        //13.找出最长一行的长度  找出全文的单词，转小写并排序
        BufferedReader br = new BufferedReader(new FileReader("D:\\myy\\测开测试文件\\student.txt"));
        int longest = br.lines().mapToInt(String::length).max().getAsInt();
        List<String> words = br.lines().flatMap(line->Stream.of(line.split(" "))).filter(word->word.length()>0)
                .map(String::toLowerCase).distinct().sorted().collect(Collectors.toList());
        br.close();
        System.out.println(longest);
        System.out.println(words);

        //14.allMatch anyMatch noneMatch

        //15.通过实现supplier接口，自己来控制流的生成，默认是串行且无序的，由于它是无限的，在管道中利用limit来限制大小
        Random seed = new Random();
        Supplier<Integer> random = seed::nextInt;
        Stream.generate(random).limit(10).forEach(System.out::println);

        //16.生成一个等差数列 打印x-> System.out.print(x+ " ")
        Stream.iterate(0,n->n+3).limit(10).forEach(x-> System.out.print(x+ " "));

        //17.groupingBy/partitioningBy




    }
}
