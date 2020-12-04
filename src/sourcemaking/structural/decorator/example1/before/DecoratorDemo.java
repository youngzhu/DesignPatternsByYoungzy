package sourcemaking.structural.decorator.example1.before;

public class DecoratorDemo {
    public static void main(String[] args) {
        A[] array = {new AwithX(), new AwithXY(), new AwithXYZ()};

        for (A a : array) {
            a.doIt();
            System.out.println();
        }
    }
}
