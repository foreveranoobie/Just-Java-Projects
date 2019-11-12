package ua.nure.storozhuk.SummaryTask4.command;

import java.util.Map;
import java.util.TreeMap;

public class CommandContainer {
	private static Map<String, Command> commands = new TreeMap<String, Command>();
	static {
		commands.put("loginUser", new LoginCommand());
		commands.put("logout", new LogoutCommand());
		commands.put("signUser", new SignUpCommand());
		commands.put("getUsers", new GetUsersCommand());
		commands.put("getCourses", new GetCoursesCommand());
		commands.put("getJournal", new JournalCommand());
		commands.put("studentCourses", new StudentCoursesCommand());
		commands.put("changeLanguage", new LanguageCommand());
		commands.put("sortCourses", new SortingCoursesCommand());
	}

	public static Command get(String commandName) {
		if (commandName == null || !commands.containsKey(commandName)) {
			return commands.get("noCommand"); 
		}
		
		return commands.get(commandName);
	}
}
