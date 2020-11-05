package sourcemaking.creational.abstractfactory;

class SedanDoor extends Door {
    void sayHi() {
        System.out.println("Hi, I'm a Sedan Door");
    }
}

class SedanHood extends Hood {
    void sayHi() {
        System.out.println("Hi, I'm a Sedan Hood");
    }
}

class SedanWheel extends Wheel {
    @Override
    void sayHi() {
        System.out.println("Hi, I'm a Sedan Wheel");
    }
}