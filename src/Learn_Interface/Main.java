package Learn_Interface;

interface Animal {
    void eat();
    void sleep();
}


interface Swimmer extends Animal {
    void swim();
}

class Dolphin implements Swimmer {
    public void eat() {
        System.out.println("Dolphin is eating.");
    }

    public void sleep() {
        System.out.println("Dolphin is sleeping.");
    }

    public void swim() {
        System.out.println("Dolphin is swimming.");
    }

    public static void staticFun(){
        System.out.println("这是接口的静态方法");
    }
}

public class Main {
    public static void main(String[] args) {
        Dolphin dolphin = new Dolphin();
        dolphin.eat();
        dolphin.sleep();
        dolphin.swim();
        dolphin.staticFun();
    }
}