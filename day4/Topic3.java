package homework.day4;

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
public class Topic3 {
    public static void main(String[] args) throws IOException {
        List<User> list = new ArrayList<>();
        Map<String,User> map = new HashMap<>();
        Map<String,User> addr = new HashMap<>();
        File f = new File("D:\\myy\\测开测试文件\\student.txt");
        InputStreamReader read = new InputStreamReader(new FileInputStream(f),"UTF-8");
        BufferedReader bufferedReader = new BufferedReader(read);
        String str;
        while ((str = bufferedReader.readLine())!= null){
            User user = new User();
            String[] strings = str.split(",");
            user.setId(strings[0]).setName(strings[1]).setGender(strings[2]).setScore(strings[3]).setAddress(strings[4]);
            list.add(user);
            map.put(user.getId(),user);
            System.out.println(user);
        }
        bufferedReader.close();
        for (Map.Entry<String,User> entry:map.entrySet()){
            System.out.println(entry.getKey() + entry.getValue());
        }
        for (Map.Entry<String,User> entry:map.entrySet()){
            addr.put(entry.getValue().getAddress(),entry.getValue());
        }

        for (Map.Entry<String,User> entry1:addr.entrySet()){
            System.out.println(entry1.getKey() + entry1.getValue());
        }

    }
}
