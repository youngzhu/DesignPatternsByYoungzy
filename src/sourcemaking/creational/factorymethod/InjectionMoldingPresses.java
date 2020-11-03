package sourcemaking.creational.factorymethod;

/**
 * Client
 * 注模机
 */
public class InjectionMoldingPresses {
    public static void main(String[] args) {
        String mold = "Car";
        mold = "";

        InjectionMold im = InjectionMold.inject(mold);
    }
}
