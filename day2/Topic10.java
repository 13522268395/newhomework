package homework;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

/**
 * 作业10: 集合作业(需要提前学习下 List,Map等基本用法)
 *
 * - 定义一个对象,User,属性有 name,age,score,gender
 *
 * - 使用集合ArrayList,来装一批User, 数据可以随意
 *
 * - 要求1: 遍历集合,打印所有内部数据
 *
 * - 要求2: 按照年纪(age)进行分组,比如23岁,有3个人, 24岁有5个人,
 *
 * - 要求3: 按照分数(score)范围分组, 比如 60-70, 有一组人, 70-80有一组人
 *
 * - 要求4: 基于分数排序, 由高到低
 *
 * - 要求5: 基于性别分组,求男同学的平均分,求女同学的平均分
 *
 * @author haoc
 */
public class Topic10 {
    public static void main(String[] args) {
        User user = new User("myy", 20, 98, "男");
        //Collection c = new ArrayList();
        List<User> c = new ArrayList();
        User user1 = new User("jack", 23, 65, "女");
        User user2 = new User("tom", 20, 75, "男");
        User user3 = new User("jerry", 23, 68, "女");
        User user4 = new User("lucy", 20, 80, "女");
        User user5 = new User("jim", 21, 81, "男");
        User user6 = new User("luna", 20, 85, "女");
        User user7 = new User("jin", 23, 86, "男");
//        User user8 = new User("ken", 21, 95, "男");
        c.add(new User("lucy",20,80,"男"));
        c.add(user);
        c.add(user1);
        c.add(user2);
        c.add(user3);
        c.add(user4);
        c.add(user5);
        c.add(user6);
        c.add(user7);
        Collections.sort(c,new Comparator<User>(){
            @Override
            public int compare(User o1, User o2) {
                return o2.getScore()-o1.getScore();
            }
        });
        c.forEach(a -> System.out.println(a.getScore()));
//        List<User> student = Arrays.asList(user1,user2,user3,user4,user5,user6);
        List<List<User>> users1 = new ArrayList<>();
        c.stream().collect(Collectors.groupingBy(User::getAge,Collectors.toList()))
                .forEach((age,fooListByAge)->{
                    users1.add(fooListByAge);
                });
        users1.forEach((userList)->{
            System.out.println(userList.size());
            userList.forEach((users)->System.out.println(users));
        });
        System.out.println("============================");
        Map<String, Map<Integer, Long>> map = c.stream().collect(Collectors.groupingBy(User::getName,Collectors.groupingBy(User::getAge,Collectors.counting())));
        System.out.println(map);
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
        Map<Integer,List<User>> map1 = c.stream().collect(groupingBy(User::getAge));
        System.out.println(map1);
        System.out.println("***********************************");
        //Map<String,List<User>> = c.stream().collect(Collectors.groupingBy(User::getAge,Collectors.counting()));
//        System.out.println(result1);
//        Map<, List> employeesByCity = c.stream().collect(groupingBy(User::getAge));
//        HashMap<User,String> hm = new HashMap<>();
//        hm.put(user8,"8");
//        Set<Map.Entry<User, String>> entrySet = hm.entrySet();
//        Iterator<Map.Entry<User, String>> it = entrySet.iterator();
//        while (it.hasNext()) {
//            Map.Entry<User, String> next = it.next();
//            User key = next.getKey();
//            String value = next.getValue();
//            System.out.println(key + " = " + value);
//        }
        for ( User users:c){
            System.out.println(users);
        }
        Object[] objects = c.toArray();
        for (int i = 0; i < objects.length; i++) {
            User users = (User) objects[i];
            System.out.println(users.getName() + " " + users.getAge() + " " + users.getGender() + " " + users.getScore());
        }
    }
}
class User{
    String name;
    int age;
    int score;
    String gender;
    public User(String name,int age,int score,String gender){
        this.name = name;
        this.age = age;
        this.score = score;
        this.gender = gender;
    }
    public String getName(){
        return name;
    }
    public int getAge(){
        return age;
    }
    public int getScore(){
        return score;
    }
    public String getGender(){
        return gender;
    }

}
