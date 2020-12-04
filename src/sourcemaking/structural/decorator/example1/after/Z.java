package sourcemaking.structural.decorator.example1.after;

public class Z extends D {
    public Z(I inner) {
        super(inner);
    }

    @Override
    public void doIt() {
        super.doIt();
        doZ();
    }

    private void doZ() {
        System.out.print('Z');
    }
}
