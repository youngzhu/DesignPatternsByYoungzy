package sourcemaking.structural.decorator.example1.after;

public abstract class D implements I {
    private I core;
    public D(I inner) {
        core = inner;
    }

    @Override
    public void doIt() {
        core.doIt();
    }
}
