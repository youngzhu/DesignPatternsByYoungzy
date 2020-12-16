package sourcemaking.structural.flyweight.example1.after;
/*
在这个重构中，把“行”作为可共享的状态（当然是在每一行中），
而“列”已经被外包（在客户端调用 report 方法时提供）了。
 */

class Gazillion {
    private int row;

    public Gazillion(int row) {
        this.row = row;
        System.out.println("ctor: " + row);
    }

    void report(int col) {
        System.out.print(" " + row + col);
    }
}

class Factory {
    private Gazillion[] pool;

    Factory(int maxRows) {
        pool = new Gazillion[maxRows];
    }

    public Gazillion getFlyweight(int row) {
        if (pool[row] == null)
            pool[row] = new Gazillion(row);
        return pool[row];
    }
}
public class FlyweightDemo {
    static final int ROWS = 6, COLS = 10;

    public static void main(String[] args) {
        Factory factory = new Factory(ROWS);
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                factory.getFlyweight(i).report(j);
            }
            System.out.println();
        }
    }
}
