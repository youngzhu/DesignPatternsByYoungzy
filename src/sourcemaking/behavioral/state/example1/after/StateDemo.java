package sourcemaking.behavioral.state.example1.after;
/*
在这里，CeilingFanPullChain类是一个引用状态类State的封装类。
前一个例子（before）中每一种状态变化被放在了State的衍生类中。

对于这样简单的问题，状态模式有点大材小用了。
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

interface State {
    void pull(CeilingFanPullChain wrapper);
}

class CeilingFanPullChain {
    private State currentState;

    CeilingFanPullChain() {
        currentState = new Off();
    }

    public void setState(State state) {
        currentState = state;
    }

    public void pull() {
        currentState.pull(this);
    }
}

/* State 的衍生类 */
class Off implements State {
    @Override
    public void pull(CeilingFanPullChain wrapper) {
        wrapper.setState(new Low());
        System.out.println("  1档");
    }
}
class Low implements State {
    @Override
    public void pull(CeilingFanPullChain wrapper) {
        wrapper.setState(new Medium());
        System.out.println("  2档");
    }
}
class Medium implements State {
    @Override
    public void pull(CeilingFanPullChain wrapper) {
        wrapper.setState(new High());
        System.out.println("  3档");
    }
}
class High implements State {
    @Override
    public void pull(CeilingFanPullChain wrapper) {
        wrapper.setState(new Off());
        System.out.println("  关闭");
    }
}

public class StateDemo {
    public static void main(String[] args) {
        CeilingFanPullChain pullChain = new CeilingFanPullChain();
        while (true) {
            System.out.print("按回车继续");
            getLine();
            pullChain.pull();
        }
    }

    static String getLine() {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line = null;
        try {
            line = in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return line;
    }
}
