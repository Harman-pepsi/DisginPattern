package _04_建造者模式;

/**
 * @Classname BuilderPattern
 * @Description
 * @Date 2020/6/13 2:14
 * @Created by Harman
 */
//产品Product
class Hource{
    //地基
    private String basic;
    //墙
    private String wall;
    //封顶
    private String roofed;

    public String getBasic() {
        return basic;
    }

    public void setBasic(String basic) {
        this.basic = basic;
    }

    public String getWall() {
        return wall;
    }

    public void setWall(String wall) {
        this.wall = wall;
    }

    public String getRoofed() {
        return roofed;
    }

    public void setRoofed(String roofed) {
        this.roofed = roofed;
    }
}

//建造者Builder
abstract class HourceBuilder{
    protected Hource hource = new Hource();
    //打地基
    abstract void buildBasic();
    //砌墙
    abstract void buildWalls();
    //封顶
    abstract void roofed();

    //将建造好的房子返回
    public Hource finishedHource(){
        return hource;
    }
}

//指挥者Dirctor
class HourceDirector{
    HourceBuilder hourceBuilder = null;

    //构造器传入HourceBuilder
    public HourceDirector(HourceBuilder hourceBuilder) {
        this.hourceBuilder = hourceBuilder;
    }

    //通过setter传入HourceBuilder
    public void setHourceBuilder(HourceBuilder hourceBuilder) {
        this.hourceBuilder = hourceBuilder;
    }

    //如何处理建造房子的流程，交给指挥者
    public Hource constructHource(){
        hourceBuilder.buildBasic();
        hourceBuilder.buildWalls();
        hourceBuilder.roofed();
        return hourceBuilder.finishedHource();
    }
}

class CommonHource extends HourceBuilder{

    @Override
    void buildBasic() {
        System.out.println("普通房子打2米地基");
    }

    @Override
    void buildWalls() {
        System.out.println("普通房子打50厘米墙");
    }

    @Override
    void roofed() {
        System.out.println("普通房子屋顶");
    }
}

class HightHource extends HourceBuilder{

    @Override
    void buildBasic() {
        System.out.println("高楼大厦打30米地基");
    }

    @Override
    void buildWalls() {
        System.out.println("高楼大厦砌100厘米墙");
    }

    @Override
    void roofed() {
        System.out.println("高楼大厦屋顶");
    }
}

public class BuilderPattern {
    public static void main(String[] args) {
        //盖普通的房子
        CommonHource commonHource = new CommonHource();
        //创建房子的指挥者
        HourceDirector hourceDirector = new HourceDirector(commonHource);
        //完成房子，返回商品
        hourceDirector.constructHource();

        HightHource hightHource = new HightHource();
        hourceDirector.setHourceBuilder(hightHource);
        hourceDirector.constructHource();
    }
}
