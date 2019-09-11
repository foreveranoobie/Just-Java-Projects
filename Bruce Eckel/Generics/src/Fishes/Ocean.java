package Fishes;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

import Generator.Generators;

public class Ocean {
  public static void serve(BigFish b, SmallFish s) {
	  System.out.println(b +" eats "+s);
  }
  public static void main(String...args) {
	  Random rand=new Random();
	  Queue<SmallFish> line = new LinkedList<SmallFish>();
      Generators.fill(line, SmallFish.generator, 15);
      List<BigFish> bigger = new ArrayList<BigFish>();
      Generators.fill(bigger, BigFish.generator, 4);
      for(SmallFish s : line) {
    	  serve(bigger.get(rand.nextInt(bigger.size())), s);
      }
  }
}
