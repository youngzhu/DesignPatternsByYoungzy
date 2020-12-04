package sourcemaking.structural.decorator.example1.before;

public class AwithX extends A {
    @Override
    public void doIt() {
        super.doIt();
        doX();
    }

    private void doX() {
        System.out.print('X');
    }
}
