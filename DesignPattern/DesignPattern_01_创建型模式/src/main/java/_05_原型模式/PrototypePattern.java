package _05_原型模式;

import java.io.*;

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
        //浅拷贝
        Sheep sheep = new Sheep("肖恩",3,"灰色");
        sheep.friend = new Sheep("多利",1,"白色");

        //System.out.println("Sheep:" + sheep + "sheep.friend:" + sheep.friend);
        Sheep sheep1 = (Sheep) sheep.clone();
        Sheep sheep2 = (Sheep) sheep.clone();
        Sheep sheep3 = (Sheep) sheep.clone();

        System.out.println("sheep1:" + sheep1 + "sheep1.friend:" + sheep1.friend + "hashcode:" + sheep1.friend.hashCode());
        System.out.println("sheep2:" + sheep2 + "sheep2.friend:" + sheep2.friend + "hashcode:" + sheep2.friend.hashCode());
        System.out.println("sheep3:" + sheep3 + "sheep3.friend:" + sheep3.friend + "hashcode:" + sheep3.friend.hashCode());

        //深拷贝
        DeepCloneType deepCloneType = new DeepCloneType();
        deepCloneType.name = "宋江";
        deepCloneType.deepCloneTarget = new DeepCloneTarget("大牛","小牛");
        //方式一深拷贝
//        DeepCloneType deepCloneType1 = (DeepCloneType) deepCloneType.clone();
//        System.out.println("deepCloneType.name=" + deepCloneType.name + ",deepCloneType.deepCloneTarget" + deepCloneType.deepCloneTarget.hashCode());
//        System.out.println("deepCloneType1.name=" + deepCloneType1.name + ",deepCloneType1.deepCloneTarget" + deepCloneType1.deepCloneTarget.hashCode());

        DeepCloneType deepCloneType1 = (DeepCloneType) deepCloneType.deepClone();
        System.out.println("deepCloneType.name=" + deepCloneType.name + ",deepCloneType.deepCloneTarget" + deepCloneType.deepCloneTarget.hashCode());
        System.out.println("deepCloneType1.name=" + deepCloneType1.name + ",deepCloneType1.deepCloneTarget" + deepCloneType1.deepCloneTarget.hashCode());
    }
}

//深拷贝
class DeepCloneTarget implements Serializable,Cloneable {
    private static final long serialVersionUID = 1L;
    private String cloneName;
    private String cloneClass;

    public DeepCloneTarget(String cloneName, String cloneClass) {
        this.cloneName = cloneName;
        this.cloneClass = cloneClass;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

class DeepCloneType implements Serializable,Cloneable{
    public String name;
    public DeepCloneTarget deepCloneTarget;

    public DeepCloneType() {
    }

    //深拷贝方式一:使用clone方法
    @Override
    protected Object clone() throws CloneNotSupportedException {
        Object depp = null;
        depp = super.clone();
        DeepCloneType deepCloneType = (DeepCloneType) depp;
        deepCloneType.deepCloneTarget = (DeepCloneTarget) deepCloneTarget.clone();
        return deepCloneType;
    }

    //深拷贝方式二:使用序列化的方法
    public Object deepClone(){
        //创建流对象
        ByteArrayOutputStream bos = null;
        ObjectOutputStream oos = null;
        ByteArrayInputStream bis = null;
        ObjectInputStream ois = null;

        try {
            //序列化
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            //当前这个对象以对象流的方式输出
            oos.writeObject(this);
            //反序列化
            bis = new ByteArrayInputStream(bos.toByteArray());
            ois = new ObjectInputStream(bis);
            DeepCloneType copyObject = (DeepCloneType) ois.readObject();
            return copyObject;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }finally {
            try {
                ois.close();
                bis.close();
                oos.close();
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
