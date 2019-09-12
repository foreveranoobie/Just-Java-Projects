import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

class Shape {
  void draw() { System.out.println(this + ".draw()"); }
  public String toString() {
	  return "Shape";
  }
}

class Circle extends Shape {
  public String toString() { return "Circle"; }
}

class Square extends Shape {
  public String toString() { return "Square"; }
}

class Triangle extends Shape {
  public String toString() { return "Triangle"; }
}	

class FiveAngle extends Triangle{
	public String toString() { return "FiveAngle";}
}

public class Shapes {
  static void getSh(FiveAngle fang) throws ClassNotFoundException {
    Class cl = fang.getClass();
    while(cl.getSuperclass() != null) {
        System.out.println(cl.getSuperclass());
        cl = cl.getSuperclass();
    }
  }
  
 /* public static Field[] getAllFields(Class klass) {
      List<Class> cls;
      cls.addAll(Arrays.asList(klass.getSuperclass()));
      if (klass.getSuperclass() != null) {
          cls.addAll(Arrays.asList(getAllFields(klass.getSuperclass())));
      }
      return fields.toArray(new Field[] {});
  }*/
  public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
    FiveAngle tri = new FiveAngle();
    getSh(tri);
    /*Class cl = Class.forName("Shape");
    Object obj = cl.newInstance();
    ((Shape) obj).draw();*/
  }
} 