package _02_依赖倒置原则;

/**
 * @Classname PrincipleOfDependenceInversion1 依赖倒置原则
 * @Description
 * @Date 2020/6/24 15:46
 * @Created by Harman
 */
/*
依赖倒转原则是指:
    |-高层模块不应该依赖低层模块，二者都应该依赖其抽象
    |-抽象不应该依赖细节，细节应该依赖抽象
    |-依赖倒转(倒置)的中心思想是面向接口编程
    |-依赖倒转原则是基于这样的设计理念：相对于细节的多变性，抽象的东西要稳定的多。以抽象为基础搭建的架构比以细节为基础的架构要稳定的多。在java中，抽象指的是接口或抽象类，细节就是具体的实现类。
    |-使用接口或抽象类的目的是制定好规范，而不涉及任何具体的操作，把展现细节的任务交给他们的实现类去完成。
*/
interface ICourse{
    void study();
}

class JavaCourse implements ICourse{
    @Override
    public void study() {
        System.out.println("Tom在学习Java课程!");
    }
}

class Python implements ICourse{

    @Override
    public void study() {
        System.out.println("Tom在学习Python课程!");
    }
}

class Tom{
    //构造函数注入
    public void study(ICourse course){
        course.study();
    }

    /* 构造器注入
    private ICourse course;

    public Tom(ICourse course) {
        this.course = course;
    }

    public void study(){
        course.study();
    }
    */

    /*
    private ICourse course;

    public void setCourse(ICourse course) {
        this.course = course;
    }

    public void study(){
        course.study();
    }
    */
}

public class PrincipleOfDependenceInversion1 {
    public static void main(String[] args) {
        Tom tom = new Tom();
        tom.study(new JavaCourse());
        tom.study(new Python());
    }
}
