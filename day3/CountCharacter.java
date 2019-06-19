package homework.day3;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * @Author myy
 * @create 2019/6/20 0:15
 */
public class CountCharacter {
    public static void main(String[] args) {
        String str = "abcdabcdabcdabcd+-..";
        System.out.println("输入的字符串为：" + str);
        String count = countCharacter(str);
        System.out.println("输出的字符串为：" + count);
        System.out.println("括号中为前面字母出现的次数");
    }
    /**
     * charAt() 方法用于返回指定索引处的字符
     * get(Object key) 返回指定键所映射的值，如果不包含该键的映射关系，返回null
     * value == null 就把 字母与个数 指定关联关系 不为空的情况下 个数+1
     * keySet() 返回此映射中包含的键的Set试图
     * 直接打印str也就是StringBuilder的引用 与str.toString()的结果一致
     */
    public static String countCharacter(String str){
        char[] c = str.toCharArray();
        Map<Character, Integer> map = new TreeMap<>();
        for (int i = 0; i < c.length; i++){
            Integer value = map.get(c[i]);
            if (!(c[i] >= 65&& c[i] <= 90 || c[i] >= 97 && c[i] <= 122)){
                continue;
            }if (value == null){
                map.put(c[i], 1);
            } else {
                map.put(c[i], value + 1);
            }
        }
        return maptoString(map);
    }
    private static String maptoString(Map<Character, Integer> map){
        StringBuilder str = new StringBuilder();
        Iterator<Character> it = map.keySet().iterator();
        while (it.hasNext()){
            Character key = it.next();
            Integer value = map.get(key);
            str.append(key+"("+value+")");
        }
        return str.toString();
    }
}
