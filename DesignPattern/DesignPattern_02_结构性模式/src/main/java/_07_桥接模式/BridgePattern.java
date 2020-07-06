package _07_桥接模式;

/**
 * @Classname BridgePattern
 * @Description 桥接模式
 * @Created by XJC·AW
 * @Date 2020/7/2 13:03
 * @Version V1.0.0
 * @Since 1.0
 */
interface Brand{
    void open();
    void call();
    void close();
}

class XiaoMi implements Brand {
    @Override
    public void open() {
        System.out.println("小米手机开机");
    }

    @Override
    public void call() {
        System.out.println("小米手机打电话");
    }

    @Override
    public void close() {
        System.out.println("小米手机关机");
    }
}

class ViVo implements Brand{
    @Override
    public void open() {
        System.out.println("ViVo手机开机");
    }

    @Override
    public void call() {
        System.out.println("ViVo手机打电话");
    }

    @Override
    public void close() {
        System.out.println("ViVo手机关机");
    }
}

abstract class Phone{
    private Brand brand;

    public Phone(Brand brand) {
        this.brand = brand;
    }

    protected void open(){
        this.brand.open();
    }

    protected void call(){
        this.brand.call();
    }

    protected void close(){
        this.brand.close();
    }
}

class FoldStyle extends Phone{

    public FoldStyle(Brand brand) {
        super(brand);
        System.out.println("折叠样式手机");
    }

    protected void open(){
        super.open();
    }

    protected void call(){
        super.call();
    }

    protected void close() {
        super.close();
    }

}

public class BridgePattern {
    public static void main(String[] args) {
        Phone phone = new FoldStyle(new XiaoMi());
        phone.open();
        phone.call();
        phone.close();
    }
}
