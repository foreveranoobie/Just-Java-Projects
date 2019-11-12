package ua.nure.storozhuk.practice8;

import java.sql.SQLException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import ua.nure.storozhuk.practice8.db.DBManager;
import ua.nure.storozhuk.practice8.db.entity.Team;
import ua.nure.storozhuk.practice8.db.entity.User;

public class Demo {
	private static void printList(List<?> list) {
        System.out.println(list);
    }
	
	public static void main(String[] args) {
		DBManager dbManager = new DBManager();
        User[] users = {new User(0, "user1"), new User(1, "user2"),
        		new User(2, "user3"), new User(3, "user4"), new User(4, "user5")};
        Team[] teams = {new Team(0, "team1"), new Team(1, "team2"),
        		new Team(2, "team3"), new Team(3, "team4"), new Team(4, "team5")};
        for(User user : users) {
        	try {
				dbManager.insertUser(user);
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }
        for(Team team : teams) {
        	try {
				dbManager.insertTeam(team);
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }
        dbManager.setTeamsForUser(users[0], teams[0]);
        dbManager.setTeamsForUser(users[1], teams[0], teams[1]);
        dbManager.setTeamsForUser(users[2], teams[0], teams[1], teams[2]);
        dbManager.setTeamsForUser(users[3], teams[0], teams[1], teams[2], teams[3]);
        dbManager.setTeamsForUser(users[4], teams[0], teams[1], teams[2], teams[3], teams[4]);
		for(int l = 0; l < 5; l++) {
			dbManager.getUserTeams(users[l]);
		}
        dbManager.closeCon();
	}

}
