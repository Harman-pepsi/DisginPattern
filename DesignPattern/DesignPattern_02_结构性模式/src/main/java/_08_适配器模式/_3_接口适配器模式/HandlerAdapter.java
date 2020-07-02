package _08_适配器模式._3_接口适配器模式;

public interface HandlerAdapter {
    public boolean support(Object handler);
    public void handle(Object handler);
}

class SimpleHanderAdpater implements HandlerAdapter{
    @Override
    public boolean support(Object handler) {
        return (handler instanceof SimpleController);
    }

    @Override
    public void handle(Object handler) {
        ((SimpleController)handler).doSimpleController();
    }
}

class HttpHandlerAdapter implements HandlerAdapter{
    @Override
    public boolean support(Object handler) {
        return (handler instanceof HttpController);
    }

    @Override
    public void handle(Object handler) {
        ((HttpController)handler).doHttpController();
    }
}

class AnnotaionHandlerAdapter implements HandlerAdapter{
    @Override
    public boolean support(Object handler) {
        return (handler instanceof AnnoationController);
    }

    @Override
    public void handle(Object handler) {
        ((AnnoationController)handler).doAnnoationController();
    }
}
