package sourcemaking.structural.bridge.example2;

import sourcemaking.structural.bridge.Node;

/*
1. Create an implementation/body abstraction
 */
public interface IStack {
    void push(int i);
    int pop();
    int top();
    boolean isEmpty();
    boolean isFull();
}

/*
2. Derive the separate implementations from the common abstraction
 */
class StackList implements IStack {
    private Node last;

    @Override
    public void push(int i) {
        if (last == null) {
            last = new Node(i);
        } else {
            last.next = new Node(i);
            last.next.prev = last;
            last = last.next;
        }
    }

    @Override
    public int pop() {
        if (isEmpty()) {
            return -1;
        }
        int ret = last.value;
        last = last.prev;
        return ret;
    }

    @Override
    public int top() {
        if (isEmpty()) {
            return -1;
        }
        return last.value;
    }

    @Override
    public boolean isEmpty() {
        return last == null;
    }

    @Override
    public boolean isFull() {
        return false;
    }
}

class StackArray implements IStack {
    private int[] items;
    private int total = -1;

    public StackArray() {
        this.items = new int[12];
    }

    public StackArray(int size) {
        this.items = new int[size];
    }

    @Override
    public void push(int i) {
        if (! isFull()) {
            items[++ total] = i;
        }
    }

    @Override
    public int pop() {
        if (isEmpty()) {
            return -1;
        }
        return items[total--];
    }

    @Override
    public int top() {
        if (isEmpty()) {
            return -1;
        }
        return items[total];
    }

    @Override
    public boolean isEmpty() {
        return total == -1;
    }

    @Override
    public boolean isFull() {
        return total == items.length - 1;
    }
}
