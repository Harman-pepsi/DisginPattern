package _06_里氏替换原则;

/**
 * @Classname PrincipleOfHistoricalReplacement 里氏替换原则
 * @Description
 * @Date 2020/6/25 1:42
 * @Created by Harman
 */
abstract class Base{
    abstract int fun1(int ia,int ib);
}

class A extends Base{

    @Override
    int fun1(int ia, int ib) {
        return ia - ib;
    }
}

class B extends Base{

    private A a = new A();
    @Override
    int fun1(int ia, int ib) {
        return ia + ib;
    }

    int fun2(int ia,int ib){
        return fun1(ia,ib) + 9;
    }

    //想使用A中的方法
    int fun3(int ia,int ib){
        return a.fun1(ia,ib);
    }
}

public class PrincipleOfHistoricalReplacement {
    public static void main(String[] args) {
        A a = new A();
        System.out.println(a.fun1(1,2));
        B b = new B();
        System.out.println(b.fun1(1, 2));
        System.out.println(b.fun2(1,2));
        System.out.println(b.fun3(1,2));
    }
}
