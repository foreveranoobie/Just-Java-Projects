package Second;

class Bicycle extends Cycle{
    void kek() {
    	System.out.println("I'm keik");
    }
    static void stMeth(Cycle cyc) {
    	cyc.kek();
    }
}
