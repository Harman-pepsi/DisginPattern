package _02_工厂模式;

import java.util.HashMap;
import java.util.Map;

/**
 * @Classname FactoryPattern 工厂模式
 * @Description
 * @Date 2020/6/10 23:57
 * @Created by Harman
 */

//原始代码
/*
class Vehicle{}
class Car extends Vehicle{}
class Truck extends Vehicle{}

public class FactoryPattern {
    public static void main(String[] args) {
        Car car = new Car();
        Truck truck = new Truck();
    }
}
上述代码由此看出两个问题:
        |-其一:类应该保持对扩展的开放，对修改的关闭(开闭原则)
        |-其二:每个类应该只有一个发生变化的原因(单一职责原则)
    每增加新的类造成主要代码的修改会打破开闭原则，而主类除了其固有的功能之外还负责实例化Vehicle对象，这种行为打破了单一职责原则。
*/


abstract class Vehicle{
    protected abstract void dirve();
}

class Car extends Vehicle{

    @Override
    protected void dirve() {
        System.out.println("drive Car......");
    }
}

class Bike extends Vehicle{

    @Override
    protected void dirve() {
        System.out.println("drive Bike......");
    }
}

class Truck extends Vehicle{

    @Override
    protected void dirve() {
        System.out.println("drive Truck......");
    }
}

//简单工厂
/*
class VehicleFactory{
    enum VehicleType{
        CAR,BIKE,TRUCK;
    }

    public static Vehicle create(VehicleType vehicleType){
        if(vehicleType.equals(VehicleType.CAR)){
            return new Car();
        }else if(vehicleType.equals(VehicleType.BIKE)){
            return new Bike();
        }else if(vehicleType.equals(VehicleType.TRUCK)){
            return new Truck();
        }else {
            return null;
        }
    }
}

public class FactoryPattern {
    public static void main(String[] args) {
        VehicleFactory.create(VehicleFactory.VehicleType.CAR).dirve();
        VehicleFactory.create(VehicleFactory.VehicleType.BIKE).dirve();
        VehicleFactory.create(VehicleFactory.VehicleType.TRUCK).dirve();
    }
}
优缺点分析:
        |-优点:逻辑简单，工厂类只负责Vehicle的实例化操作，符合单一职责原则；用户只调用Vehicle接口，这样做可以减少耦合，符合依赖倒置原则。
        |-缺点:当增加新的类时候，就需要对工厂类进行更改，这样做就打破了开闭原则。
    改进:可以使得注册的新类在使用时才被实例化，从而保证其对扩展开放，同时对修改关闭。具体实现方式有以下两种:
        |-方式一:使用反射机制注册产品类对象和实例化
        |-方式二:注册产品对象并向每个对象添加newInstance(）方法，该方法返回与自身类型相同的新实例。
*/

//反射机制进行类注册的简单工厂
/*
class VehicleFactory{
    private Map<String,Class> map = new HashMap<>();
    //注册类名、类
    public void registerVehicle(String vehicleName,Class vehicleClass){
        map.put(vehicleName,vehicleClass);
    }
    //根据类名获取注册的类
    public Vehicle createVehicle(String vehicleName) throws IllegalAccessException, InstantiationException {
        Class vehicleClass = map.get(vehicleName);
        return (Vehicle) vehicleClass.newInstance();
    }
}
public class FactoryPattern {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        VehicleFactory factory = new VehicleFactory();
        factory.registerVehicle("Car",Car.class);
        factory.createVehicle("Car").dirve();
        factory.registerVehicle("Bike",Bike.class);
        factory.createVehicle("Bike").dirve();
        factory.registerVehicle("Truck",Truck.class);
        factory.createVehicle("Truck").dirve();
    }
}
优缺点分析:
        |-在某种情况下，这种反射机制并不适用。比如，反射机制需要运行权限，这在某些特定环境中是无法实现的。反射机制也会降低程序的运行效率，在对性能要求很高的场景下应该避免使用这种机制。
*/

//newInstance方法进行类注册的简单工厂
class VehicleFactory{
    private Map<String,Vehicle> map = new HashMap<>();
    //注册类名，实体类
    public void registerVehicle(String vehicleName,Vehicle vehicle){
        map.put(vehicleName,vehicle);
    }
    //根据类名获取实体类
    public Vehicle createVehile(String vehicleName){
        return map.get(vehicleName);
    }
}

public class FactoryPattern {
    public static void main(String[] args) {
        VehicleFactory factory = new VehicleFactory();
        factory.registerVehicle("Car",new Car());
        factory.createVehile("Car").dirve();
        factory.registerVehicle("Bike",new Bike());
        factory.createVehile("Bike").dirve();
        factory.registerVehicle("Truck",new Truck());
        factory.createVehile("Truck").dirve();
    }
}