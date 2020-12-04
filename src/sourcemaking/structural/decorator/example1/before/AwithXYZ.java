package sourcemaking.structural.decorator.example1.before;

public class AwithXYZ extends AwithX {
    private aWithY y = new aWithY();
    private aWithZ z = new aWithZ();

    @Override
    public void doIt() {
        super.doIt();
        y.doY();
        z.doZ();
    }

}
