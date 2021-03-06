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

//懒汉式--->线程安全，同步方法
/**
 优缺点说明:
 |-解决了线程不安全问题
 |-效率太低了，每个线程再想获得类的实例的时候，执行getInstance()方法都要进行同步。而其实这个方法只执行一次实例化代码就够了，后面的想获得该类实例，直接return就行了。方法进行同步效率太低。
 结论:在实际开发中，不推荐使用
 **/
class LazyStyle_ThreadWithSafe{
    //构造器私有化
    private LazyStyle_ThreadWithSafe(){}
    //创建对象
    private static LazyStyle_ThreadWithSafe instance;
    //创建获取对象实例的公共接口，添加同步代码块来解决线程问题
    public static synchronized LazyStyle_ThreadWithSafe getInstance(){
        if(instance == null){
            instance = new LazyStyle_ThreadWithSafe();
        }
        return instance;
    }
}

//懒汉式--->线程安全，同步代码块
/**
 优缺点说明:
 |-这种方法本意是想对上述线程安全,同步方法实现方式的改进，因为前面同步方法效率太低，改为同步产生实例实例化的代码块
 |-但是这种同步并不是能起到线程同步的作用。跟上一个线程不安全实现方式遇到的情形一样，假如一个线程进入了if(instance == null){...}判断代码块，还未来得及往下执行，另一个线程也通过了这个判断语句，这是便会产生多个实例
 结论：在实际开发中，不能使用这种方式
 **/
class LazyStyle_ThreadWithSafeOfUseSynBlock{
    //构造器私有化
    private LazyStyle_ThreadWithSafeOfUseSynBlock(){}
    //创建对象
    private static LazyStyle_ThreadWithSafeOfUseSynBlock instance;
    //提供一个静态的公有方法，加入同步处理代码，解决线程安全问题
    public static LazyStyle_ThreadWithSafeOfUseSynBlock getInstance(){
        if(instance == null){
            synchronized (LazyStyle_ThreadWithSafeOfUseSynBlock.class){
                instance = new LazyStyle_ThreadWithSafeOfUseSynBlock();
            }
        }
        return instance;
    }
}

//双重检查
/**
 优缺点分析:
 |-双重检查概念是多线程开发中常使用到的，如代码所示，我们进行两次if(Singleton == null)的检查，这样就可以保证线程安全了
 |-这样实例化代码只用执行一次，后面在此访问时，判断if(Singleton == null)，直接return实例化对象，也避免了反复进行方法的同步
 |-线程安全；延迟加载；效率较高
 结论：在实际开发中，推荐使用这种设计模式
 **/
class LazyStyle_DoubleCheck{
    //构造器私有化
    private LazyStyle_DoubleCheck(){}
    //创建对象
    private static LazyStyle_DoubleCheck instance;
    //提供一个静态的公共接口，加入双重检查解决线程安全问题，同时解决懒加载效率问题
    public static LazyStyle_DoubleCheck getInstance(){
        if(instance == null){
            synchronized (LazyStyle_DoubleCheck.class){
                if(instance == null){
                    instance = new LazyStyle_DoubleCheck();
                }
            }
        }
        return instance;
    }
}

//静态内部类
/**
 优缺点说明:
 |-这种方式采用了类装载的机制来保证初始化实例时只有一个线程
 |-静态内部类方式在Singleton类被装载时并不会立即实例化，而是在需要实例化时，调用getInstance()方法，才会装载到SingleInstance类，从而完成Singleton的实例化。
 |-类的静态属性只会在第一次加载类的时候初始化，所以在这里，JVM帮组我们保证了线程的安全性，在类进行初始化时，别的线程时无法进入的。
 |-优点:避免线程不安全，利用静态内部类的特点实现延迟加载，效率高。
 结论：推荐使用
 **/
class SingletonPattern_InnerStaticClass {
    //构造器私有化
    private SingletonPattern_InnerStaticClass(){}
    //提供一个静态内部类，该类中有一个静态属性instance
    private static class SingletonInstance {
        private final static SingletonPattern_InnerStaticClass INSTANCE = new SingletonPattern_InnerStaticClass();
    }
    //提供一个静态方法，直接返回SigletonInstance.INSTANCE
    public static SingletonPattern_InnerStaticClass getInstance(){
        return SingletonInstance.INSTANCE;
    }
}

//枚举
/**
 优缺点分析:
 |-这借助JDK1.5中添加的枚举来实现单例模式。不仅能避免多线程同步问题，而且还能防止反序列化重新创建新的对象。
 |-这种方式是Effective Java作者Josh Bloch提倡的方式
 结论:强烈推荐使用
 **/
enum Singleton{
    INSTANCE;
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

        //懒汉式--->线程安全 同步方法
        LazyStyle_ThreadWithSafe instance7 = LazyStyle_ThreadWithSafe.getInstance();
        LazyStyle_ThreadWithSafe instance8 = LazyStyle_ThreadWithSafe.getInstance();
        System.out.println(instance7.hashCode()); //1836019240
        System.out.println(instance8.hashCode()); //1836019240
        System.out.println(instance7 == instance8); //true

        //懒汉式--->线程安全 同步代码块
        LazyStyle_ThreadWithSafeOfUseSynBlock instance9 = LazyStyle_ThreadWithSafeOfUseSynBlock.getInstance();
        LazyStyle_ThreadWithSafeOfUseSynBlock instance10 = LazyStyle_ThreadWithSafeOfUseSynBlock.getInstance();
        System.out.println(instance9.hashCode()); //325040804
        System.out.println(instance10.hashCode()); //325040804
        System.out.println(instance9 == instance10); //true

        //懒汉式--->线程安全，双重检查
        LazyStyle_DoubleCheck instance11 = LazyStyle_DoubleCheck.getInstance();
        LazyStyle_DoubleCheck instance12 = LazyStyle_DoubleCheck.getInstance();
        System.out.println(instance11.hashCode()); //1173230247
        System.out.println(instance12.hashCode()); //1173230247
        System.out.println(instance11 == instance12); //true

        //类加载--->静态内部类
        SingletonPattern_InnerStaticClass instance13 = SingletonPattern_InnerStaticClass.getInstance();
        SingletonPattern_InnerStaticClass instance14 = SingletonPattern_InnerStaticClass.getInstance();
        System.out.println(instance13.hashCode()); //856419764
        System.out.println(instance14.hashCode()); //856419764
        System.out.println(instance13 == instance14); //true

        //枚举
        Singleton instance15 = Singleton.INSTANCE;
        Singleton instance16 = Singleton.INSTANCE;
        System.out.println(instance15.hashCode()); //621009875
        System.out.println(instance16.hashCode()); //621009875
        System.out.println(instance15 == instance16); //true
    }
}
