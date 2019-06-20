package homework.day3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class User {
    String userId;
    String name;
    int age;
    Map<String, List<String>> hobby = new HashMap<>();
    public User(String name, int age, Map<String, List<String>> hobby) {

        this.name = name;
        this.age = age;
        this.hobby = hobby;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getName() {
        return name;
    }
    public Map<String, List<String>> getHobby() {
        return hobby;
    }
    public void setHobby(Map<String, List<String>> hobby) {
        this.hobby = hobby;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return
                getName() + "," + getAge() + ", hobby=(" + getHobby() + ')';
    }


    public static void main(String[] args) {
        Map<String, List<String>> hobby = new HashMap<>();
        List<String> list = new ArrayList<>();
        list.add("一个人的书房");
        list.add("软件测试的艺术");
        list.add("围城");
        hobby.put("文学",list);
        Map<String, List<String>> hobby1 = new HashMap<>();
        List<String> list1 = new ArrayList<>();
        list1.add("肖申克的救赎");
        list1.add("窃听风云");
        list1.add("无间道");
        hobby1.put("电影",list1);
        hobby1.put("文学",list);
        Map<String, List<String>> hobby2 = new HashMap<>();
        hobby2.put("电影",list1);
        User user1 = new User("木木老师",28,hobby);
        User user2 = new User("大叔",33,hobby1);
        User user3 = new User("大佬",26,hobby);
        User user4 = new User("菜鸡",18,hobby2);
        User user5 = new User("我",29,hobby);
        Map<String, User> map = new HashMap<>(2);
        map.put("u001",user1);
        map.put("u002",user2);
        map.put("u003",user3);
        map.put("u004",user4);
        map.put("u005",user5);
        for (Map.Entry<String,User> entry:map.entrySet()){
            System.out.println(entry.getKey() + "," + entry.getValue().toString());
        }
        List<User> list2 = new ArrayList<>();
        list2.add(user1);
        list2.add(user2);
        list2.add(user3);
        list2.add(user4);
        list2.add(user5);
        List<User> filterList = list2.stream().filter(e->e.getAge()>30).collect(Collectors.toList());
        List<User> filterList1 = list2.stream().filter(e->e.getAge()<20).collect(Collectors.toList());
        List<User> filterList11 = list2.stream().filter(e->e.getAge()>=20 && e.getAge()<=30).collect(Collectors.toList());
        System.err.println("老年人" + filterList);
        System.err.println("正值壮年" + filterList1);
        System.err.println("小鲜肉" + filterList11);
        List<User> filterList2 = list2.stream().filter(e->e.getHobby().containsKey("电影")).collect(Collectors.toList());
        List<User> filterList3 = list2.stream().filter(e->e.getHobby().containsKey("文学")).collect(Collectors.toList());
        System.err.println("电影发烧友" + filterList2);
        System.err.println("文学爱好者" + filterList3);
    }
}