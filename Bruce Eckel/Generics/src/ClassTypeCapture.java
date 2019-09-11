import java.util.*;

class Building {}
class House extends Building {}

public class ClassTypeCapture<T> {
  Map<String, Class<?>> map = new HashMap<String, Class<?>>();
  public ClassTypeCapture() {
  }
  public void addType(String typename, Class<?> kind) {
	  map.put(typename, kind);
  }
  public Object createNew(String typename) throws InstantiationException, IllegalAccessException {
	  if(map.containsKey(typename)) {
	  return map.get(typename).newInstance();
	  }
	  else {
		  return null;
	  }
  }
  public static void main(String[] args) throws InstantiationException, IllegalAccessException {
    ClassTypeCapture<Building> ctt1 =
      new ClassTypeCapture<Building>();
    ctt1.addType("Building", Building.class);
    ctt1.addType("House", House.class);
    House hs = (House) ctt1.createNew("House");
    System.out.println(hs instanceof Building);
    Building bd = (Building) ctt1.createNew("Building");
    System.out.println(bd instanceof House);
  }
}