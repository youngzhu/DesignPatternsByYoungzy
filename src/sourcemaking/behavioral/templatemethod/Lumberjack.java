package sourcemaking.behavioral.templatemethod;

public class Lumberjack extends Worker {
    Lumberjack() {
        this("伐木工");
    }
    public Lumberjack(String title) {
        super(title);
    }

    @Override
    void work() {
        System.out.println("  吴刚似的去砍树");
    }
}
