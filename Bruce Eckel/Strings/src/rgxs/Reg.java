package rgxs;

import java.util.Arrays;

public class Reg {

	public static void main(String[] args) {
		/*String txt = "Let's taalk about this or about talk if about.";
		String pnt = "kek.";
		System.out.println(txt.matches("\\w.+\\.$"));
		System.out.println(txt.replaceAll("(a|e|i|y|o|u)", "_"));
		System.out.println(Arrays.toString(txt.split("(about|Let's)")));*/
		String check = "Arline ate eight apples and one orange while Anita hadn't any";
		System.out.println(check.matches("(?i)((^[aeiou])|(\\s+[aeiou]))\\w+?[aeiou]\\b"));
	}

}
