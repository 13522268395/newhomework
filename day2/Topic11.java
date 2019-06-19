//package homework;
//
//
//import java.io.*;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.StringTokenizer;
//
//
//
///**
// * 作业11: I/O + 集合 + 对象 练习题
// *
// * 文件: 位置随意,数据格式: id, name, gender, score, address
// *
// * 读取出数据之后,放到对象中User
// *
// * 在将User放入集合中
// *
// * 集合中的User,按照address,gender,分组,且每个组内的分数还要排序
// *
// * - 如: 北京,男 ; 上海,女
// *
// * @author haoc
// */
//public class Topic11{
//    public static void main(String[] args) {
//        List<User> list = new ArrayList();
//        readCSV("D:/myy/测开测试文件/student.csv", (ArrayList) list);
//        list.forEach(a -> System.out.println(a.getScore()));
//    }
//}
//public static void readCSV(String readpath, ArrayList list)
//{
//    File inFile = new File(readpath);
//    try{
//        BufferedReader reader = new BufferedReader(new FileReader(inFile));
//        boolean sign = false;
//        while (reader.ready())
//        {
//            String line = reader.readLine();
//            StringTokenizer st = new StringTokenizer(line, ",");
//            int id,score;
//            String name,gender,address;
//            if (st.hasMoreTokens()&&sign)
//            {
//                id = Integer.valueOf(st.nextToken().trim());
//                name = String.valueOf(st.nextToken().trim());
//                gender = String.valueOf(st.nextToken().trim());
//                score = Integer.valueOf(st.nextToken().trim());
//                address = String.valueOf(st.nextToken().trim());
//                Stu student = new Stu(id,name,gender,score,address);
//                list.add(student);
//            }
//            else
//            {
//                sign = true;
//            }
//        }
//        reader.close();
//    }
//    catch (FileNotFoundException e)
//    {
//        e.printStackTrace();
//    }
//    catch (IOException e)
//    {
//        e.printStackTrace();
//    }
//
//}
//class Stu{
//    String name;
//    String gender;
//    int score;
//    String address;
//    int id;
//    public Stu(int id,String name,String gender,int score,String address){
//        this.name = name;
//        this.id = id;
//        this.score = score;
//        this.gender = gender;
//        this.address = address;
//    }
//    public String getName(){
//        return name;
//    }
//    public int getId(){
//        return id;
//    }
//    public int getScore(){
//        return score;
//    }
//    public String getGender(){
//        return gender;
//    }
//    public String getAddress() {
//        return address;
//    }
//}