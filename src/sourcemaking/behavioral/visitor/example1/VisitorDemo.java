package sourcemaking.behavioral.visitor.example1;

/*
1. 在 Element 的层次结构中添加一个 accept(Visitor)方法
2. 创建一个 Visitor 基类，为每一个 Element 类型的对象添加 visit 方法
3. 为不同的 Element 对象不同的操作创建 Visitor 的衍生类
4. 客户端创建 Visitor 对象，并通过 accept 方法调用
 */

interface Element {
    void accept(Visitor visitor);
}

class Foo implements Element {
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public String getFoo() {
        return "FOO";
    }
}

class Bar implements Element {
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public String getBar() {
        return "BAR";
    }
}

class Baz implements Element {
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public String getBaz() {
        return "BAZ";
    }
}

interface Visitor {
    void visit(Foo foo);
    void visit(Bar bar);
    void visit(Baz baz);
}

class UpVisitor implements Visitor {
    @Override
    public void visit(Foo foo) {
        System.out.println("do up on " + foo.getFoo());
    }

    @Override
    public void visit(Bar bar) {
        System.out.println("do up on " + bar.getBar());
    }

    @Override
    public void visit(Baz baz) {
        System.out.println("do up on " + baz.getBaz());
    }
}


class DownVisitor implements Visitor {
    @Override
    public void visit(Foo foo) {
        System.out.println("do down on " + foo.getFoo());
    }

    @Override
    public void visit(Bar bar) {
        System.out.println("do down on " + bar.getBar());
    }

    @Override
    public void visit(Baz baz) {
        System.out.println("do down on " + baz.getBaz());
    }
}
public class VisitorDemo {
    public static void main(String[] args) {
        Element[] list = {new Foo(), new Bar(), new Baz()};
        UpVisitor up = new UpVisitor();
        DownVisitor down = new DownVisitor();
        for (Element element : list) {
            element.accept(up);
        }
        for (Element element : list) {
            element.accept(down);
        }
    }
}
