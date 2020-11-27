package sourcemaking.structural.composite.example1.before;

import java.util.ArrayList;

public class Directory {
    private String name;
    private ArrayList includeFiles = new ArrayList();

    public Directory(String name) {
        this.name = name;
    }

    public void add(Object o) {
        includeFiles.add(o);
    }

    public void ls() {
        System.out.println(CompositeDemo.compositeBuilder + name);
        CompositeDemo.compositeBuilder.append("    ");
        for (Object obj : includeFiles) {
            // recover the type of this object
            String name = obj.getClass().getSimpleName();
            if (name.equals("Directory")) {
                ((Directory)obj).ls();
            } else {
                ((File)obj).ls();
            }
        }

        CompositeDemo.compositeBuilder.setLength(CompositeDemo.compositeBuilder.length() - 3);
    }
}
