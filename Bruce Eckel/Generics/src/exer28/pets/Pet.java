//: typeinfo/pets/Pet.java
package exer28.pets;

public class Pet extends Individual {
  public Pet(String name) { super(name); }
  public Pet() { super(); }
  public void speak() {System.out.println("Pet is saying something");}
} ///:~
