package sourcemaking.creational.abstractfactory;

public class Client {
    public static void main(String[] args) {
        StampingEquipment factory = changeByRoller(StampingDies.SEDAN);

//        factory = changeByRoller(StampingDies.SUV);
//        factory = changeByRoller(null);

        Wheel wheel = factory.stampWheel();
        wheel.sayHi();
        Hood hood = factory.stampHood();
        hood.sayHi();
        Door door = factory.stampDoor();
        door.sayHi();
    }

    /**
     * 通过辊轴切换模具
     *
     * @return
     */
    private static StampingEquipment changeByRoller(StampingDies stampingDies) {
        return StampingEquipment.getFactory(stampingDies);
    }
}
