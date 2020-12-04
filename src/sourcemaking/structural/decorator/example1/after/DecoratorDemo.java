package sourcemaking.structural.decorator.example1.after;

public class DecoratorDemo {
    public static void main(String[] args) {
        I[] array = {new X(new A()), new Y(new X(new A())),
                new Z(new Y(new X(new A())))};

        for (I i : array) {
            i.doIt();
            System.out.println();
        }
    }
}
