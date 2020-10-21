package sourcemaking.creational.abstractfactory.example1;

class EmberToolkit extends AbstractFactory {
    @Override
    CPU createCPU() {
        System.out.println("create Ember CPU");
        return new EmberCPU();
    }

    @Override
    MMU createMMU() {
        System.out.println("create Ember MMU");
        return new EmberMMU();
    }
}
