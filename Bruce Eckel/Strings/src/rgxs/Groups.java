package rgxs;

import java.util.*;
import java.util.regex.*;

public class Groups {
  static public final String POEM =
    "Twas brillig, and the slithy toves\n" +
    "Did gyre and gimble in the wabe.\n" +
    "All mimsy were the borogoves,\n" +
    "And the mome raths outgrabe.\n\n" +
    "Beware the Jabberwock, my son,\n" +
    "The jaws that bite, the claws that catch.\n" +
    "Beware the Jubjub bird, and shun\n" +
    "The frumious Bandersnatch.";
  public static void main(String[] args) {
   /* Matcher m =
      Pattern.compile("(?m)(\\S+)\\s+((\\S+)\\s+(\\S+))$")
        .matcher(POEM);
    while(m.find()) {
      for(int j = 0; j <= m.groupCount(); j++)
        System.out.printf("[" + m.group(j) + "]");
      System.out.println("");
    }*/
	  //Matcher m = Pattern.compile("[A-Z][a-z]+(\\s*|.*)").matcher(POEM);
	  //Set<String> UnWords = new HashSet<String>();
	  for(String str : POEM.split("\n")) {
	  Matcher m = Pattern.compile(".+").matcher(str);
	  while(m.find()) {
		  //System.out.printf(m.group(0)+"\n");
		  //UnWords.add(m.group(0));
		  System.out.println("Find");
	  }
	  if(m.lookingAt()) {
		  System.out.println("LookingAt"+m.start()+" "+m.end());
	  }
	  if(m.matches()) {
		  System.out.println("Matches"+m.start()+" "+m.end());
	  }
	  }
	  System.out.println("\nThe result\n\n\n");
	  /*for(String str : UnWords) {
		  System.out.println(str);
	  }*/
  }
}
