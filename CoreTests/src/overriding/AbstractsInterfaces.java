package overriding;

public class AbstractsInterfaces implements Human, Bird {

}

interface Bird {
    default void doSomething(String text, int id){}
}

interface Human {
    default void doSomething(String text, int id) {
        System.out.println("I'm doing something");
    }
}
