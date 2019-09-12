public class algor {
	static void Vampires() {
		int arr[] = new int[4];
		int numbs[] = new int[4];
		label1:
		for(int l = 1111; l < 10000; l++) {
			label2:
			for(int k = 10; k<100;k++) {
				label3:
				for(int m = 10; m<100; m++) {
					if(k*m==l) {
					int res = l;
                       for(int div = 0; div<4; div++) {
                    	   arr[div] = res % 10;
                    	   res/=10;
                    	   if (div == 0) {
                    		   numbs[div] = k / 10;
                    		   continue;
                    	   }
                    	   if (div == 1) {
                    		   numbs[div] = k % 10;
                    		   continue;
                    	   }
                    	   if (div == 2) {
                    		   numbs[div] = m / 10;
                    	       continue;
                    	   }
                    	   if (div == 3) {
                    		   numbs[div] = m % 10;
                    		   break;
                    	   }
                       }
                       int count = 0;
                       for (int nums = 0; nums < 4; nums++) {
                    	   for(int check = 0; check < 4; check++) {
                    		   if(arr[nums] == numbs[check] && arr[nums]!=10) {
                    			   count++;
                    			   arr[nums] = numbs[check] = 10;
                    		   }
                    	   }
                       }
                       if(count==4) {
                       System.out.printf("%d * %d = %d\n", k, m, l);
                       }
					}
				}
			}
		}
	}
	public static void main(String[] args) {
	      Vampires();
	}
}


