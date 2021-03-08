package sourcemaking.behavioral.visitor.example3;

/*
观察者-组合模式

讨论。我们可能会定义这样一个方法：void process(virtual Base obj1, virtual Base obj2)，
2个对象都来自同样的继承结构。问题是关键字 virtual 不能要求对传入的参数进行动态绑定。
Java仅能识别已经传过来的对象类型，而不能识别传送中的。

所以，为了识别这两个对象的类型，每一个对象必须是一个虚拟方法调用的接收者。
这样的话，当第一个对象调用 process1() 方法时，它的类型在运行时是已知的，而第二个参数仍是未知的。
然后第二个对象调用 process2() 方法，第一个对象的类型被作为参数传递。现在控制流就知道这两个对象的类型了。
 */

import java.io.ByteArrayInputStream;

interface Base {
    void execute(Base target);
    void doJob(Foo foo);
    void doJob(Bar bar);
    void doJob(Baz baz);
}

class Foo implements Base {
    @Override
    public void execute(Base target) {
        target.doJob(this);
    }
    @Override
    public void doJob(Foo foo) {
        System.out.println("Foo object calls by yourself");
    }

    @Override
    public void doJob(Bar bar) {
        System.out.println("Bar object was called From Foo");
    }

    @Override
    public void doJob(Baz baz) {
        System.out.println("Baz object was called From Foo");
    }
}

class Bar implements Base {
    @Override
    public void execute(Base target) {
        target.doJob(this);
    }

    @Override
    public void doJob(Foo foo) {
        System.out.println("Foo object was called From Bar");
    }

    @Override
    public void doJob(Bar bar) {
        System.out.println("Bar object calls by yourself");
    }

    @Override
    public void doJob(Baz baz) {
        System.out.println("Baz object was called From Bar");
    }
}


class Baz implements Base {
    @Override
    public void execute(Base target) {
        target.doJob(this);
    }

    @Override
    public void doJob(Foo foo) {
        System.out.println("Foo object was called From Baz");
    }

    @Override
    public void doJob(Bar bar) {
        System.out.println("Bar object was called From Baz");
    }

    @Override
    public void doJob(Baz baz) {
        System.out.println("Baz object calls by yourself");
    }
}
public class VisitorDemo {
    public static void main(String[] args) {
        Base objects[] = {new Foo(), new Bar(), new Baz()};
        for (Base obj : objects) {
            for (int j = 0; j < 3; j++) {
                obj.execute(objects[j]);
            }
            System.out.println();
        }
    }
}
