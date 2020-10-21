package sourcemaking.creational.abstractfactory.example1;

public class Client {
    public static void main(String[] args) {
        AbstractFactory factory = AbstractFactory.getFactory(Architecture.EMBER);
        CPU cpu = factory.createCPU();
        MMU mmu = factory.createMMU();
    }
}
