package overriding;

public class DefaultsInterface implements EggLayer, FireBreather {

    public static void main(String... args) {
        DefaultsInterface myApp = new DefaultsInterface();
        System.out.println(myApp.identifyMyself());
    }
}

interface Animal {
    default public String identifyMyself() {
        return "I am an animal.";
    }
}

interface EggLayer extends Animal {
    default public String identifyMyself() {
        return "I am able to lay eggs.";
    }
}

interface FireBreather extends Animal {
}