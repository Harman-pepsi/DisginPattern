package _04_接口隔离原则;

/**
 * @Classname PrincipleOfInterfaceIsolation 接口隔离原则
 * @Description
 * @Date 2020/6/24 18:52
 * @Created by Harman
 */
/*
interface IAnimal{
    void eat();
    void fly();
    void swim();
}

class Bird implements IAnimal{
    @Override
    public void eat() {

    }

    @Override
    public void fly() {

    }

    //鸟不会游泳
    @Override
    public void swim() {

    }
}

class Dog implements IAnimal{
    @Override
    public void eat() {

    }

    //狗不会飞
    @Override
    public void fly() {

    }

    @Override
    public void swim() {

    }
}
*/

interface IEatAnimal{
    void eat();
}

interface IFlyAnimal{
    void fly();
}

interface ISwimAnimal{
    void swim();
}

class Bird implements IEatAnimal,IFlyAnimal{

    @Override
    public void eat() {
        System.out.println("吃虫");
    }

    @Override
    public void fly() {
        System.out.println("给爷飞");
    }
}

class Dog implements IEatAnimal,ISwimAnimal{
    @Override
    public void eat() {
        System.out.println("吃肉");
    }

    @Override
    public void swim() {
        System.out.println("给爷爬");
    }
}

public class PrincipleOfInterfaceIsolation {
    public static void main(String[] args) {
        Bird bird = new Bird();
        bird.eat();
        bird.fly();

        Dog dog = new Dog();
        dog.eat();
        dog.swim();
    }
}
