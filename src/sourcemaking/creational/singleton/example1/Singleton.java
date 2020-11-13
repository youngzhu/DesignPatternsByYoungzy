package sourcemaking.creational.singleton.example1;

/*
马里兰大学计算机科学研究员比尔·庞格写过一篇文章是关于用Java实现单例模式
时的问题。庞格的“双重检查锁”引发了Java 5 在内存模型方面的改变，并且成了
Java实现单例模式的标准方式。这种“懒加载”的技术在初始化实例时用过，在所有Java版本
中都可以使用。它利用了类初始化的优势，所以在所有兼容Java的编译器和虚拟机中都可以
正确地使用。

内部类不会在调用 getInstance() 方法之前被引用，也不会被类加载器提前加载。
所以，这种方式是线程安全的，而并不需要特定的语法，如 volatile 或 synchronized
 */

public class Singleton {
    private Singleton(){}

    private static class SingletonHolder {
        private static final Singleton INSTANCE = new Singleton();
    }

    public static Singleton getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
