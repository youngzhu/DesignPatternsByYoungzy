package sourcemaking.creational.abstractfactory;

/*
核查清单 Step 4：为每一个平台创建一个工厂类
 */

/**
 * 平台1 - Model 1 - 轿车
 */
class SedanStampingEquipment extends StampingEquipment {

    @Override
    Wheel stampWheel() {
        return new SedanWheel();
    }

    @Override
    Hood stampHood() {
        return new SedanHood();
    }

    @Override
    Door stampDoor() {
        return new SedanDoor();
    }
}

/**
 * 平台2 - Model 2 - 跑车
 */
class SportsStampingEquipment extends StampingEquipment {

    @Override
    Wheel stampWheel() {
        return new SportsWheel();
    }

    @Override
    Hood stampHood() {
        return new SportsHood();
    }

    @Override
    Door stampDoor() {
        return new SportsDoor();
    }
}

/**
 * 平台3 - Model 3 - SUV
 */
class SUVStampingEquipment extends StampingEquipment {

    @Override
    Wheel stampWheel() {
        return new SUVWheel();
    }

    @Override
    Hood stampHood() {
        return new SUVHood();
    }

    @Override
    Door stampDoor() {
        return new SUVDoor();
    }
}