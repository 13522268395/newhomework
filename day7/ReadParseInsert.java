package homework.day7;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author myy
 * @create 2019/7/14 11:57
 * 回顾之前的一个作业, day02/homework.Topic11
 *
 * 从文件取出数据之后,将数据放到数据库中去,
 *
 * 注: 注意代码的健壮性 以及 编写相应的测试用例
 *
 * 要求: 经过几天的代码编写, 请尽量用一些设计的手段进去
 */


/**
 * 1、读取文件
 * 2、对数据进行解析
 * 3、入库
 */
public class ReadParseInsert {

    private static final String URL = "jdbc:mysql://127.0.0.1:3306/cakes?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=UTC";
    private static final String USER_NAME = "root";
    private static final String PASSWD = "123456";

    public static void main(String[] args) {
        List<String> result = readFile("D:\\myy\\测开测试文件\\user.txt");
        for (String string : result) {
            System.out.println(string);
        }
        List<User> userList = parse(result);
        for (User user : userList) {
            System.out.println(user);
        }
        int insertResult = insert(userList);
        System.out.println(insertResult);
    }

    private static List<String> readFile(String path){
        List<String> result = new ArrayList<>();
        File file = new File(path);
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"))) {
            String val = null;
            while ((val = bufferedReader.readLine()) != null){
                result.add(val);
            }
            return result;
        }catch (Exception ex){
            System.out.println("出错原因");
            throw new IllegalStateException();
        }
    }

    private static List<User> parse(List<String> result){
        List<User> users = new ArrayList<>();
        for (String data : result) {
            String[] strings = data.split(",");
            Integer id = Integer.parseInt(strings[0]);
            String name = strings[1];
            String gender = strings[2];
            Integer score = Integer.parseInt(strings[3]);
            String address = strings[4];
            User user = new User(id,name,gender,score,address);
            users.add(user);
        }
        return users;
    }

    private static int insert(List<User> userList) {
        int effectrows = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWD);
            String sql = "insert into user(id,name,gender,score,address) values(?,?,?,?,?)";
            for (User user : userList) {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, user.getId());
                preparedStatement.setString(2, user.getName());
                preparedStatement.setString(3, user.getGender());
                preparedStatement.setInt(4, user.getScore());
                preparedStatement.setString(5, user.getAddress());
                effectrows = preparedStatement.executeUpdate() + effectrows;
            }
            return effectrows;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            try{
                if (null != preparedStatement){
                    preparedStatement.close();
                }
                if (null != connection){
                    connection.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return -1;
    }
}
