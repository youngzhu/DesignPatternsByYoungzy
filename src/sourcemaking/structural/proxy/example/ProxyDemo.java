package sourcemaking.structural.proxy.example;

/*
1. 为远程的或昂贵的或敏感的对象创建一个包装类
2. 在包装类里封装目标对象的复杂功能
3. 客户端和包装类交互
4. 包装类委托给目标类
5. 为了支持兼容，创建一个接口
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

// 5. 为了在包装类和目标类之间实现兼容，创建一个接口
interface SocketInterface {
    String readLine();
    void writeLine(String str);
    void dispose();
}

class SocketProxy implements SocketInterface {
    // 1. 创建包装类
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public SocketProxy(String host, int port, boolean wait) {
        try {
            if (wait) {
                // 2 封装其复杂的部分
                ServerSocket server = new ServerSocket(port);
                socket = server.accept();
            } else {
                socket = new Socket(host, port);
            }

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String readLine() {
        String str = null;
        try {
            str = in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }

    @Override
    public void writeLine(String str) {
        // 4 包装类 委托给目标类
        out.println(str);
    }

    @Override
    public void dispose() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

public class ProxyDemo {
    public static void main(String[] args) {
        // 3 客户端调用包装类
        SocketInterface socket = new SocketProxy("127.0.0.1", 80,
                //"first".equals(args[0]) ? true : false
                false);
        String str;
        boolean skip = true;
        while (true) {
            if (/*args[0].equals("second") && */skip) {
                skip = !skip;
            } else {
                str = socket.readLine();
                System.out.println("Receive - " + str);
                if (str == null)
                    break;
            }

            System.out.print("Send ---");
            str = new Scanner(System.in).nextLine();
            socket.writeLine(str);
            if ("quit".equals(str))
                break;
        }

        socket.dispose();
    }
}
