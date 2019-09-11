package exer40;
import java.lang.reflect.Method;
import java.util.*;

import exer28.pets.*;
class Apply {
	  public static <T, S extends Iterable<? extends T>>
	  void apply(S seq, Method f, Object... args) {
	    try {
	      for(T t: seq)
	        f.invoke(t, args);
	    } catch(Exception e) {
	      // Failures are programmer errors
	      throw new RuntimeException(e);
	    }
	  }
	}	
public class Main {
   public static void main(String...strings) throws NoSuchMethodException, SecurityException {
	   List<Pet> list = new ArrayList<Pet>();
	   list.add(new Pug());
	   list.add(new Dog());
	   list.add(new Cymric());
	   list.add(new EgyptianMau());
	   list.add(new Hamster());
	   Apply.apply(list, Pet.class.getMethod("speak"));
   }
}
