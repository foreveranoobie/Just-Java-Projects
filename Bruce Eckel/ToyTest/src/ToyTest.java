import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

interface HasBatteries {}
interface Waterproof {}
interface Shoots {}

class FancyToy extends Toy
implements HasBatteries, Waterproof, Shoots {
  FancyToy() { super(1); }
}

public class ToyTest {
  static void printInfo(Class<?> cc) {
    System.out.println("Class name: " + cc.getName() +
      " is interface? [" + cc.isInterface() + "]");
    System.out.println("Simple name: " + cc.getSimpleName());
    System.out.println("Canonical name : " + cc.getCanonicalName());
  }
  public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
    Class<?> c = Class.forName("Toy");
    Constructor<?> cons = c.getConstructor(int.class);
    Object obj = cons.newInstance(115);
  }
}