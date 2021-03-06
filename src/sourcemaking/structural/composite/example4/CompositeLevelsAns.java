package sourcemaking.structural.composite.example4;

import java.util.ArrayList;
import java.util.List;

abstract class Entity {
    protected static StringBuffer indent = new StringBuffer();
    protected static int level = 1;

    abstract void traverse(int[] levels);

    protected boolean printThisLevel(int[] levels) {
        for (int i = 0; i < levels.length; i++) {
            if (level == levels[i]) {
                return true;
            }
        }
        return false;
    }
}

class Product extends Entity {
    private int value;
    public Product(int value) {
        this.value = value;
    }

    @Override
    void traverse(int[] levels) {
        if (printThisLevel(levels))
            System.out.println(indent.toString() + value);
    }
}

class Box extends Entity {
    private List<Entity> children = new ArrayList<>();
    private int value;
    public Box(int value) {
        this.value = value;
    }

    public void add(Entity c) {
        children.add(c);
    }

    @Override
    void traverse(int[] levels) {
        if (printThisLevel(levels)) {
            System.out.println(indent.toString() + value);
            indent.append("  ");
        }

        level ++;

        for (int i = 0; i < children.size(); i++)
            children.get(i).traverse(levels);

        level --;

        if (printThisLevel(levels))
            indent.setLength(indent.length() - 2);
    }
}

public class CompositeLevelsAns {
    public static void main(String[] args) {
        Box root = init();
        int[] levels = new int[args.length];
        for (int i = 0; i < args.length; i++)
            levels[i] = Integer.parseInt(args[i]);

        root.traverse(levels);
    }

    private static Box init() {
        Box[] nodes = new Box[7];
        nodes[1] = new Box(7);
        int[] s = {1, 4, 7};
        for (int i = 0; i < 3; i++) {
            nodes[2] = new Box(21 + i);
            nodes[1].add(nodes[2]);
            int lev = 3;
            for (int j = 0; j < 4; j++) {
                nodes[lev - 1].add(new Product(lev * 10 + s[i]));
                nodes[lev] = new Box(lev * 10 + s[i] + 1);
                nodes[lev - 1].add(nodes[lev]);
                nodes[lev - 1].add(new Product(lev * 10 + s[i] + 2));
                lev++;
            }
        }

        return nodes[1];
    }
}
