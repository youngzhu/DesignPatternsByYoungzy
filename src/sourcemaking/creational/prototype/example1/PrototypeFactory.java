package sourcemaking.creational.prototype.example1;

public class PrototypeFactory {
    public static void main(String[] args) {
        if (args.length > 0) {
            for (String type : args) {
                Person prototype = Factory.getPrototype(type);
                if (prototype != null) {
                    System.out.println(prototype + " is here");
                }
            }
        } else {
            System.out.println("Run again with args of command");
        }
    }
}
