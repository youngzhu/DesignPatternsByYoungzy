package sourcemaking.creational.singleton.example2;

/**
 * 双重锁校验法
 */
public final class Singleton {
    private static volatile Singleton instance;

    private Singleton() {}

    public static Singleton getInstance() {
        if (instance == null) {
            // 一次校验
            // 如果为空，再同步
            synchronized (Singleton.class) {
                if (instance == null) {
                    // 二次校验
                    // 同步块内再次验空
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
