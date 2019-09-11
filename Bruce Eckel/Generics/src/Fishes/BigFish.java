package Fishes;

import Generator.Generator;

public class BigFish {
  private static long counter = 1;
  private final long id = counter++;
  private BigFish() {}
  public String toString() { return "BigFish "+id;}
  public static Generator<BigFish> generator = 
		  new Generator<BigFish>() {
			@Override
			public BigFish next() {
				// TODO Auto-generated method stub
				return new BigFish();
			}
  };
}
