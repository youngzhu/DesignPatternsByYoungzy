package sourcemaking.behavioral.templatemethod;

public class FireFighter extends Worker {

    FireFighter() {
        super("消防员");
    }
    public FireFighter(String title) {
        super(title);
    }

    @Override
    void work() {
        System.out.println("  滴嘟滴嘟灭火去");
    }
}
