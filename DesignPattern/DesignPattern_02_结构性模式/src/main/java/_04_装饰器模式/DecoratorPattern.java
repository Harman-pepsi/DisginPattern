package _04_装饰器模式;

/**
 * @Classname DecoratorPattern
 * @Description 装饰者模式
 * @Created by XJC·AW
 * @Date 2020/7/6 11:50
 * @Version V1.0.0
 * @Since 1.0
 */
abstract class Drink{

    private String desc;
    private float price = 0.0f;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public abstract float cost();
}

class Coffe extends Drink{

    @Override
    public float cost() {
        return super.getPrice();
    }
}

class Espresso extends Coffe{

    public Espresso() {
        setDesc("意大利咖啡");
        setPrice(6.0f);
    }
}

class LongBlcak extends Coffe{

    public LongBlcak() {
        setDesc("黑咖啡");
        setPrice(5.0f);
    }
}

class ShortBlack extends Coffe{

    public ShortBlack() {
        setDesc("浅咖啡");
        setPrice(9.0f);
    }
}

class Decorator extends Drink{

    private Drink drink;

    public Decorator(Drink drink) {
        this.drink = drink;
    }

    @Override
    public float cost() {
        return super.getPrice() + drink.cost();
    }

    @Override
    public String getDesc() {
        return super.getDesc() + " " + super.getPrice() + "&&" + drink.getDesc();
    }
}

class Chocolate extends Decorator{

    public Chocolate(Drink drink) {
        super(drink);
        setDesc("巧克力");
        setPrice(3.0f);
    }
}

class Milk extends Decorator{

    public Milk(Drink drink) {
        super(drink);
        setDesc("牛奶");
        setPrice(2.0f);
    }
}

public class DecoratorPattern {
    public static void main(String[] args) {
        //2份巧克力+一份牛奶的黑咖啡
        Drink drink = new LongBlcak();
        System.out.println("费用1:" + drink.cost());
        System.out.println("描述:" + drink.getDesc());

        //加入牛奶
        drink = new Milk(drink);
        System.out.println("drink 加入一份牛奶 费用=" + drink.cost());
        System.out.println("drink 加入一份牛奶 描述=" + drink.getDesc());

        //加入巧克力
        drink = new Chocolate(drink);
        System.out.println("drink 加入一份巧克力 费用=" + drink.cost());
        System.out.println("drink 加入一份巧克力 描述=" + drink.getDesc());
    }
}
