package sourcemaking.behavioral.iterator.example1.before;
/*
一些含有数据的类提供了访问其内部数据结构的权限。客户端可以偶然或恶意地破坏数据结构。
 */

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

class IntegerBox {
    private final List<Integer> list = new ArrayList<>();

    public void add(int in) {
        list.add(in);
    }

    public List getData() {
        return list;
    }
}

public class IteratorDemo {
    public static void main(String[] args) {
        IntegerBox box = new IntegerBox();
        for (int i = 9; i > 0; --i) {
            box.add(i);
        }

        Collection integerList = box.getData();
        for (Object anInteger : integerList) {
            System.out.print(anInteger + " ");
        }
        System.out.println();
        integerList.clear();
        integerList = box.getData();
        System.out.println("size of data is " + integerList.size());
    }
}
