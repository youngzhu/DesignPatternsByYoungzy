package sourcemaking.behavioral.templatemethod;

public class TemplaMethodDemo {
    public static void main(String[] args) {
        Worker fireFighter = new FireFighter();
        fireFighter.dailyRoutine();

        Worker lumberjack = new Lumberjack();
        lumberjack.dailyRoutine();

        Worker manager = new Manager();
        manager.dailyRoutine();

        Worker postman = new Postman();
        postman.dailyRoutine();
    }
}
