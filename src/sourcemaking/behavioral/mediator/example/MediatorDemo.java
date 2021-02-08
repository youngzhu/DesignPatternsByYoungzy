package sourcemaking.behavioral.mediator.example;

/*
1. 创建一个“中介”使得“发送者”和“接收者”之间解耦
2. 生产者只与中介耦合
3. 消费者只与中介耦合
4. 中介决定消息的存储和检索
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// 1. 中介
class Mediator {
    // 4. 中介仲裁
    private boolean slotFull = false;
    private int number;

    public synchronized void storeMessage(int number) {
        // 没有更多的空间存储消息了
        while (slotFull) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        slotFull = true;
        this.number = number;
        notifyAll();
    }

    public synchronized int retrieveMessage() {
        // 没有消息
        while (! slotFull) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        slotFull = false;
        notifyAll();
        return number;
    }
}


class Producer implements Runnable {
    // 2 消费者只与中介耦合
    private Mediator mediator;
    private int id;
    private static int num = 1;

    public Producer(Mediator mediator) {
        this.mediator = mediator;
        id = num++;
    }

    @Override
    public void run() {
        int num;
        while (true) {
            mediator.storeMessage(num = (int)(Math.random() * 100));
            System.out.print("p" + id + "-" + num + " ");
        }
    }
}

class Consumer implements Runnable {
    // 3 消费者只与中介耦合
    private Mediator mediator;
    private int id;
    private static int num = 1;

    public Consumer(Mediator mediator) {
        this.mediator = mediator;
        id = num++;
    }

    @Override
    public void run() {
        while (true) {
            System.out.print("c" + id + "-" + mediator.retrieveMessage() + " ");
        }
    }
}
public class MediatorDemo {
    public static void main(String[] args) {
        List<Thread> threads = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Press ENTER for exit");
        Mediator mediator = new Mediator();
        threads.add(new Thread(new Producer(mediator)));
        threads.add(new Thread(new Producer(mediator)));
        threads.add(new Thread(new Consumer(mediator)));
        threads.add(new Thread(new Consumer(mediator)));
        threads.add(new Thread(new Consumer(mediator)));
        threads.add(new Thread(new Consumer(mediator)));
        for (Thread thread : threads) {
            thread.start();
        }

        boolean stop = false;
        String exit = scanner.nextLine();
        while (!stop) {
            if ("".equals(exit)) {
                stop = true;
                for (Thread t : threads) {
                    // 忽略弃用
                    t.stop();
                }
            }
        }
    }
}
