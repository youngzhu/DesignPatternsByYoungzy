package sourcemaking.creational.factorymethod;

/**
 * Product
 * 基类
 */
abstract class InjectionMold {
    public static InjectionMold inject(String mold) {
        if ("Car".equals(mold)) {
            return new ToyCarMold();
        } else {
            return new ToyDuckMold();
        }

    }
}

/**
 * Product 1
 */
class ToyDuckMold extends  InjectionMold {
    protected ToyDuckMold(){System.out.println("玩具鸭注模...");}
}

/**
 * Product 2
 */
class ToyCarMold extends  InjectionMold {
    protected ToyCarMold(){System.out.println("玩具小汽车注模...");}
}
