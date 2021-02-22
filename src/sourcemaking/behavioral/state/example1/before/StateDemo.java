package sourcemaking.behavioral.state.example1.before;
/*
3种风速的吊扇
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 不够好：状态不灵活
class CeilingFanPullChain {
    private int currentState;

    CeilingFanPullChain() {
        currentState = 0;
    }

    public void pull() {
        if (currentState == 0) {
            currentState = 1;
            System.out.println("  1档");
        } else if (currentState == 1) {
            currentState = 2;
            System.out.println("  2档");
        } else if (currentState == 2) {
            currentState = 3;
            System.out.println("  3档");
        } else {
            currentState = 0;
            System.out.println("  关闭");
        }
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
