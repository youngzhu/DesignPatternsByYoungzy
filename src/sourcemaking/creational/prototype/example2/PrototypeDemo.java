package sourcemaking.creational.prototype.example2;
import java.util.ArrayList;
import java.util.List;

/*
1 Create a "contract" with clone() and getName() entries
2 Design a "registry" that maintains a cache of prototypical objects
3 Populate the registry with an initializePrototypes() function
4 The registry has a findAndClone() "virtual constructor" that can transform
    a String into its correct object(it calls clone() which then calls "new")
5 All classes relate themselves to the clone() contract
6 Client uses the findAndClone() virtual ctor instead of the "new" operator
 */

// 没太看懂意义在哪。。

// 1 the contract
interface Prototype {
    Prototype clone();
    String getName();
    void execute();
}

class PrototypeModule {
    // 2 registry of prototypical objs
    private static List<Prototype> prototypeList = new ArrayList<>();

    public static void addPrototype(Prototype prototype) {
        prototypeList.add(prototype);
    }

    public static Prototype createPrototype(String name) {
        // 4 virtual ctor
        for (Prototype p : prototypeList) {
            if (p.getName().equals(name)) {
                return p.clone();
            }
        }

        System.out.println(name + ": doesn't exist");
        return null;
    }
}

// 5 Sign-up for the contract
class PrototypeAlpha implements Prototype {
    private final static String NAME = "AlphaVersion";

    @Override
    public Prototype clone() {
        return new PrototypeAlpha();
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void execute() {
        System.out.println(NAME + ": does something");
    }
}

class PrototypeBeta implements Prototype {
    private final static String NAME = "BetaVersion";

    @Override
    public Prototype clone() {
        return new PrototypeBeta();
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void execute() {
        System.out.println(NAME + ": does something");
    }
}

class ReleasePrototype implements Prototype {
    private final static String NAME = "ReleaseCandidate";

    @Override
    public Prototype clone() {
        return new ReleasePrototype();
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void execute() {
        System.out.println(NAME + ": does real work");
    }
}

public class PrototypeDemo {
    public static void main(String[] args) {
        if (args.length > 0) {
            initializePrototypes();
            List<Prototype> prototypes = new ArrayList<>();
            // 6 client does not use "new"
            for (String name : args) {
                Prototype prototype = PrototypeModule.createPrototype(name);
                if (prototype != null) {
                    prototypes.add(prototype);
                }
            }

            for (Prototype p : prototypes) {
                p.execute();
            }
        } else {
            System.out.println("Run again with args of command");
        }
    }

    // 3 populate the registry
    private static void initializePrototypes() {
        PrototypeModule.addPrototype(new PrototypeAlpha());
        PrototypeModule.addPrototype(new PrototypeBeta());
        PrototypeModule.addPrototype(new ReleasePrototype());
    }
}
