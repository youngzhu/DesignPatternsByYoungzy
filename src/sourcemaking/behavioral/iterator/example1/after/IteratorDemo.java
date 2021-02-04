package sourcemaking.behavioral.iterator.example1.after;

/*
将遍历集合的功能从集合中剥离出来，将它提升为一个对象属性。
这简化了集合，允许多个遍历同时发生，并将集合算法跟数据结构解耦。
 */

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

class IntegerBox {
    private List<Integer> list = new ArrayList<>();

    class Iterator {
        private IntegerBox box;
        private java.util.Iterator iterator;
        private int value;

        public Iterator(IntegerBox box) {
            this.box = box;
        }

        public void first() {
            iterator = box.list.iterator();
            next();
        }

        public void next() {
            try {
                value = (Integer)iterator.next();
            } catch (NoSuchElementException e) {
                value = -1;
            }
        }

        public boolean isDone() {
            return value == -1;
        }

        public int currentValue() {
            return value;
        }
    }

    public void add(int in) {
        list.add(in);
    }

    public Iterator getIterator() {
        return new Iterator(this);
    }
}

public class IteratorDemo {
    public static void main(String[] args) {
        IntegerBox box = new IntegerBox();
        for (int i = 9; i > 0; --i)
            box.add(i);

        // getData() 被移除了
        // 必须使用 Iterator
        IntegerBox.Iterator firstItr = box.getIterator();
        IntegerBox.Iterator secondItr = box.getIterator();
        for (firstItr.first(); ! firstItr.isDone(); firstItr.next())
            System.out.print(firstItr.currentValue() + " ");
        System.out.println();
        // 两个同时遍历
        for (firstItr.first(), secondItr.first(); ! firstItr.isDone(); firstItr.next(), secondItr.next())
            System.out.print(firstItr.currentValue() + " " + secondItr.currentValue() +",");

    }
}
