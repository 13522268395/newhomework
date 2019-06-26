package homework.day4;

import com.sun.javafx.collections.MappingChange;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class Topic3Another {
    public static void main(String[] args) throws IOException {
        List<User> list = new ArrayList<>();
        Map<String,User> map = new HashMap<>();
        Map<String,List<User>> addr = new HashMap<>();
        Map<String,List<User>> gend = new HashMap<>();
        File f = new File("D:\\myy\\测开测试文件\\student.txt");
        InputStreamReader read = new InputStreamReader(new FileInputStream(f),"UTF-8");
        BufferedReader bufferedReader = new BufferedReader(read);
        String str;
        while ((str = bufferedReader.readLine())!= null) {
            User user = new User();
            String[] strings = str.split(",");
            user.setId(strings[0]).setName(strings[1]).setGender(strings[2]).setScore(strings[3]).setAddress(strings[4]);
            list.add(user);
            map.put(user.getId(), user);
            System.out.println(user);
        }
        bufferedReader.close();

        for (Map.Entry<String,User> entry:map.entrySet()){
            User user = entry.getValue();
            String address = entry.getValue().getAddress();
            if (addr.containsKey(address)){
                addr.get(address).add(user);
            }else {
                List<User> userList = new ArrayList<>();
                userList.add(user);
                addr.put(address, userList);
            }
        }
        System.out.println(addr);
        System.out.println("=================");
        //按地址分组 并遍历
        for (Map.Entry<String,List<User>> listEntry:addr.entrySet()){
            System.out.println(listEntry.getKey());
            List<User> users = listEntry.getValue();
            for (User user : users){
                System.out.println(user);
            }
            System.out.println();
        }

        for (Map.Entry<String,User> entry:map.entrySet()){
            User user = entry.getValue();
            String gender = entry.getValue().getGender();
            if (gend.containsKey(gender)){
                gend.get(gender).add(user);
            }else {
                List<User> userList = new ArrayList<>();
                userList.add(user);
                gend.put(gender, userList);
            }
        }
        System.out.println(gend);
        System.out.println("******************************");
        //按性别分组 并遍历
//        for (Map.Entry<String,List<User>> listEntry:gend.entrySet()){
//            System.out.println(listEntry.getKey());
//            List<User> users = listEntry.getValue();
//            for (User user : users){
//                System.out.println(user);
//            }
//            System.out.println();
//        }
    }
}
