package sourcemaking.behavioral.templatemethod;

public class Postman extends Worker {
    Postman() {
        this("邮递员");
    }
    public Postman(String title) {
        super(title);
    }

    @Override
    void work() {
        System.out.println("  啦啦啦 啦啦啦 好像串台了");
    }
}
