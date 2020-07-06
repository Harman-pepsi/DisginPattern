package _05_组合模式;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname CombinationPattern
 * @Description 组合模式
 * @Created by XJC·AW
 * @Date 2020/7/6 15:13
 * @Version V1.0.0
 * @Since 1.0
 */
abstract class OrganizztionComponent{
    private String name;
    private String des;

    protected void add(OrganizztionComponent organizztionComponent){
       throw new UnsupportedOperationException();
    }

    protected void remove(OrganizztionComponent organizztionComponent){
        throw new UnsupportedOperationException();
    }

    public OrganizztionComponent(String name, String des) {
        this.name = name;
        this.des = des;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    protected abstract void print();
}

class University extends OrganizztionComponent{

    List<OrganizztionComponent> organizztionComponentList = new ArrayList<>();

    public University(String name, String des) {
        super(name, des);
    }

    @Override
    protected void add(OrganizztionComponent organizztionComponent) {
        organizztionComponentList.add(organizztionComponent);
    }

    @Override
    protected void remove(OrganizztionComponent organizztionComponent) {
        organizztionComponentList.remove(organizztionComponent);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public String getDes() {
        return super.getDes();
    }

    @Override
    protected void print() {
        System.out.println("------------"+ getName()+"------------");
        for (OrganizztionComponent organizztionComponent : organizztionComponentList) {
            organizztionComponent.print();
        }
    }
}

class College extends OrganizztionComponent{

    List<OrganizztionComponent> organizztionComponentList = new ArrayList<>();

    public College(String name, String des) {
        super(name, des);
    }

    @Override
    protected void add(OrganizztionComponent organizztionComponent) {
        organizztionComponentList.add(organizztionComponent);
    }

    @Override
    protected void remove(OrganizztionComponent organizztionComponent) {
        organizztionComponentList.remove(organizztionComponent);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public String getDes() {
        return super.getDes();
    }

    @Override
    protected void print() {
        System.out.println("------------"+ getName()+"------------");
        for (OrganizztionComponent organizztionComponent : organizztionComponentList) {
            organizztionComponent.print();
        }
    }
}

class Department extends OrganizztionComponent{

    public Department(String name, String des) {
        super(name, des);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public String getDes() {
        return super.getDes();
    }

    @Override
    protected void print() {
        System.out.println(getName());
    }
}

public class CombinationPattern {
    public static void main(String[] args) {
        //大学
        OrganizztionComponent university = new University("清华大学","还行");

        //学院
        OrganizztionComponent computerCollege = new College("计算机学院", "计算机学院");
        OrganizztionComponent infoCollege = new College("信息工程学院", "信息工程学院");

        //系
        computerCollege.add(new Department("软件工程","软件工程不错"));
        computerCollege.add(new Department("网络工程","网络工程不错"));
        computerCollege.add(new Department("计算机科学与技术","计算机科学与技术不错"));

        infoCollege.add(new Department("信息工程","信息工程很好学"));
        infoCollege.add(new Department("通信工程","通信工程很难学"));

        university.add(computerCollege);
        university.add(infoCollege);

        university.print();

    }
}
