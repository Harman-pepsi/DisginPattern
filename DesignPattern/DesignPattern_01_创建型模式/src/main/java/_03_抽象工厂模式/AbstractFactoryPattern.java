package _03_抽象工厂模式;

import java.util.HashMap;
import java.util.Map;

/**
 * @Classname AbstractFactoryPattern 抽象工厂模式
 * @Description
 * @Date 2020/6/12 17:36
 * @Created by Harman
 */
interface IVehicle{
    void driver();
}

class Car implements IVehicle{

    @Override
    public void driver() {
        System.out.println("driver Car......");
    }
}

class Bike implements IVehicle{

    @Override
    public void driver() {
        System.out.println("driver Bike......");
    }
}

class Truck implements IVehicle{

    @Override
    public void driver() {
        System.out.println("driver Truck......");
    }
}

interface IRefit{
    void refit();
}

class SuperCar implements IRefit{

    @Override
    public void refit() {
        System.out.println("Refit Car......");
    }
}

class SuperBike implements IRefit{

    @Override
    public void refit() {
        System.out.println("Refit Bike......");
    }
}

class SuperTruck implements IRefit{

    @Override
    public void refit() {
        System.out.println("Refit Truck......");
    }
}

abstract class AbstractFactory{
    abstract IVehicle getVehicle(String vehicleType);
    abstract IRefit getRefit(String refitType);
}

class VehicleFactory extends AbstractFactory{

    @Override
    IVehicle getVehicle(String vehicleType) {
        if(vehicleType.equals("Car")){
            return new Car();
        }else if(vehicleType.equals("Bike")){
            return new Bike();
        }else if(vehicleType.equals("Truck")){
            return  new Truck();
        }else {
            return null;
        }
    }

    @Override
    IRefit getRefit(String refitType) {
       return null;
    }
}

class RefitFactory extends AbstractFactory{
    @Override
    IVehicle getVehicle(String vehicleType) {
        return null;
    }

    @Override
    IRefit getRefit(String refitType) {
        if(refitType.equals("SuperCar")){
            return new SuperCar();
        }else if(refitType.equals("SuperBike")){
            return new SuperBike();
        }
        else if(refitType.equals("SuperTruck")){
            return new SuperTruck();
        }else {
            return null;
        }
    }
}

class FactoryBuilder {
    public static AbstractFactory getFactory(String choice){
        if(choice.equals("vehicle")){
            return new VehicleFactory();
        }else if(choice.equals("refit")){
            return new RefitFactory();
        }else{
            return null;
        }
    }
}

public class AbstractFactoryPattern {
    public static void main(String[] args) {
        AbstractFactory vehicle = FactoryBuilder.getFactory("vehicle");
        vehicle.getVehicle("Car").driver();
        vehicle.getVehicle("Bike").driver();
        vehicle.getVehicle("Truck").driver();

        AbstractFactory refit = FactoryBuilder.getFactory("refit");
        refit.getRefit("SuperCar").refit();
        refit.getRefit("SuperBike").refit();
        refit.getRefit("SuperTruck").refit();
    }
}
