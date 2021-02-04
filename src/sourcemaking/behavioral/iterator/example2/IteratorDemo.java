package sourcemaking.behavioral.iterator.example2;

/*
1. 给集合类设计一个内部的迭代器
2. 给集合类添加一个 createIterator() 方法
3. 客户端请求集合类创建一个迭代器
4. 客户端使用迭代器的 first(), isDone(), next(), currentItem() 方法
 */

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.NoSuchElementException;

class IntSet {
    private Hashtable ht = new Hashtable();

    // 1. 设计一个迭代器
    static class Iterator {
        private IntSet set;
        private Enumeration e;
        private Integer current;

        public Iterator(IntSet set) {
            this.set = set;
        }

        public void first() {
            e = set.ht.keys();
            next();
        }

        public void next() {
            try {
                current = (Integer) e.nextElement();
            } catch (NoSuchElementException e) {
                current = null;
            }
        }

        public boolean isDone() {
            return current == null;
        }

        public int currentItem() {
            return current;
        }
    }

    public void add(int i) {
        ht.put(i, "null");
    }

    public boolean isMember(int i) {
        return ht.containsKey(i);
    }

    public Hashtable getHashtable() {
        return ht;
    }

    // 2. 创建迭代器的方法
    public Iterator createIterator() {
        return new Iterator(this);
    }
}

public class IteratorDemo {
    public static void main(String[] args) {
        IntSet set = new IntSet();
        for (int i = 2; i < 10; i+=2)
            set.add(i);
        for (int i = 1; i < 9; i++)
            System.out.print(i + "-" + set.isMember(i) + " ");

        // 3 客户端请求获得迭代器
        IntSet.Iterator it1 = set.createIterator();
        IntSet.Iterator it2 = set.createIterator();

        // 4 客户端使用迭代器
        System.out.print("\nIterator: ");
        for (it1.first(), it2.first(); ! it1.isDone(); it1.next(), it2.next())
            System.out.print(it1.currentItem() + " " + it2.currentItem() + ",");

        // Java 还有另外一种枚举的方式
        System.out.print("\nEnumeration: ");
        for (Enumeration e = set.getHashtable().keys(); e.hasMoreElements();)
            System.out.print(e.nextElement() + " ");
    }
}
