package _01_单例模式;

/**
 * @Classname SingletonPattern 单例模式
 * @Description TODO
 * @Date 2020/6/10 18:01
 * @Created by Harman
 */
//饿汉式--->静态变量
class HungryStyle_StaticVariable{
    //构造器私有化
    private HungryStyle_StaticVariable(){}
    //内部创建对象实例
    private final static HungryStyle_StaticVariable instance = new HungryStyle_StaticVariable();
    //获取对象实例的公共接口
    public static HungryStyle_StaticVariable getInstance(){
        return instance;
    }
}

//饿汉式--->静态代码块
class HungryStyle_StaticBlock{
    //构造器私有化
    private HungryStyle_StaticBlock(){}
    //创建对象
    private static HungryStyle_StaticBlock instance;
    //代码块创建对象实例
    static {
        instance = new HungryStyle_StaticBlock();
    }
    //获取对象实例的公共接口
    public static HungryStyle_StaticBlock getInstance(){
        return instance;
    }
}

public class SingletonPattern {
    public static void main(String[] args) {
        //饿汉式--->静态变量
        HungryStyle_StaticVariable instance1 = HungryStyle_StaticVariable.getInstance();
        HungryStyle_StaticVariable instance2 = HungryStyle_StaticVariable.getInstance();
        System.out.println(instance1.hashCode()); //1735600054
        System.out.println(instance2.hashCode()); //1735600054
        System.out.println(instance1 == instance2); //true

        //饿汉式--->静态代码块
        HungryStyle_StaticBlock instance3 = HungryStyle_StaticBlock.getInstance();
        HungryStyle_StaticBlock instance4 = HungryStyle_StaticBlock.getInstance();
        System.out.println(instance3.hashCode()); //21685669
        System.out.println(instance4.hashCode()); //21685669
        System.out.println(instance3 == instance4); //true
    }
}
