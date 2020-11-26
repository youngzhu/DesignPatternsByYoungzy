package sourcemaking.structural.bridge.example2;

/*
3. Create an interface/handle class that "has a" and delegates to the impl
 */
public class Stack {
    private IStack impl;

    public Stack(String s) {
        if (s.equals("array")) {
            impl = new StackArray();
        } else if (s.equals("list")) {
            impl = new StackList();
        } else {
            System.out.println("Stack: unknown parameter");
        }
    }

    public Stack() {
        this("array");
    }

    public void push(int i) {
        impl.push(i);
    }

    public int pop() {
        return impl.pop();
    }

    public int top() {
        return impl.top();
    }

    public boolean isEmpty() {
        return impl.isEmpty();
    }

    public boolean isFull() {
        return impl.isFull();
    }
}

class StackHanoi extends Stack {
    private int totalRejected = 0;

    public StackHanoi() {
        super("array");
    }

    public StackHanoi(String s) {
        super(s);
    }

    /*
    4. Embellish the interface class with derived classes if desired
     */
    public int reportRejected() {
        return totalRejected;
    }

    @Override
    public void push(int i) {
        if (! isEmpty() && i > top()) {
            totalRejected ++;
        } else
            super.push(i);
    }
}

class StackFIFO extends Stack {
    private IStack stackImpl = new StackList();

    public StackFIFO() {
        super("array");
    }

    public StackFIFO(String s) {
        super(s);
    }

    @Override
    public int pop() {
        while (! isEmpty()) {
            stackImpl.push(super.pop());
        }
        int ret = stackImpl.pop();
        while (! stackImpl.isEmpty()) {
            push(stackImpl.pop());
        }
        return ret;
    }
}
