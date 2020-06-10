package _01_单例模式;

/**
 * @Classname SingletonPattern 单例模式
 * @Description TODO
 * @Date 2020/6/10 18:01
 * @Created by Harman
 */

//饿汉式--->静态变量
/**
 该方法优缺点:
 |-优点：这种写法比较简单，就是在类装载的时候就完成实例化。避免线程同步问题。
 |-缺点：在类装载的时候就完成实例化，没有达到Lazy Loading的效果，如果从始至终从未使用这个实例，则会造成内存的浪费。
 |-这种方式基于ClassLoader机制避免了多线程的同步问题，不过，instance在类装载时就实例化，在单例模式中大多是都是调用getInstance方法，但是导致类装载的原因有很多种，因此不能确定有其他的方式(或者其他的静态方法)导致类装载，这时候初始化instance就没有达到Lazy Loading的效果。
 结论:这种单例模式可以用，可能造成内存浪费。
 **/
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
/**
 该方法的优缺点：
 |-这种方式和上面的方式其实类似，只不过将类实例化的过程放在了静态代码块中，也是在类装载的时候，就执行静态代码块中的代码，初始化类的实例。优缺点和上面是一样的。
 结论:这种单例模式可用，但是可能造成内存浪费。
 **/
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

//懒汉式--->线程不安全
/**
 优缺点分析：
 |-起到Lazy Loading的效果，但是只能在单线程下使用。
 |-如果在多线程下，一个线程进入了 if(instance == null){...}判断语句中，还未来得及往下执行，另一个线程也通过了这个判断语句，这是便会产生多个实例。所以在多线程环境下不可以使用这种方式
 结论:在实际开发中，不要使用这种方式
 **/
class LazyStyle_ThreadUnSafe{
    //构造器私有化
    private LazyStyle_ThreadUnSafe(){}
    //创建对象
    private static LazyStyle_ThreadUnSafe instance;
    //创建获取对象实例的公共接口，当用到该方法时候，才去加载instance，即懒汉式
    public static LazyStyle_ThreadUnSafe getInstance(){
        if(instance == null){
            instance = new LazyStyle_ThreadUnSafe();
        }
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

        //懒汉式--->线程不安全
        LazyStyle_ThreadUnSafe instance5 = LazyStyle_ThreadUnSafe.getInstance();
        LazyStyle_ThreadUnSafe instance6 = LazyStyle_ThreadUnSafe.getInstance();
        System.out.println(instance5.hashCode()); //2133927002
        System.out.println(instance6.hashCode()); //2133927002
        System.out.println(instance5 == instance6); //true

    }
}
