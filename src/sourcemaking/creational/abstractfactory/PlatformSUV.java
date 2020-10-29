package sourcemaking.creational.abstractfactory;

class SedanHood extends Hood {
    void sayHi() {
        System.out.println("Hi, I'm a Sedan Hood");
    }
}
class SportsHood extends Hood {
    void sayHi() {
        System.out.println("Hi, I'm a Sports Hood");
    }
}
class SUVHood extends Hood {
    void sayHi() {
        System.out.println("Hi, I'm a SUV Hood");
    }
}
