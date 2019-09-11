package exer34;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        ExtBase extB = new ExtBase();
        ExtBase ext2 = extB.callRet(new ExtBase());
        System.out.println(ext2.getClass().getSimpleName());
	}

}
