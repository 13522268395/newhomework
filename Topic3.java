package homework;
import java.util.Scanner;
/**
 * 作业3: 实现一个计算器
 *
 * 需求如下:
 *
 * - 1.接收客户端输入(Java核心卷一,第三章,3.7节)的三个数据,格式: 数据1 操作符 数据2,如: 1 + 2
 *
 * - 2.如果数据1和2是数值型,则进行相应的运算符操作
 *
 * - 3.如果数据1和2不是数值型,则将数据1和数据2按字符串处理,与运算符一起拼接后输出,如: abc - bcd,输出"abc-bcd"
 *
 * 备注:
 *
 * - 1.计算器运算符只支持+,-,*,/,%
 *
 * - 2.数据和运算符之间使用空格隔开
 *
 * 要求:
 *
 * - 1.考虑程序的健壮性
 *
 * - 2.自行设计测试用例
 *
 * @author haoc
 */
public class Topic3{
    public  boolean isNum(String str){
        for(int i = str.length(); --i>=0 ;){
            if(!Character.isDigit(str.charAt(i))){
                return false;
            }
        }
        return true;
    }
    public String calResult(int str,int str1,String ope){
        String result=null;
        switch(ope){
            case "+":
                result =  String.valueOf(str+str1);
                break;
            case "*":
                result = String.valueOf(str*str1);
                break;
            default:
                System.out.println("操作符错误");
        }return result;
    }
    public static void main(String[] args) {
        Scanner data = new Scanner(System.in);
        System.out.println("请输入第一个数据:");
        String num1 = data.next();
        System.out.println("请输入操作符");
        String ope = data.next();
        System.out.println("请输入第二个数据:");
        String num2 = data.next();
        Topic3 gc = new Topic3();
        if(gc.isNum(num1)&&gc.isNum(num2)){
            String result = gc.calResult(Integer.valueOf(num1),Integer.valueOf(num2),ope);
            System.out.println(result);
        }else{
            System.out.println(num1+ope+num2);
        }
    }
}