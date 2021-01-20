package sourcemaking.behavioral.command.example2;

/*
Java反射和命令设计模式
动机：有时候需要在不知道请求的具体操作或请求的接收者时发起一个请求。命令模式建议将以下所有（或部分）
封装进一个对象里：一个对象，一个方法名和一些参数。Java不支持“函数指针”，但是它的反射机制可以做的很好。
“命令”对于调用者来说是一个黑盒。所有调用者要做的就是调它的“execute”方法。
 */

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class SimpleCommand {
    private int state;

    public SimpleCommand(int state) {
        this.state = state;
    }

    // 不能直接用 int
    // 虽然编译不报错，但是反射时 需要严格一致
    public void add(Integer value) {
        state += value;
    }

    public void addTwoOperands(Integer first, Integer second) {
        state = state + first + second;
    }

    public int getState() {
        return state;
    }
}

class ReflectCommand {
    // 封装的对象
    private Object receiver;
    // 预注册的请求
    private Method action;
    // 预注册的参数
    private Object[] args;

    public ReflectCommand(Object receiver, String methodName, Object[] args) {
        this.receiver = receiver;
        this.args = args;
        Class clz = receiver.getClass();
        Class[] argTypes = new Class[args.length];
        for (int i = 0; i < args.length; i++) {
            argTypes[i] = args[i].getClass();
        }

        try {
            action = clz.getMethod(methodName, argTypes);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public Object execute() {
        try {
            action.invoke(receiver, args);
            return receiver.getClass().getMethod("getState").invoke(receiver);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException exception) {
            exception.printStackTrace();
        }
        return null;
    }

}

public class CommandDemo {
    public static void main(String[] args) {
        SimpleCommand[] simpleCommands = {new SimpleCommand(1), new SimpleCommand(2)};
        System.out.print("Normal call results: ");
        simpleCommands[0].add(3);
        System.out.print(simpleCommands[0].getState() + " ");
        simpleCommands[1].addTwoOperands(4, 5);
        System.out.print(simpleCommands[1].getState());

        ReflectCommand[] reflectCommands = {
                new ReflectCommand(simpleCommands[0], "add", new Integer[] {3}),
                new ReflectCommand(simpleCommands[1], "addTwoOperands", new Integer[] {4, 5})
        };
        System.out.print("\nReflection result: ");
        for (ReflectCommand command : reflectCommands) {
            System.out.print(command.execute() + " ");
        }
    }
}
