package _08_适配器模式._3_接口适配器模式;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname DispatchServlet
 * @Description
 * @Created by XJC·AW
 * @Date 2020/7/2 11:46
 * @Version V1.0.0
 * @Since 1.0
 */
public class DispatchServlet {
    private List<HandlerAdapter> handlerAdapterList = new ArrayList<>();

    public DispatchServlet() {
        handlerAdapterList.add(new SimpleHanderAdpater());
        handlerAdapterList.add(new HttpHandlerAdapter());
        handlerAdapterList.add(new AnnotaionHandlerAdapter());
    }

    public void doDispatch(){
        AnnoationController controller = new AnnoationController();
        HandlerAdapter handler = getHandler(controller);
        handler.handle(controller);

    }

    public HandlerAdapter getHandler(Controller controller){
        for (HandlerAdapter handlerAdapter : handlerAdapterList) {
            if (handlerAdapter.support(controller)){
                return handlerAdapter;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        new DispatchServlet().doDispatch();
    }
}
