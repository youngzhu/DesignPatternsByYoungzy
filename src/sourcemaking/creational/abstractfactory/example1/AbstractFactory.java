package sourcemaking.creational.abstractfactory.example1;

abstract class AbstractFactory {
    private static final EmberToolkit EMBER_TOOLKIT = new EmberToolkit();
    private static final IntelToolkit INTEL_TOOLKIT = new IntelToolkit();

    static AbstractFactory getFactory(Architecture architecture) {
        AbstractFactory factory = null;

        switch (architecture) {
            case EMBER:
                factory = EMBER_TOOLKIT;
                break;
            case INTEL:
                factory = INTEL_TOOLKIT;
                break;
        }

        return factory;
    }

    abstract CPU createCPU();
    abstract MMU createMMU();
}
