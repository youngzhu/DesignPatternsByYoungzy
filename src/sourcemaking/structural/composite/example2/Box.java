package sourcemaking.structural.composite.example2;

import java.util.ArrayList;
import java.util.List;

public class Box extends Entity {
    private List children = new ArrayList();
    private int value;

    public Box(int value) {
        this.value = value;
    }

    public void add(Entity c) {
        children.add(c);
    }

    @Override
    public void traverse(int[] levels) {
        if (printThisLevel(levels)) {
            System.out.println(indent.toString() + value);
            indent.append("    ");
        }
        level ++;
        for (Object child : children) {
            ((Entity)child).traverse(levels);
        }
        level --;
        if (printThisLevel(levels)) {
            indent.setLength(indent.length() - 4);
        }

    }
}
