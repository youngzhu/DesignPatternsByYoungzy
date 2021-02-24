package sourcemaking.behavioral.strategy.example;

/*
1. 定义可互换的一簇算法的接口
2. 将算法的实现细节放到各衍生类中
3. 各衍生类可能通过模板方法模式实现
4. 客户端只与接口耦合
 */

// 1. 定义接口
interface Strategy {
    void solve();
}

// 2. 衍生类
abstract class StrategySolution implements Strategy {
    // 3. 模板方法
    @Override
    public void solve() {
        start();
        while (nextTry() && !isSolution()) {}
        stop();
    }

    abstract void start();
    abstract boolean nextTry();
    abstract boolean isSolution();
    abstract void stop();
}

class Foo extends StrategySolution {
    private int state = 1;

    @Override
    void start() {
        System.out.print("Start  ");
    }

    @Override
    void stop() {
        System.out.println("Stop");
    }

    @Override
    boolean nextTry() {
        state++;
        System.out.print("Next-Try-" + state + "  ");
        return true;
    }

    @Override
    boolean isSolution() {
        boolean done = state == 3;
        System.out.print("IsSolution-" + done + "  ");
        return done;
    }
}

// 2. 实现
abstract class StrategySearch implements Strategy {
    // 3. 模板方法
    @Override
    public void solve() {
        while (true) {
            preProcess();
            if (search()) {
                break;
            }
            postProcess();
        }
    }

    abstract void preProcess();
    abstract boolean search();
    abstract void postProcess();
}

class Bar extends StrategySearch {
    private int state = 1;

    @Override
    void preProcess() {
        System.out.print("PreProcess  ");
    }

    @Override
    void postProcess() {
        System.out.print("PostProcess  ");
    }

    @Override
    boolean search() {
        state++;
        System.out.print("Search-" + state + "  ");
        return state == 3;
    }
}

// 4. 客户端仅跟接口耦合
public class StrategyDemo {
    // 耦合
    private static void execute(Strategy strategy) {
        strategy.solve();
    }

    public static void main(String[] args) {
        Strategy[] strategies = {new Foo(), new Bar()};
        for (Strategy strategy : strategies)
            execute(strategy);
    }
}
