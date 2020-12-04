package sourcemaking.structural.decorator.example1.before;

public class aWithZ extends A {
    @Override
    public void doIt() {
        super.doIt();
        doZ();
    }

    public void doZ() {
        System.out.print('Z');
    }
}
