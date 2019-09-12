package dividing;

import java.util.Random;

public class divSort {
    
	public static int mergesort(int[] array) {
		int[] left;
		int[] right;
		int result;
		if(array.length == 1) {
			return array[0];
		}
		else {
			for(int m = 0; m < array.length/2; m++) {
				left[m] = array[m];
			}
			for(int m = array.length/2; m < array.length; m++) {
				right[m - array.length/2] = array[m];
			}
			left = 
			return result;	
		}
	}
	
	public static void main(String[] args) {
	 	// TODO Auto-generated method stub
       int array[] = new int[10]; 
	   Random rand = new Random();
       for(int i = 0; i < 10; i++) {
    	   array[i] = rand.nextInt(9) + 1;
    	   System.out.print(array[i]+" ");
       }
       
	}

}
