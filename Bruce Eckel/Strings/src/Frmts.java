import java.util.Formatter;

public class Frmts {
   public static void main(String[] args) {
	   double num = 4.4442;
	   System.out.printf("%.2f",num);
	   System.out.printf("kek\n");
	   System.out.println(new strFrm());
   }
}
class strFrm{
	public String toString() {
		return String.format("%010d", 15555);
	}
}