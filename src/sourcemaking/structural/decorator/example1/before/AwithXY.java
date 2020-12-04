package sourcemaking.structural.decorator.example1.before;

public class AwithXY extends AwithX {
    private aWithY y = new aWithY();

    @Override
    public void doIt() {
        super.doIt();
        y.doY();
    }

}
