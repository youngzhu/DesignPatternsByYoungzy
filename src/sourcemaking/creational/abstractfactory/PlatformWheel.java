package sourcemaking.creational.abstractfactory;

class SedanWheel extends Wheel {
    @Override
    void sayHi() {
        System.out.println("Hi, I'm a Sedan Wheel");
    }
}
class SportsWheel extends Wheel {
    void sayHi() {
        System.out.println("Hi, I'm a Sports Wheel");
    }
}
class SUVWheel extends Wheel {
    void sayHi() {
        System.out.println("Hi, I'm a SUV Wheel");
    }
}
