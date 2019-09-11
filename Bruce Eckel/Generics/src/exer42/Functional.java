package exer42;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

class Human{
	String name;
	Human(){}
	Human(String str){
		name = str;
	}
	String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String toString() {
		return "It's a human. His name is "+name;
	}
}

class Animal{
	String name;
	Animal(){}
	Animal(String str){
		name = str;
	}
	String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String toString() {
		return "It's an animal. His name is "+name;
	}
}

interface Combiner<T> { T combine(T x, T y); }
interface UnaryFunction<R,T> { R function(T x); }
interface Collector<T> extends UnaryFunction<T,T> {
  T result(); // Extract result of collecting parameter
}
interface UnaryPredicate<T> { boolean test(T x); }

public class Functional {
	public static <T> T
	  reduce(Iterable<T> seq, Combiner<T> combiner) {
	    Iterator<T> it = seq.iterator();
	    if(it.hasNext()) {
	      T result = it.next();
	      while(it.hasNext())
	        result = combiner.combine(result, it.next());
	      return result;
	    }
	    return null;
	  }
	
	  public static <T> Collector<T>
	  forEach(Iterable<T> seq, Collector<T> func) {
	    for(T t : seq)
	      func.function(t);
	    return func;
	  }
	  
	  static class HumanAdder implements Combiner<Human> {
		    public Human combine(Human x, Human y) {
		      return new Human(x.getName()+" and "+y.getName());
		    }
	  }
	  
	  static class AnimalAdder implements Combiner<Animal> {
		    public Animal combine(Animal x, Animal y) {
		      return new Animal(x.getName()+" and "+y.getName());
		    }
	  }
	  
	  static class HumanCollector implements Collector<Human>{
		Human hum = null;
		@Override
		public Human function(Human x) {
			hum = new Human(x.getName());
			return hum;
		}
		@Override
		public Human result() {
			// TODO Auto-generated method stub
			return new Human(hum.getName() + "after unary");
		} 
	  }
	  
	  static class AnimalCollector implements Collector<Animal>{
			Animal anim = null;
			@Override
			public Animal function(Animal x) {
				anim = new Animal(x.getName());
				return anim;
			}
			@Override
			public Animal result() {
				// TODO Auto-generated method stub
				return new Animal(anim.getName() + "after unary");
			} 
		  }
	  
	  static class HumanPredict implements UnaryPredicate<Human>{
		@Override
		public boolean test(Human x) {
			if(x != null) {
				return true;
			}
			return false;
		}
	   }
	  
	  static class AnimalPredict implements UnaryPredicate<Animal>{
			@Override
			public boolean test(Animal x) {
				if(x != null) {
					return true;
				}
				return false;
			}
		}
	  
	  public static void main(String...strings) {
		  List<Human> humansL = Arrays.asList(new Human("Jack"), new Human("John"), new Human("Joseph"));
		  Human hum = reduce(humansL, new HumanAdder());
		  System.out.print(hum.getName());
		  List<Animal> animL = Arrays.asList(new Animal("Jerry Lee"));
		  AnimalCollector animCol = new AnimalCollector();
		  forEach(animL, animCol);
		  System.out.println("\n"+animCol.anim.getName());
	  }
}
