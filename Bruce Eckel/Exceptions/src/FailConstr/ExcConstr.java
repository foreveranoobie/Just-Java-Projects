package FailConstr;

class wDisp {
	wDisp(){
		
	}
	public void dispose() {
		System.out.println("wDispose");
	}
}

class FailingConstructor {
  Object[] objs;
  wDisp dpse;
  wDisp dpse1;
  FailingConstructor(int num){
     try {
	 objs = new Object[1];
	 dpse = new wDisp();
     System.out.println(objs[num]);
     try {
    	 dpse1 = new wDisp();
         System.out.println(objs[num+1]+"");
     }catch(Exception e) {
         System.out.println("error");
     }finally {
    	 dpse1.dispose();
     }
     }catch(Exception e) {
    	 
     }
     finally {
    	 dpse.dispose();
     }
  }
  FailingConstructor(){
  }
}

public class ExcConstr {
   public static void main(String[] args) {
	   try {
		   FailingConstructor fConst = new FailingConstructor(0);
	   } catch(Exception e) {
		   FailingConstructor fConst = new FailingConstructor(0);
	   } finally {
		   System.out.println("do smt other");
	   }
   }
}

