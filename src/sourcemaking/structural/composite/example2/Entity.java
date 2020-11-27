package sourcemaking.structural.composite.example2;

/*
基类里的静态属性可以被所有的子类共享，用来控制缩进
 */
abstract class Entity {
    protected static StringBuilder indent = new StringBuilder();
    protected static int level = 1;

    public abstract void traverse(int[] levels);

    protected boolean printThisLevel(int[] levels) {
        for (int value : levels) {
            if (level == value) {
                return true;
            }
        }
        return false;
    }
}

