package sourcemaking.behavioral.command.example1;

import java.util.ArrayList;
import java.util.List;

interface Command {
    void execute();
}

/**
 * 家政
 */
class DomesticEngineer implements Command {
    @Override
    public void execute() {
        System.out.println("take out the trash");
    }
}

/**
 * 政客
 */
class Politician implements Command {
    @Override
    public void execute() {
        System.out.println("take money from the rich, take votes from the poor");
    }
}

/**
 * 程序员
 */
class Programmer implements Command {
    @Override
    public void execute() {
        System.out.println("sell the bugs, charge extra for the fixes");
    }
}

public class CommandDemo {
    static List<Command> produceRequests() {
        List<Command> queue = new ArrayList<>();
        queue.add(new DomesticEngineer());
        queue.add(new Politician());
        queue.add(new Programmer());
        return queue;
    }

    static void workOffRequests(List<Command> queue) {
        for (Command command : queue) {
            command.execute();
        }
    }
    public static void main(String[] args) {
        List<Command> queue = produceRequests();
        workOffRequests(queue);

    }
}
