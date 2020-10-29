/*
译文中所谓的“平台”
 */
package sourcemaking.creational.abstractfactory;

class SedanDoor extends Door {
    void sayHi() {
        System.out.println("Hi, I'm a Sedan Door");
    }
}

class SportsDoor extends Door {
    void sayHi() {
        System.out.println("Hi, I'm a Sports Door");
    }
}

class SUVDoor extends Door {
    void sayHi() {
        System.out.println("Hi, I'm a SUV Door");
    }
}