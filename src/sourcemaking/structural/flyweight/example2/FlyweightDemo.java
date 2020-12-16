package sourcemaking.structural.flyweight.example2;
/*
1. 确定可共享的状态（内在的）和不可共享的状态（外在的）
2. 创建一个工厂，该工厂可以返回一个已存在的对象或一个新对象
3. 客户端必须用工厂类代替“new”操作去获取对象
4. 客户端（或者第三方）必须提供或计算外在的状态（即不可共享的状态）
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

class FlyweightFactory {
    private static Map<String, Button> map = new TreeMap<>();
    private static int sharedButtons = 0;
    private static ButtonListener listener = new ButtonListener();

    public static Button makeButton(String num) {
        Button button;
        if (map.containsKey(num)) {
            // 1 确定内在的状态：按钮标签
            // 2 返回一个已存在的对象。
            // 相同的Button不能被多次添加到Map中，而且Button不能被克隆
            // 所以，这仅仅是蝇量模式共享的一个模拟
            button = new Button(map.get(num).getLabel());
            sharedButtons++;
        } else {
            // 2 返回一个新对象
            button = new Button(num);
            map.put(num, button);
        }
        button.addActionListener(listener);
        return button;
    }

    public static void report() {
        System.out.print("new Buttons - " + map.size() +
                ", \"shared\" Buttons - " + sharedButtons + ", ");
        for (Object o : map.keySet())
            System.out.print(o + " ");
        System.out.println();
    }
}

class ButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        Button button = (Button)e.getSource();
        Component[] buttons = button.getParent().getComponents();
        int i = 0;
        for (; i < buttons.length; i ++) {
            if (button == buttons[i])
                break;
        }
        // 4 第三方计算外在的状态：x 和 y
        System.out.println("label-" + e.getActionCommand()
                // 按钮的行和列
                + " x-" + i / 10 + " y-" + i % 10);
    }
}

public class FlyweightDemo {
    public static void main(String[] args) {
        Random random = new Random();
        Frame frame = new Frame("Flyweight Demo");
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        frame.setLayout(new GridLayout(10, 10));
        // 1 确定可共享的和不可共享的状态
        // 可共享的：按钮标签
        // 不可共享的：按钮位置
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++)
                // 3 必须使用工厂类获取对象
                frame.add(FlyweightFactory.makeButton(Integer.toString(random.nextInt(15))));
        }
        frame.pack();
        frame.setVisible(true);
        FlyweightFactory.report();
    }
}
