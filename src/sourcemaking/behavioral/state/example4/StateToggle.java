package sourcemaking.behavioral.state.example4;

/*
状态设计模式：一个含有2个状态、1个时间的有限状态机（FSM，Finite State Machine）
（事件是状态的变化逻辑，这些逻辑被分散在State的衍生类中）

1. 创建一个封装类代表状态机
2. 这个状态类持有一个状态引用——current
3. 所有客户端的请求都简便地委托给当前的状态对象，封装对象作为参数传入
4. 创建状态基类使得状态之间可以相互转换
5. 状态基类可以指定任何有意义的“默认”的操作
6. 每一个具体的状态类只需要覆盖它应该有的行为
7. 状态类的方法会改变封装类的当前的状态
 */

import java.io.InputStreamReader;

// 1. 封装类
class Button {
    // 2. 当前状态
    private State current;

    Button() {
        current = OFF.instance();
    }

    void setCurrent(State state) {
        current = state;
    }

    // 3. 将请求委托给状态对象
    void push() {
        current.push(this);
    }
}

// 4. 状态对象
class State {
    // 5. 默认行为
    void push(Button button) {
        button.setCurrent(OFF.instance());
        System.out.println("  关");
    }
}
class ON extends State {
    private static ON instance = new ON();

    private ON() {}

    static State instance() {
        return instance;
    }
}
class OFF extends State {
    private static OFF instance = new OFF();

    private OFF() {}

    static State instance() {
        return instance;
    }

    // 6. 在必要的时候覆盖默认行为
    void push(Button button) {
        // 7. 回调封装类，改变其当前状态
        button.setCurrent(ON.instance());
        System.out.println("  开");
    }
}

public class StateToggle {
    public static void main(String[] args) throws Exception {
        InputStreamReader in = new InputStreamReader(System.in);
        Button button = new Button();
        while (true) {
            System.out.print("按回车继续");
            in.read();
            button.push();
        }
    }
}
