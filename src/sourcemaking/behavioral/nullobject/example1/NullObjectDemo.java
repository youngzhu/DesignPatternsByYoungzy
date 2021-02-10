package sourcemaking.behavioral.nullobject.example1;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

/*
用一个实现了特定接口但什么都不做的对象替代一个空的引用，来表示一个对象的缺失，如一个实例或一个不存在的客户。
用“空对象”作为一种模式实现的方式的好处是它是可预测的且没有副作用，因为它什么都没做。

举个例子，有一个获取一个文件夹中的文件并逐个处理的方法。
如果有一个空文件夹，可能会获得一个异常或者一个空的引用，而不是一个列表。
因此，那块希望获得一个列表的代码在继续之前必须校验一下，这可能使得其设计变得复杂。
如果返回一个空对象（如空的列表），就没必要校验这个返回的值是不是一个列表了。
对这个方法的调用只是简单的跟往常一样的遍历，简洁而高效。
当然，如果返回了空对象需要有不同的处理，还是要校验的。

在测试中，空对象模式可以充当桩（好别扭啊，不知道是啥），在某些资源（如数据库）不可用的时候。
 */
class NullOutputStream extends OutputStream {
    @Override
    public void write(int b) throws IOException {
        // do nothing
    }
}

class NullPrintStream extends PrintStream {
    public NullPrintStream() {
        super(new NullOutputStream());
    }
}

class Application {
    private PrintStream debugOut;

    public Application(PrintStream debugOut) {
        this.debugOut = debugOut;
    }

    public void doSomething() {
        int sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += i;
            debugOut.println("i = " + i);
        }
        System.out.println("sum = " + sum);
    }
}

public class NullObjectDemo {
    public static void main(String[] args) {
        Application app = new Application(new NullPrintStream());
        app.doSomething();
    }
}
