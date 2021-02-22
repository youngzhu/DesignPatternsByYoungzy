package sourcemaking.behavioral.state.example1.mine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
在after的基础上
在每个状态的构造函数里，可以将状态描述打出，而不是在pull方法里
 */
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
    Off() {
        System.out.println("  当前状态：关闭");
    }

    @Override
    public void pull(CeilingFanPullChain wrapper) {
        wrapper.setState(new Low());
    }
}

class Low implements State {
    Low() {
        System.out.println("  当前状态：1档");
    }

    @Override
    public void pull(CeilingFanPullChain wrapper) {
        wrapper.setState(new Medium());
    }
}

class Medium implements State {
    Medium() {
        System.out.println("  当前状态：2档");
    }

    @Override
    public void pull(CeilingFanPullChain wrapper) {
        wrapper.setState(new High());
    }
}

class High implements State {
    High() {
        System.out.println("  当前状态：3档");
    }

    @Override
    public void pull(CeilingFanPullChain wrapper) {
        wrapper.setState(new Off());
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
