package sourcemaking.behavioral.state.example5;

/*
1. 创建一个状态机的封装类
2. 该封装类含有所有状态对象的一个数组
3. 封装对象有一个状态对象的索引值表示当前状态
4. 封装对象有一个状态变化表
5. 所有客户端的请求都委托给状态对象
6. 创建一个状态基类使得状态之间可以互相切换
7. 状态基类指定默认行为
8. 具体的状态类覆盖相应的方法
 */

// 1. 封装类，状态机
class FSM {
    // 2. 状态数组
    private State[] states = {new A(), new B(), new C()};
    // 4. 状态变化表
    private int[][] transition = {{2, 1, 0}, {0, 2, 1}, {1, 2, 2}};
    // 3. 当前状态索引
    private int curStateIdx = 0;

    private void next(int msg) {
        curStateIdx = transition[curStateIdx][msg];
    }

    // 5. 委托
    void on() {
        states[curStateIdx].on();
        next(0);
    }

    void off() {
        states[curStateIdx].off();
        next(1);
    }

    void ack() {
        states[curStateIdx].ack();
        next(2);
    }
}

// 6. 状态基类
abstract class State {
    // 7. 默认的行为
    void on() {
        System.out.println("  错误");
    }
    void off() {
        System.out.println("  错误");
    }
    void ack() {
        System.out.println("  错误");
    }
}
class A extends State {
    // 8. 具体的状态类覆盖具体的方法
    @Override
    void on() {
        System.out.println("A + on = C");
    }

    @Override
    void off() {
        System.out.println("A + off = B");
    }

    @Override
    void ack() {
        System.out.println("A + ack = A");
    }
}
class B extends State {
    @Override
    void on() {
        System.out.println("B + on = C");
    }

    @Override
    void off() {
        System.out.println("B + off = C");
    }
}
class C extends State {
    @Override
    void on() {
        System.out.println("C + on = B");
    }
}

public class StateDemo {
    public static void main(String[] args) {
        FSM fsm = new FSM();
        int[] msgs = {2, 1, 2, 1, 0, 2, 0, 0};
        for (int msg : msgs) {
            if (msg == 0) {
                fsm.on();
            } else if (msg == 1) {
                fsm.off();
            } else if (msg == 2) {
                fsm.ack();
            }
        }
    }
}
