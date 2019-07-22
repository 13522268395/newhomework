package homework.day8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author myy
 * @create 2019/7/23 0:10
 * 随便一个文件 搜索文件中单词 然后把单词小写 去重 排序
 */
public class Topic1 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("D:\\myy\\测开测试文件\\EnglishChapter"));
        List<String> words = br.lines().flatMap(line-> Stream.of(line.split(" ")))
                .filter(word->word.length()>0)
                .map(String::toLowerCase).distinct().sorted().collect(Collectors.toList());
        br.close();
        System.out.println(words);
    }
}
