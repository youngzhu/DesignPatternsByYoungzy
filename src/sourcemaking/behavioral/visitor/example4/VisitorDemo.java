package sourcemaking.behavioral.visitor.example4;

import java.util.ArrayList;
import java.util.List;

/*

 */
interface Component {
    void traverse();
}

class Leaf implements Component {
    private int number;

    public Leaf(int number) {
        this.number = number;
    }

    @Override
    public void traverse() {
        System.out.print(number + " ");
    }
}

class Composite implements Component {
    private static char next = 'a';
    private List children = new ArrayList();
    private char letter = next++;

    public void add(Component component) {
        children.add(component);
    }

    @Override
    public void traverse() {
        System.out.print(letter + " ");
        for (Object child : children) {
            ((Component)child).traverse();
        }
    }
}

public class VisitorDemo {
    public static void main(String[] args) {
        Composite[] containers = new Composite[3];
        for (int i = 0; i < containers.length; i++) {
            containers[i] = new Composite();
            for (int j = 1; j < 4; j++) {
                containers[i].add(new Leaf(i * containers.length + j));
            }
        }
        for (int i = 1; i < containers.length; i++)
            containers[0].add(containers[i]);
        containers[0].traverse();
        System.out.println();
    }
}
