package sourcemaking.creational.abstractfactory;

/**
 * 抽象工厂 - 冲压设备
 * 核查清单 Step 3
 */
abstract class StampingEquipment {
    private static final SedanStampingEquipment SEDAN_STAMPING_EQUIPMENT = new SedanStampingEquipment();
    private static final SportsStampingEquipment SPORTS_STAMPING_EQUIPMENT = new SportsStampingEquipment();
    private static final SUVStampingEquipment SUV_STAMPING_EQUIPMENT = new SUVStampingEquipment();

    abstract Wheel stampWheel();
    abstract Hood stampHood();
    abstract Door stampDoor();

    static StampingEquipment getFactory(StampingDies stampingDies) {

        switch (stampingDies) {
            case SEDAN:
                return SEDAN_STAMPING_EQUIPMENT;
            case SPORTS:
                return SPORTS_STAMPING_EQUIPMENT;
            case SUV:
                return SUV_STAMPING_EQUIPMENT;
            default:
                throw new RuntimeException("Error Stamping Dies.");
        }
    }
}
