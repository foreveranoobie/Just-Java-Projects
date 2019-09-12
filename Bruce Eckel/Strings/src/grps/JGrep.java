package grps;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.*;

public class JGrep {
  public static void main(String[] args) throws Exception {
    //Pattern p = Pattern.compile("(//*.+/*/)|(//.+)|(//*.+[\r\n]+.*/*/)", Pattern.CASE_INSENSITIVE);
	//+Comments Pattern p = Pattern.compile("([/][*](.*\n)*[*][/])|([/][*].+[*][/])|(//.*)|([/][*].*\n+.*[*][/])|([/][*](.*[\n][\\s])*[*][/])", Pattern.UNIX_LINES);
	//+Comments Pattern p = Pattern.compile("[/][*][.*\n[^*/]]*[*][/]", Pattern.UNIX_LINES);
	//+ClassesFound Pattern p = Pattern.compile("class[.*[^{]]*", Pattern.UNIX_LINES | Pattern.DOTALL);
    // Iterate through the lines of the input file:
    int index = 0;
    String st = null;
    st = new String(Files.readAllBytes(Paths.get("f:\\proga.txt"))); 
    //String[] stArr = st.split(" ");
    String[] stArr = st.split("(//*.+[\\r\\n]+.+/*/)");
    //|(//*.+\\r?\\n/*/)
    Matcher m = p.matcher(st);
   /* for(String line : stArr) {
    	System.out.println(line);
      m.reset(line);
      while(m.find())
        System.out.println(index++ + ": " +
          m.group() + ": " + m.start());
    }*/
    while(m.find())
        System.out.println(index++ + ": " +
          m.group() + ": " + m.start());
  }
}