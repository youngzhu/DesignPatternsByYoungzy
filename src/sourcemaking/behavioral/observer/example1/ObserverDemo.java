package sourcemaking.behavioral.observer.example1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
1. 将“独立”的功能封装在抽象的主题类中
2. 将“有依赖”的功能封装在观察者类中
3. 主题类只与观察者的基类发生耦合
4. 观察者类将主题类注入自身（如通过构造函数）
5. 主题类向所有注册过的观察者广播它的事件
6. 观察者从主题类中获取他们需要的信息
7. 客户端决定观察者的类型和主题的状态
 */
abstract class Observer {
    protected Subject subject;
    public abstract void update();
}

class Subject {
    private List<Observer> observers = new ArrayList<>();
    private int state;

    public void add(Observer observer) {
        observers.add(observer);
    }

    public int getState() {
        return state;
    }

    public void setState(int value) {
        this.state = value;
        execute();
    }

    private void execute() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}

class HexObserver extends Observer {
    public HexObserver(Subject subject) {
        this.subject = subject;
        this.subject.add(this);
    }

    @Override
    public void update() {
        System.out.print(" " + Integer.toHexString(subject.getState()));
    }
}

class OctObserver extends Observer {
    public OctObserver(Subject subject) {
        this.subject = subject;
        this.subject.add(this);
    }

    @Override
    public void update() {
        System.out.print(" " + Integer.toOctalString(subject.getState()));
    }
}

class BinObserver extends Observer {
    public BinObserver(Subject subject) {
        this.subject = subject;
        this.subject.add(this);
    }

    @Override
    public void update() {
        System.out.print(" " + Integer.toBinaryString(subject.getState()));
    }
}

public class ObserverDemo {
    public static void main(String[] args) {
        Subject subject = new Subject();
        // 由客户端决定观察者的类型以及数字状态
        new HexObserver(subject);
        new OctObserver(subject);
        new BinObserver(subject);

        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < 5; i++) {
            System.out.print("\nEnter a number: ");
            subject.setState(scanner.nextInt());
        }
    }
}
