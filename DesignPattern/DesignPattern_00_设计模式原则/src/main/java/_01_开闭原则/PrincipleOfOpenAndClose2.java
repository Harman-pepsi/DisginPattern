package _01_开闭原则;

/**
 * @Classname PrincipleOfOpenAndClose2 开闭原则
 * @Description
 * @Date 2020/6/24 15:28
 * @Created by Harman
 */
abstract class Shap{
    int m_Type;
    public abstract void draw();
}

class Rectangle extends Shap{
    public Rectangle() {
        super.m_Type = 1;
    }

    @Override
    public void draw() {
        System.out.println("画矩形!");
    }
}

class Circle extends Shap{
    public Circle() {
        super.m_Type = 2;
    }

    @Override
    public void draw() {
        System.out.println("画圆形!");
    }
}

class GraphicEditor{
    public void drawShap(Shap s){
        System.out.println("m_Type:" + s.m_Type);
        s.draw();
    }
}

public class PrincipleOfOpenAndClose2 {
    public static void main(String[] args) {
       GraphicEditor editor = new GraphicEditor();
       editor.drawShap(new Rectangle());
       editor.drawShap(new Circle());
    }
}
