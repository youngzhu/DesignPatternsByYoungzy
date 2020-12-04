package sourcemaking.structural.decorator.example3;

/*
1. 找到最小的共通性使得各类之间可以互相替换
2. 为可选操作（方法）创建第二层的基类
3. 核心类和装饰类之间是“is-a”的关系
4. 装饰类含有一个接口的引用
5. 装饰类将职责委托给接口的引用对象
6. 为每一种可能的装饰建一个衍生类（继承子装饰类）
7. 各衍生类将职责委托给基类（super.do()），再加上额外的操作
8. 客户端根据需求去组合使用
 */

// 1. 最低通用性的接口
interface Widget {
    void draw();
}

// 3. 核心类
class TextField implements Widget {
    private int width, height;

    public TextField(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw() {
        System.out.println("TextField: " + width + ", " + height);
    }
}

// 2. 二级类
abstract class Decorator implements Widget {
    // 4. 包含一个引用
    private Widget widget;

    public Decorator(Widget widget) {
        this.widget = widget;
    }

    // 5. 委托
    @Override
    public void draw() {
        widget.draw();
    }
}

// 6. 可选的装饰
class BorderDecorator extends Decorator {
    public BorderDecorator(Widget widget) {
        super(widget);
    }

    @Override
    public void draw() {
        // 7. 委托给基类，并增加其他操作
        super.draw();
        System.out.println("Border Decorator");
    }
}

// 6. 可选的装饰
class ScrollDecorator extends Decorator {
    public ScrollDecorator(Widget widget) {
        super(widget);
    }

    @Override
    public void draw() {
        // 7.
        super.draw();
        System.out.println("Scroll Decorator");
    }
}

public class DecoratorDemo {
    public static void main(String[] args) {
        // 8. 客户端组合使用
        Widget widget = new BorderDecorator(new BorderDecorator(new ScrollDecorator(new TextField(30, 40))));
        widget.draw();
    }
}
