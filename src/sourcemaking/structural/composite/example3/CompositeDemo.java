package sourcemaking.structural.composite.example3;

/*
1. 从各个类中抽取出一个具有共通性的接口，以便各个类之间可以互相替换
2. 所有相关类都实现了这个接口
3. 在“容器”类中引用这个接口
4. “容器”类使用多态将具体实现委托给子类
 */

// 1 具有共通性的接口
interface Component {
    void traverse();
}

// 2 is-a 的关系
class Primitive implements Component {
    private int value;

    public Primitive(int value) {
        this.value = value;
    }

    @Override
    public void traverse() {
        System.out.print(value + "  ");
    }
}

// 2 is-a
abstract class Composite implements Component {
    // 3 引用接口
    private Component[] children = new Component[9];
    private int total = 0;
    private int value;

    public Composite(int value) {
        this.value = value;
    }

    public void add(Component c) {
        children[total++] = c;
    }

    @Override
    public void traverse() {
        System.out.print(value + "  ");
        for (int i = 0; i < total; i++) {
            // 委托给子类
            children[i].traverse();
        }
    }
}

// 2种不同的“容器”类
// 主要内容已在基类Composite里了
class Row extends Composite {

    public Row(int value) {
        super(value);
    }

    @Override
    public void traverse() {
        System.out.print("Row");
        super.traverse();
    }
}
class Column extends Composite {

    public Column(int value) {
        super(value);
    }

    @Override
    public void traverse() {
        System.out.print("Col");
        super.traverse();
    }
}


public class CompositeDemo {
    public static void main(String[] args) {
        Composite first = new Row(1);
        Composite second = new Column(2);
        Composite third = new Column(3);
        Composite forth = new Row(4);
        Composite fifth = new Row(5);

        first.add(second);
        first.add(third);
        third.add(forth);
        third.add(fifth);

        first.add(new Primitive(6));
        second.add(new Primitive(7));
        third.add(new Primitive(8));
        forth.add(new Primitive(9));
        fifth.add(new Primitive(10));

        first.traverse();
    }
}
