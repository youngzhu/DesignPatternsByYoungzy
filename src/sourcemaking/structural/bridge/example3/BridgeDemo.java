package sourcemaking.structural.bridge.example3;

/*
Create an interface/wrapper class that "has a"
implementation object and delegates all requests to it
 */
class Stack {
    protected IStack impl;

    public Stack(String s) {
        if (s.equals("java"))
            impl = new StackJava();
        else
            impl = new StackMine();
    }

    public Stack() {
        this("java");
    }

    public void push(int i) {
        impl.push(i);
    }

    public int pop() {
        return (Integer) impl.pop();
    }

    public boolean isEmpty() {
        return impl.isEmpty();
    }
}

/*
Embellish the interface class with derived classes if desired
 */
class StackHanoi extends Stack {
    private int totalRejected = 0;

    public StackHanoi() {
        super("java");
    }

    public StackHanoi(String s) {
        super(s);
    }

    public int reportRejected() {
        return totalRejected;
    }

    public void push(int i) {
        if (! impl.isEmpty() && i > (Integer)impl.peek()) {
            totalRejected ++;
        } else {
            impl.push(i);
        }
    }
}

/*
Create an implementation/body base class
 */
interface IStack {
    // push要返回，否则 StackJava 会报错
    Object push(Object o);
    Object peek();
    boolean isEmpty();
    Object pop();
}

class StackJava extends java.util.Stack implements IStack {}

/*
Derive the separate implementations from the common abstraction
 */
class StackMine implements IStack {
    private Object[] items = new Object[20];
    private int total = -1;

    @Override
    public Object push(Object o) {
        return items[++total] = o;
    }

    @Override
    public Object peek() {
        return items[total];
    }

    @Override
    public boolean isEmpty() {
        return total == -1;
    }

    @Override
    public Object pop() {
        return items[total--];
    }
}

public class BridgeDemo {
    public static void main(String[] args) {
        Stack[] stacks = {new Stack("java"), new Stack("mine"),
                new StackHanoi("java"), new StackHanoi("mine")};

        for (int i = 0, num; i < 20; i++) {
            num = (int)(Math.random() * 1000) % 40;
            for (Stack stack : stacks) {
                stack.push(num);
            }
        }
        for (int i = 0, num; i < stacks.length; i++) {
            while (! stacks[i].isEmpty()) {
                System.out.print(stacks[i].pop() + " ");
            }
            System.out.println();
        }

        System.out.println("total rejected is " + ((StackHanoi)stacks[3]).reportRejected());
    }
}
