package _08_适配器模式._1_类适配器模式;

/**
 * @Classname AdpaterPatternOfClass
 * @Description 类适配器模式
 * @Created by XJC·AW
 * @Date 2020/7/2 10:56
 * @Version V1.0.0
 * @Since 1.0
 */
class VoltageOutPut220V{
    public int voltageOutPut220V(){
        int src = 220;
        System.out.println("电压输出为:" +src+ "伏");
        return src;
    }
}

interface IVoltageOutPut5V{
    public int voltageOutPut5V();
}

class VoltageAdapter extends VoltageOutPut220V implements IVoltageOutPut5V{
    @Override
    public int voltageOutPut5V() {
        int srcV = voltageOutPut220V();
        int desV = srcV /44;
        return desV;
    }
}

class Phone{
    public void charging(IVoltageOutPut5V voltageOutPut5V){
        if (voltageOutPut5V.voltageOutPut5V() == 5){
            System.out.println("电压调整为5V,可以充电.......");
        }else{
            System.out.println("电压不符.......");
        }
    }
}

public class AdpaterPatternOfClass {
    public static void main(String[] args) {
        Phone phone = new Phone();
        phone.charging(new VoltageAdapter());
    }
}
