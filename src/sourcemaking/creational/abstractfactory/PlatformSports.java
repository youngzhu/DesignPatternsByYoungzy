package sourcemaking.creational.abstractfactory;

class SportsDoor extends Door {
    void sayHi() {
        System.out.println("Hi, I'm a Sports Door");
    }
}

class SportsHood extends Hood {
    void sayHi() {
        System.out.println("Hi, I'm a Sports Hood");
    }
}

class SportsWheel extends Wheel {
    void sayHi() {
        System.out.println("Hi, I'm a Sports Wheel");
    }
}