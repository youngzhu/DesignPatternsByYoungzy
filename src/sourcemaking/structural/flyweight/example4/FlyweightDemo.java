package sourcemaking.structural.flyweight.example4;

/*
在线程池中共享的8个处理线程

现在ColorBox变成了一个蝇量：颜色改变和渲染的功能保留在内部，
而线程的行为变成了外部的。

ThreadPool对象扮演了工厂的角色。ColorBox对象在创建时注入了ThreadPool对象。
然后启动8个处理线程。当线程被交换进CPU，它就从线程池中随机选择一个蝇量对象，
然后调用 changeColor() 方法。
 */

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

class ColorBox extends Canvas {
    private Color curColor = getColor();

    private static Color[] colors = {Color.black, Color.blue, Color.cyan, Color.darkGray, Color.gray, Color.green,
            Color.lightGray, Color.red, Color.magenta, Color.orange, Color.pink, Color.white, Color.yellow};

    public ColorBox(ThreadPool threadPool) {
        threadPool.register(this);
    }

    private static Color getColor() {
        return colors[(int)(Math.random() * 1000) % colors.length];
    }

    public void changeColor() {
        curColor = getColor();
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(curColor);
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}

class ThreadPool {
    private Vector<ColorBox> boxes = new Vector();
    private int pause;

    class HandlerThread extends Thread {
        @Override
        public void run() {
            while (true) {
                int random = (int)(Math.random() * 1000) % boxes.size();
                boxes.elementAt(random).changeColor();

                try {
                    Thread.sleep(pause);
                } catch (InterruptedException ignored) {}

            }
        }
    }

    public ThreadPool(int pause) {
        this.pause = pause;
    }

    public void register(ColorBox colorBox) {
        boxes.addElement(colorBox);
    }

    public void start() {
        int NUM_THREADS = 8;
        for (int i = 0; i < NUM_THREADS; i++) {
            new HandlerThread().start();
        }
    }
}

public class FlyweightDemo {
    public static void main(String[] args) {
        int size = 8;
        int pause = 100;

        if (args.length > 0)
            size = Integer.parseInt(args[0]);
        if (args.length > 1)
            pause = Integer.parseInt(args[1]);

        ThreadPool pool = new ThreadPool(pause);
        Frame frame = new Frame("ColorBoxes - 8 shared HandlerThreads");
        frame.setLayout(new GridLayout(size, size));
        for (int i = 0; i < size * size; i++) {
            frame.add(new ColorBox(pool));
        }
        frame.setSize(500, 400);
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        pool.start();
    }
}
