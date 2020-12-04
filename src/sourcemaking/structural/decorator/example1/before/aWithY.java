package sourcemaking.structural.decorator.example1.before;

public class aWithY extends A {
    @Override
    public void doIt() {
        super.doIt();
        doY();
    }

    public void doY() {
        System.out.print('Y');
    }
}
