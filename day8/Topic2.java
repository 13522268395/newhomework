package homework.day8;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author myy
 * @create 2019/7/24 0:00
 * 参考链接: https://blog.csdn.net/qq_35387940/article/details/92620465
 * 研究下这个题, 试着自己去实现一下
 * 优雅就完事儿啦！！！
 */
public class Topic2 {
    public static void main(String[] args) {
        List<Map<String,Integer>> list = new ArrayList<Map<String,Integer>>(){{
            add(new HashMap<String,Integer>(){{put("a",1);put("c",3);}});
            add(new HashMap<String,Integer>(){{put("a",11);put("b",2);}});
            add(new HashMap<String,Integer>(){{put("a",111);put("c",1);}});
            add(new HashMap<String,Integer>(){{put("b",22);}});
            add(new HashMap<String,Integer>(){{put("a",1111);put("b",222);}});
        }};

        Map<String,Object> resultMap=new HashMap<>();
        List<Map<String, Object>> listA = new ArrayList<>();
        List<Map<String, Object>> listB = new ArrayList<>();
        List<Map<String, Object>> listC = new ArrayList<>();
        for (Map<String, Integer> get_map:list){
            for(Map.Entry<String, Integer> entry : get_map.entrySet()){
                if (entry.getKey().equals("a")){
                    Map<String, Object> processMapA= new HashMap<>();
                    processMapA.put(entry.getKey(),entry.getValue());
                    listA.add(processMapA);
                }
                if (entry.getKey().equals("b")){
                    Map<String, Object> processMapB= new HashMap<>();
                    processMapB.put(entry.getKey(),entry.getValue());
                    listB.add(processMapB);
                }
                if (entry.getKey().equals("c")){
                    Map<String, Object> processMapC= new HashMap<>();
                    processMapC.put(entry.getKey(),entry.getValue());
                    listC.add(processMapC);
                }
            }
        }

        listA.sort(new Comparator<Map<String, Object>>() {
            @Override
            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                Integer value1 = Integer.valueOf(o1.get("a").toString());
                Integer value2 = Integer.valueOf(o2.get("a").toString());
                return value2.compareTo(value1);
            }
        });

        listB.sort((o1, o2) -> {
            Integer value1 = Integer.valueOf(o1.get("b").toString()) ;
            Integer value2 = Integer.valueOf(o2.get("b").toString()) ;
            return value2.compareTo(value1);
        });
        listC.sort((o1, o2) -> {
            Integer value1 = Integer.valueOf(o1.get("c").toString());
            Integer value2 = Integer.valueOf(o2.get("c").toString());
            return value2.compareTo(value1);
        });
        resultMap.put("a",listA);
        resultMap.put("b",listB);
        resultMap.put("c",listC);
        System.out.println(resultMap.toString());


        Map map = list.stream()
                .flatMap(e->e.entrySet().stream())
                .sorted(((o1,o2)->o2.getValue().compareTo(o1.getValue())))
                .map(v->new HashMap<String,Integer>(){{put(v.getKey(),v.getValue());}})
                .collect(Collectors.groupingBy(o->o.entrySet().iterator().next().getKey()));

        System.out.println(map);


    }
}
