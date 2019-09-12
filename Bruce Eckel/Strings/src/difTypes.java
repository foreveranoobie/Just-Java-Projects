import java.util.Scanner;

public class difTypes {

	public static void main(String[] args) {
        typeS tS = new typeS("1 21500000000 8,32143 1,23142354 kek");
        System.out.println(tS);
	}

}

class typeS{
	int num;
	long numL;
	float numF;
	double numD;
	String numS;
	typeS(String str){
		Scanner stdin = new Scanner(str);
		num = stdin.nextInt();
		numL = stdin.nextLong();
		numF = stdin.nextFloat();
		numD = stdin.nextDouble();
		numS = stdin.nextLine();
	}
	public String toString() {
		return num+" "+numL+" "+numF+" "+numD+" "+numS;
	}
}