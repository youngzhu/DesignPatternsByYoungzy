package sourcemaking.structural.bridge.example1;

class StackArray {
    private int[] items;
    private int size = -1;

    public StackArray() {
        this.items = new int[12];
    }

    public StackArray(int cells) {
        this.items = new int[cells];
    }

    public void push(int i) {
        if (! isFull()) {
            items[++size] = i;
        }
    }

    public boolean isEmpty() {
        return size == -1;
    }

    public boolean isFull() {
        return size == items.length - 1;
    }

    public int top() {
        if (isEmpty()) {
            return -1;
        }
        return items[size];
    }

    public int pop() {
        if (isEmpty()) {
            return -1;
        }
        return items[size--];
    }
}

class StackFIFO extends StackArray {
    private StackArray stackArray = new StackArray();

    public int pop() {
        while (! isEmpty()) {
            stackArray.push(super.pop());
        }

        int ret = stackArray.pop();
        while (! stackArray.isEmpty()) {
            push(stackArray.pop());
        }

        return ret;
    }
}

class StackHanoi extends StackArray {
    private int totalRejected = 0;

    public int reportRejected() {
        return totalRejected;
    }

    @Override
    public void push(int i) {
        if (! isEmpty() && i > top()) {
            totalRejected++;
        } else
            super.push(i);
    }
}