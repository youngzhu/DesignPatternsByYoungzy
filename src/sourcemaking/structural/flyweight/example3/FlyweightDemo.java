package sourcemaking.structural.flyweight.example3;

/*
重量级的ColorBoxes（每个ColorBox一个线程）
转化为
蝇量ColorBox和一个维护线程池的工厂类

探讨。为每一个ColorBox创建一个线程是比较直接的解决方案，但如果有大量
实例需要创建就是不划算的。在一组ColorBox之间共享一个线程池需要一些前期设置，
但是不会像前一个方案一样耗尽系统资源。

在下面的实现中，ColorBox将自己封装成了线程类。这个线程对象提供了所有线程该有
的功能，只要在状态从“准备”到“运行”的时候调用 run() 方法。当每一个线程对象（ColorBox）
被交换进CPU，ColorBox就会改变自己的颜色，然后放弃CPU（通过调用 sleep() 方法）以便其他线程可以执行。

在线程池的实现中，在ColorBox准备好后，线程池创建并启动了8个处理线程（HandlerThread）。当一个处理线程
被交换进CPU时，它从线程池的私有列表中获取了一个随机的ColorBox对象，告诉这个ColorBox对象改变它的颜色，
然后优雅地进入睡眠状态。

“通常，调用 sleep() 方法（合理的时间间隔）可以使你的线程类应用运行的更快”。
这更加深了这样一种认知 —— 线程是一种魔法。没有足够的调用（sleep()方法）则独占了CPU；
没有足够的时间间隔，则在它完成有用的工作之前由于时间限制打断了当前正在运行的线程。
 */

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class ColorBox extends Canvas implements Runnable {
    private int pause;
    private Color curColor = getColor();
    private static Color[] colors = {Color.black, Color.blue, Color.cyan, Color.darkGray, Color.gray, Color.green,
            Color.lightGray, Color.red, Color.magenta, Color.orange, Color.pink, Color.white, Color.yellow};

    public ColorBox(int pause) {
        this.pause = pause;
        new Thread(this).start();
    }

    private static Color getColor() {
        return colors[(int)(Math.random() * 1000) % colors.length];
    }

    @Override
    public void run() {
        while (true) {
            curColor = getColor();
            repaint();
            try {
                Thread.sleep(pause);
            } catch (InterruptedException ignored) {

            }
        }
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(curColor);
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}
public class FlyweightDemo {
    public static void main(String[] args) {
        int size = 8;
        int pause = 100;
        if (args.length > 0) {
            size = Integer.parseInt(args[0]);
        }
        if (args.length > 1) {
            pause = Integer.parseInt(args[1]);
        }

        Frame frame = new Frame("ColorBoxes - 1 thread per ColorBox");
        frame.setLayout(new GridLayout(size, size));
        for (int i = 0; i < size * size; i++) {
            frame.add(new ColorBox(pause));
        }

        frame.setSize(500, 400);
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}
