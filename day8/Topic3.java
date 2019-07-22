package homework.day8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author myy
 * @create 2019/7/23 0:26
 * 统计文件中的单词
 * 实现统计分析，计算出现次数，并由大到小顺序排序
 */
public class Topic3 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new FileReader("D:\\myy\\测开测试文件\\EnglishChapter"));
        Map<String, Long> collect = br.lines().map(String::trim)
                .filter(s -> !s.isEmpty()).map(s -> s.split(" "))
                .flatMap(array -> Stream.of(array))
                .collect(Collectors.groupingBy(s -> s, Collectors.counting()));
        br.close();
        Map<String, Long> finalMap = new LinkedHashMap<>();
        collect.entrySet().stream().sorted(Map.Entry.<String,Long>comparingByValue().reversed()).forEachOrdered(e->finalMap.put(e.getKey(),e.getValue()));
        System.out.println(finalMap);
    }
}
