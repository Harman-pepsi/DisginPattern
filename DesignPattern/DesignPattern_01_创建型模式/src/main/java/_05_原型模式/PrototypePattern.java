package _05_原型模式;

/**
 * @Classname PrototypePattern 原型模式
 * @Description
 * @Date 2020/6/13 3:16
 * @Created by Harman
 */

class Sheep implements Cloneable{
    private String name;
    private Integer age;
    private String cloor;
    private String adress = "蒙古";
    public Sheep friend; //是对象，克隆是如何处理的，默认是浅拷贝

    public Sheep(String name, Integer age, String cloor) {
        this.name = name;
        this.age = age;
        this.cloor = cloor;
    }

    @Override
    public String toString() {
        return "Sheep{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", cloor='" + cloor + '\'' +
                ", adress='" + adress + '\'' +
                '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
       Sheep sheep = null;
       sheep = (Sheep) super.clone();
       return sheep;
    }
}
public class PrototypePattern {
    public static void main(String[] args) throws CloneNotSupportedException {
        Sheep sheep = new Sheep("肖恩",3,"灰色");
        sheep.friend = new Sheep("多利",1,"白色");

        //System.out.println("Sheep:" + sheep + "sheep.friend:" + sheep.friend);
        Sheep sheep1 = (Sheep) sheep.clone();
        Sheep sheep2 = (Sheep) sheep.clone();
        Sheep sheep3 = (Sheep) sheep.clone();

        System.out.println("sheep1:" + sheep1 + "sheep1.friend:" + sheep1.friend + "hashcode:" + sheep1.friend.hashCode());
        System.out.println("sheep2:" + sheep2 + "sheep2.friend:" + sheep2.friend + "hashcode:" + sheep2.friend.hashCode());
        System.out.println("sheep3:" + sheep3 + "sheep3.friend:" + sheep3.friend + "hashcode:" + sheep3.friend.hashCode());
    }
}
