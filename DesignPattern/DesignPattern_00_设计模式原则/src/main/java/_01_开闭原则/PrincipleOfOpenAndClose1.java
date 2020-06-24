package _01_开闭原则;

/**
 * @Classname PrincipleOfOpenAndClose 开闭原则
 * @Description
 * @Date 2020/6/24 8:14
 * @Created by Harman
 */
interface ICourse{
    Integer getId();
    String getName();
    Double getPrice();
}

class JavaCourse implements ICourse{

    private Integer id;
    private String name;
    private Double price;

    public JavaCourse(Integer id, String name, Double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Double getPrice() {
        return this.price;
    }
}

//通过继承来实现变化，而不修改原有代码
class JavaDiscountCourse extends JavaCourse{

    public JavaDiscountCourse(Integer id, String name, Double price) {
        super(id, name, price);
    }

    public Double getOriginPrice(){
        return super.getPrice();
    }

    public Double getPrice(){
        return super.getPrice() * 0.6;
    }
}

public class PrincipleOfOpenAndClose1 {
    public static void main(String[] args) {
        JavaDiscountCourse course = new JavaDiscountCourse(1,"Java编程思想",99.99d);
        System.out.println(course.getOriginPrice());
        System.out.println(course.getPrice());
    }
}
