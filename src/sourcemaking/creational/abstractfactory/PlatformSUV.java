package sourcemaking.creational.abstractfactory;

class SUVDoor extends Door {
    void sayHi() {
        System.out.println("Hi, I'm a SUV Door");
    }
}

class SUVHood extends Hood {
    void sayHi() {
        System.out.println("Hi, I'm a SUV Hood");
    }
}

class SUVWheel extends Wheel {
    void sayHi() {
        System.out.println("Hi, I'm a SUV Wheel");
    }
}