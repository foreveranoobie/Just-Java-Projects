//: typeinfo/pets/LiteralPetCreator.java
// Using class literals.
package exer28.pets;
import java.util.*;

public class LiteralPetCreator extends PetCreator {
  // No try block needed.
  @SuppressWarnings("unchecked")
  public static final List<Class<? extends Pet>> allTypes =
    Collections.unmodifiableList(Arrays.asList(
      Pet.class, Dog.class, Cat.class,  Rodent.class,
      Mutt.class, Pug.class, EgyptianMau.class, Manx.class,
      Cymric.class, Rat.class, Mouse.class,Hamster.class));
  // Types for random creation:
  private static final List<Class<? extends Pet>> types =
    allTypes.subList(allTypes.indexOf(Mutt.class),
      allTypes.size());
  public List<Class<? extends Pet>> types() {
    return types;
  }	
  public static void main(String[] args) {
    System.out.println(types);
  }
 
public static <T> void contraVar(Generic1<? super T> g1, T kind) {
	g1.setArg(kind);
}
public static <T> T coVar(Generic2<? extends T> g2) {
	return g2.getArg();
}

class Generic1<T>{
	T kind;
	public void setArg(T arg) {
		kind = arg;
	}
}
class Generic2<T>{
	T kind;
	public T getArg() {
		return kind;
	}
}
}
