package ListIter;

class TheWords {
     private int count;
     private String word;
     protected TheWords(String str, int num){
    	 count = num;
    	 word = str;
     }
     public String toString() {
    	 return ("The word " +word+" with count "+count);
     }
}
