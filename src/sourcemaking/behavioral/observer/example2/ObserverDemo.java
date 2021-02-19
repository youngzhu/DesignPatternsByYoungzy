package sourcemaking.behavioral.observer.example2;

import java.util.Enumeration;
import java.util.Vector;

/*
类继承 VS 类型继承
SensorSystem 是主题类。Lighting，Gates和Surveillance 是观察者类。
主题类 只跟接口类 AlarmListener 耦合

一个对象的类定义了一个对象是如何实现的。
相比而已，一个对象的类型只关系到它的接口。
类继承表示一个对象依照另一个对象来实现。
类型继承表示一个对象可以代替另一个对象。
 */
interface AlarmListener {
    void alarm();
}

/*传感器*/
class SensorSystem {
    private Vector listeners = new Vector();

    public void register(AlarmListener listener) {
        listeners.addElement(listener);
    }

    public void soundTheAlarm() {
        for (Enumeration e = listeners.elements(); e.hasMoreElements();) {
            ((AlarmListener)e.nextElement()).alarm();
        }
    }

}

class Lighting implements AlarmListener {
    @Override
    public void alarm() {
        System.out.println("lights up");
    }
}

class Gates implements AlarmListener {
    @Override
    public void alarm() {
        System.out.println("gates close");
    }
}

class CheckList {
    // 模板方法模式
    public void byTheNumbers() {
        localize();
        isolate();
        identify();
    }

    protected void localize() {
        System.out.println("  establish a perimeter");
    }

    protected void isolate() {
        System.out.println("  isolate the grid");
    }

    protected void identify() {
        System.out.println("  identify the source");
    }
}

// 类继承
// 类型继承
/*监视器*/
class Surveillance extends CheckList implements AlarmListener {
    @Override
    public void alarm() {
        System.out.println("Surveillance - by the numbers:");
        byTheNumbers();
    }

    @Override
    protected void isolate() {
        System.out.println("  train the cameras");
    }
}

public class ObserverDemo {
    public static void main(String[] args) {
        SensorSystem sensorSystem = new SensorSystem();
        sensorSystem.register(new Gates());
        sensorSystem.register(new Lighting());
        sensorSystem.register(new Surveillance());
        sensorSystem.soundTheAlarm();
    }
}
