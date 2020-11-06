package sourcemaking.creational.prototype.example1;

import java.util.HashMap;
import java.util.Map;

/**
 * 抽象工厂可以存储一组原型对象，以备用
 */
public class Factory {
    private static final Map<String, Person> prototypes = new HashMap<>();

    static {
        prototypes.put("tom", new Tom());
        prototypes.put("dick", new Dick());
        prototypes.put("harry", new Harry());
    }

    public static Person getPrototype(String type) {

        try {
            return prototypes.get(type).clone();
        } catch (NullPointerException e) {
            System.out.println("Prototype with name:" + type + ", doesn't exist");
            return null;
        }
    }
}
