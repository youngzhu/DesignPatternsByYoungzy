package sourcemaking.structural.flyweight.example1.before;

/*
探讨。尽量在细粒度上去使用对象是很好的，但过了头就不好了。
蝇量模式建议将不可共享的状态从类中去除，并且在需要时由客户端来提供它。
这将更多的责任放在了客户端，但是，重要的是只需要创建更少的蝇量类的实例了。
这些实例的共享是通过工厂类来改进的，这个工厂类维护了一个蝇量实例的缓存。
 */

class Gazillion {
    private static int num = 0;
    private int row, col;

    public Gazillion(int maxPerRow) {
        row = num / maxPerRow;
        col = num % maxPerRow;
        num++;
    }

    void report() {
        System.out.print(" " + row + col);
    }
}

public class FlyweightDemo {
    static final int ROWS = 6, COLS = 10;

    public static void main(String[] args) {
        Gazillion[][] matrix = new Gazillion[ROWS][COLS];

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                matrix[i][j] = new Gazillion(COLS);
            }
        }

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                matrix[i][j].report();
            }
            System.out.println();
        }
    }
}
