package com.home;


import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author myy
 * @create 2019/6/26 0:02
 * 文件: 位置随意,数据格式: id, name, gender, score, address
 *
 * 读取出数据之后,放到对象中User
 *
 * 在将User放入集合中
 *
 * 集合中的User,按照address,gender,分组,且每个组内的分数还要排序
 *
 * - 如: 北京,男 ; 上海,女
 */
public class Topic3 {
    public static void main(String[] args) throws IOException {
        List<User1> list = new ArrayList<>();
        Map<String,User1> map = new HashMap<>();
        Map<String,User1> addr = new HashMap<>();
        File f = new File("D:\\myy\\测开测试文件\\student.txt");
        InputStreamReader read = new InputStreamReader(new FileInputStream(f),"UTF-8");
        BufferedReader bufferedReader = new BufferedReader(read);
        String str;
        while ((str = bufferedReader.readLine())!= null){
            User1 user = new User1();
            String[] strings = str.split(",");
            user.setId(strings[0]).setName(strings[1]).setGender(strings[2]).setScore(strings[3]).setAddress(strings[4]);
            list.add(user);
            map.put(user.getId(),user);
            System.out.println(user);
        }
        bufferedReader.close();
        List<User1> filterList = list.stream().filter(e->e.getAddress().contains("北京")).collect(Collectors.toList());
        System.out.println(filterList);
        System.out.println("----------------上面过滤北京地区----------------------");

        Map<String, List<User1>> collect = list.stream().collect(Collectors.groupingBy(User1::getAddress));
        System.out.println(collect);
        System.out.println("============上面按地址分组===============");

        Map<String,List<User1>> collect1 = list.stream().collect(Collectors.groupingBy(User1::getGender));
        System.out.println(collect1);
        System.out.println("++++++++++++上面按性别分组+++++++++++++++++");

        Map<String,List<User1>> collect2 = list.stream().sorted((o1,o2)-> Integer.parseInt(o1.getScore()) - Integer.parseInt(o2.getScore())).collect(Collectors.groupingBy(User1::getGender));
        System.out.println(collect2);
        System.out.println("&&&&&&&&&&&&&&上面按性别分组 成绩升序&&&&&&&&&&&&&&");

        Collections.sort(list, new Comparator<User1>() {
            @Override
            public int compare(User1 o1, User1 o2) {
                return (Integer.parseInt(o2.getScore()) - Integer.parseInt(o1.getScore()));
            }
        });

        System.out.println(list);
        System.out.println("*************上面按成绩降序排序***************");
        //Map<List<String>, List<User1>> collect2 = list.stream().collect(Collectors.groupingBy(f -> Arrays.asList(f.getAddress(),f.getGender())));
        //尝试按区域、性别 分组



        for (Map.Entry<String,User1> entry:map.entrySet()){
            System.out.println(entry.getKey() + entry.getValue());
        }
        System.out.println("==========上面遍历map==============");

        for (Map.Entry<String,User1> entry:map.entrySet()){
            addr.put(entry.getValue().getAddress(),entry.getValue());
        }
        System.out.println(addr);
        System.out.println("=======上面尝试按地址分组，结果key值一样，value被覆盖啦=============");


        for (Map.Entry<String,User1> entry1:addr.entrySet()){
            System.out.println(entry1.getKey() + entry1.getValue());
        }
        System.out.println("************上面遍历分组后的map**************");
//        测试map中key值相同，插入不同的value，查看value的值
//        System.out.println("=========================");
//        System.out.println(addr);
//        Map<String,String> testMap = new HashMap<>();
//        testMap.put("test","myy");
//        testMap.put("test","lj");
//        System.out.println(testMap.values());

    }
}
