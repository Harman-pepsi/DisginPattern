package _08_适配器模式._3_接口适配器模式;

public interface Controller {
}

class SimpleController implements Controller{
    public void doSimpleController(){
        System.out.println("doSimpleController.......");
    }
}

class HttpController implements Controller{
    public void doHttpController(){
        System.out.println("doHttpController.......");
    }
}

class AnnoationController implements Controller{
    public void doAnnoationController(){
        System.out.println("doAnnoationController.......");
    }
}
