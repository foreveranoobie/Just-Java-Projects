package overriding;

public class TwoDefaultsInterfaces implements OperateCar, FlyCar {
    @Override
    public void startEngine(int key) {
        OperateCar.super.startEngine(key);
        FlyCar.super.startEngine(key);
    }

    public static void main(String... args){
        TwoDefaultsInterfaces tdi = new TwoDefaultsInterfaces();
        tdi.startEngine(3);
    }
}

interface OperateCar {
    // ...
    default void startEngine(int key) {
        System.out.println("Int key from operate car " + key);
    }
}

interface FlyCar {
    // ...
    default void startEngine(int key) {
        System.out.println("Int key from fly car " + key);
    }
}