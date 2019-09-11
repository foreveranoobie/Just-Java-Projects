package Fishes;

import Generator.Generator;

public class SmallFish {
	  private static long counter = 1;
	  private final long id = counter++;
	  private SmallFish() {}
	  public String toString() { return "SmallFish "+id;}
	  public static Generator<SmallFish> generator = 
			  new Generator<SmallFish>() {
				@Override
				public SmallFish next() {
					return new SmallFish();
				}
	  };
	}
