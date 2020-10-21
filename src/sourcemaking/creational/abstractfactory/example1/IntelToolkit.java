package sourcemaking.creational.abstractfactory.example1;

class IntelToolkit extends AbstractFactory {
    @Override
    CPU createCPU() {
        System.out.println("create Intel CPU");
        return new IntelCPU();
    }

    @Override
    MMU createMMU() {
        System.out.println("create Intel MMU");
        return new IntelMMU();
    }
}
