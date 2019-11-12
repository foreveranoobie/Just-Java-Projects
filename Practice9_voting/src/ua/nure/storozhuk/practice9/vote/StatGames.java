package ua.nure.storozhuk.practice9.vote;

import java.util.HashMap;
import java.util.HashSet;

public class StatGames {
    public static HashMap<String, Integer> games;
    public static HashSet<String> sessions;
    static {
    	games = new HashMap<>();
    	sessions = new HashSet<>();
    	games.put("Volleyball", 0);
    	games.put("Basketball", 0);
    	games.put("Football", 0);
    }
}
