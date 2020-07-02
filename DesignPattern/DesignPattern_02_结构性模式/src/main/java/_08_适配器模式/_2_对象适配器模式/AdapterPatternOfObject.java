package _08_适配器模式._2_对象适配器模式;

import java.awt.print.PrinterJob;

/**
 * @Classname AdapterPatternOfObject
 * @Description 对象适配器
 * @Created by XJC·AW
 * @Date 2020/7/2 11:17
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

class VoltageOutPutAdapter extends VoltageOutPut220V implements IVoltageOutPut5V{
    private VoltageOutPut220V voltageOutPut220V;

    public VoltageOutPutAdapter(VoltageOutPut220V voltageOutPut220V) {
        this.voltageOutPut220V = voltageOutPut220V;
    }

    @Override
    public int voltageOutPut5V() {
        int srcV = 0;
        if(voltageOutPut220V != null){
            srcV = voltageOutPut220V.voltageOutPut220V();
        }
        int desV = srcV / 44;
        return desV;
    }
}

class Phone{
    public void charging(IVoltageOutPut5V voltageOutPut5V){
        if(voltageOutPut5V.voltageOutPut5V() == 5){
            System.out.println("电压为5V,可以充电........");
        }else{
            System.out.println("电压不正常.........");
        }
    }
}

public class AdapterPatternOfObject {
    public static void main(String[] args) {
        Phone phone = new Phone();
        phone.charging(new VoltageOutPutAdapter(new VoltageOutPut220V()));
    }
}
