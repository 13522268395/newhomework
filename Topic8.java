package homework;

/**
 * 作业8: 回想下面向对象练习的时候,我们设计的老师类和学生类,好多重复的属性和方法
 *
 * - 使用面向对象中的继承来抽象老师和学生更高一层的类,父类
 *
 * - 让相同的属性和方法可以抽离到父类中
 *
 * @author haoc
 */

public class Topic8 {
    String name;
    int age;
    public Topic8(String name,int age){
        this.name = name;
        this.age = age;
    }
    public void work(){
        System.out.println(age + "岁的" + name + "正在休息");
    }
}
 class Teacher extends Topic8{
    public Teacher(String name,int age){
        super(name, age);
    }
    @Override
    public void work(){
        super.work();
        System.out.println("休息好后准备去给学生上课");
    }
}

 class Student extends Topic8{
    public Student(String name,int age){
        super(name, age);
    }
    @Override
    public void work(){
        super.work();
        System.out.println("休息好后准备去听老师讲课");
    }

     public static void main(String[] args) {
         Student s = new Student("myy",29);
         s.work();
     }
}