package ua.nure.storozhuk.practice6.part6;

public class Part6 {

	public static void main(String[] args) {
		boolean check = (args.length == 4);
		boolean checkIn = ("-i".equals(args[0]) || "--input".equals(args[0]));
		if (check && (checkIn) && ("-t".equals(args[2]) || ("--task".equals(args[2])))) {
			switch (args[3]) {
			case "frequency":
				Part61.main(new String[] { args[1] });
				break;
			case "length":
				Part62.main(new String[] { args[1] });
				break;
			case "duplicates":
				Part63.main(new String[] { args[1] });
				break;
			default:
				break;
			}
		}
	}
}
