package sourcemaking.behavioral.memento.example;

import java.util.ArrayList;

class Memento {
    private String state;

    public Memento(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}

// 发明者/提出者
class Originator {
    private String state;
    // 很多消耗内存的私有数据没必要定义状态，也不应该被存储
    // 所以有了备忘录对象

    public void setState(String state) {
        System.out.println("Originator: Setting state to " + state);
        this.state = state;
    }

    public Memento save() {
        System.out.println("Originator: Saving to Memento.");
        return new Memento(state);
    }

    public void restore(Memento memento) {
        state = memento.getState();
        System.out.println("Originator: State after restoring from Memento: " + state);
    }
}

// 看门人/守护者
class Caretaker {
    private ArrayList<Memento> mementos = new ArrayList<>();

    public void addMemento(Memento memento) {
        mementos.add(memento);
    }

    public Memento getMemento() {
        return mementos.get(1);
    }
}

public class MementoDemo {
    public static void main(String[] args) {
        Caretaker caretaker = new Caretaker();
        Originator originator = new Originator();
        originator.setState("State1");
        originator.setState("State2");
        caretaker.addMemento(originator.save());
        originator.setState("State3");
        caretaker.addMemento(originator.save());
        originator.setState("State4");
        originator.restore(caretaker.getMemento());
    }
}
