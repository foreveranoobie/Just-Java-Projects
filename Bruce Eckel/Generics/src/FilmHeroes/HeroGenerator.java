	package FilmHeroes;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

public class HeroGenerator implements Iterable<Hero>{
    private Class[] types = new Class[] {HarryPotter.class, VolanDeMort.class, Roland.class,
    		Devil.class, RobinGood.class};
    public static Random rand = new Random();
    public HeroGenerator() {}
    private int size = 0;
    public HeroGenerator(int n) {
    	size = n;
    }
    public Hero next(){
			try {
				return (Hero) types[rand.nextInt(types.length)].newInstance();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw new RuntimeException(e);
		    }
    }
    public class HeroIterator implements Iterator<Hero>{
    	int count = size;
		@Override
		public boolean hasNext() {
           return count > 0;
		}
		@Override
		public Hero next() {
            count--;
			return HeroGenerator.this.next();
		}
		public void remove() {
			size--;
			count--;
		}
		};
	@Override
	public Iterator<Hero> iterator() {
		return new HeroIterator();
	}
	public static<V> LinkedList<V> lList() {
		return new LinkedList<V>();
	}
	public static void f(LinkedList<?> lst) {}
	public static void main(String[] args) {
		LinkedList<Hero> hr = lList();
		f(HeroGenerator.<Hero>lList());
	}
}
