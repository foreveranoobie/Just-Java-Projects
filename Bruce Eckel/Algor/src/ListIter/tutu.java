package ListIter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class tutu {

	public static void main(String[] args) throws FileNotFoundException {
		Queue<Double> prq = new PriorityQueue<Double>(); 
		Random rand = new Random();
		Double dble;
		for(int i = 0; i < 10; i++) {
			dble = rand.nextDouble() * 15;
			System.out.println(dble);
			prq.offer(dble);
		}
		System.out.println();
		for(int m = 0; m < 10; m++)
	    System.out.println(prq.poll());
		/*Map<String, ArrayList<Integer>> glass = new LinkedHashMap<String, ArrayList<Integer>>();
		File file = new File("f:\\listfiles.txt");
		Scanner input = new Scanner(file);
		int i = 0;
		while(input.hasNext()) {
			i++;
			String word = input.next();
			if(glass.containsKey(word)) {
				glass.get(word).add(i);
				continue;
			}
			glass.put(word, new ArrayList<Integer>(1));
			glass.get(word).add(i);
		}
		System.out.println(glass);
		Map <String, Integer> glass = new HashMap<String, Integer>();
		File file = new File("f:\\listfiles.txt");
		Scanner input = new Scanner(file);
		while(input.hasNext()) {
			String word = input.next();
			if(glass.containsKey(word)) {
				glass.replace(word, (glass.get(word)+1));
				continue;
			}
			glass.put(word, 1);
		}
		System.out.println(glass);
		Set <TheWords> theSet = new LinkedHashSet<TheWords>();
		SortedSet<String> keys = new TreeSet<>(glass.keySet());
		for (String key : keys) { 
		   theSet.add(new TheWords(key, glass.get(key)));
		   System.out.println(key);
		}
		for(TheWords twds : theSet) {
			System.out.println(twds);
		}

		glass.put("a", 0);
		glass.put("e", 0);
		glass.put("i", 0);
		glass.put("o", 0);
		glass.put("u", 0);
		glass.put("y", 0);
		String word = "i'm a human";
		String[] wrds = word.split("");
        for(String wr : wrds) 
        {
        	if(glass.containsKey(wr)) {
        		System.out.println("Found");
        		glass.replace(wr, (glass.get(wr)+1));
        	}
        }
		Set <String> glass = new HashSet<String>();
		Collections.addAll(glass, "a e i o u y".split(" "));
		String word = "yeah";
		Set <String> resGl = new HashSet<String>();
		Collections.addAll(resGl, word.split(""));
		Iterator<String> itr = resGl.iterator();
		int i = 0;
		while(itr.hasNext()) {
			if(glass.contains(itr.next())){
               i++;
			}
		}
		System.out.printf("Totally found %d letters\n", i);*/
	}

}
