/*
译文中所谓的“产品”
 */
package sourcemaking.creational.abstractfactory;

/**
 * 汽车零部件
 */
abstract class AutoBodyPart {
    abstract void sayHi();
}

abstract class Wheel extends AutoBodyPart {

}

abstract class Hood extends AutoBodyPart {

}

abstract class Door extends AutoBodyPart {

}
