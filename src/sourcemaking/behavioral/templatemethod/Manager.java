package sourcemaking.behavioral.templatemethod;

public class Manager extends Worker {
    Manager() {
        this("经理人");
    }
    public Manager(String title) {
        super(title);
    }

    @Override
    void work() {
        System.out.println("  嗯。。啊。。散会");
    }

    @Override
    void relax() {
        System.out.println("  XX的快乐你想象不到啊");
    }

    @Override
    void sleep() {
        System.out.println("  喝个小酒再睡");
        super.sleep();
    }
}
