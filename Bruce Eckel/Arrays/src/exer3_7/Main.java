package exer3_7;

import java.util.Random;

public class Main {
	public static void main(String... strings) {
       int first = (int)(Math.random() * 5 + 1);
       int arr[][][] = new int[first][][];
       for(int f = 0; f < first; f++) {
           arr[f] = new int[(int)(Math.random() * 5 + 1)][];
    	   for(int s=0; s < arr[f].length; s++) {
    		   arr[f][s] = new int[(int)(Math.random() * 5 + 1)];
    		   for(int t = 0; t < arr[f][s].length; t++) {
    			   arr[f][s][t] = (int)(Math.random() * 100 + 1);
    		   }
    	   }
       }
       for(int f = 0; f < arr.length; f++) {
    	   System.out.print("[ ");
    	   for(int s = 0; s < arr[f].length; s++) {
    		   System.out.print("[");
    		   for(int t = 0; t < arr[f][s].length; t++) {
    			   System.out.print(arr[f][s][t]+" ");
    		   }
    		   System.out.print("]");
    	   }
    	   System.out.print(" ]\n");
       }
	}
}
