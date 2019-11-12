package ua.nure.storozhuk.practice8.db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import ua.nure.storozhuk.practice8.db.entity.Team;
import ua.nure.storozhuk.practice8.db.entity.User;

public class DBManager {
	Connection conn;
	Statement st;
	ResultSet rs;

	public static DBManager getInstance() {
		return new DBManager();
	}

	public DBManager() {
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream("app.properties"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		String url = prop.getProperty("connection.url");
		try {
			conn = DriverManager.getConnection(url);
			conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (rs != null) {
			try {
				rs.next();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void updateTeam(Team team) {
		try {
			st.execute("update teams set name = '" + team.getName() + "' where id = " + team.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteTeam(Team team) {
		try {
			st.execute("delete from teams where name = '" + team.getName() + "'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Team> getUserTeams(User user) {
		List<Team> userTeams = new LinkedList<>();
		try {
			rs = st.executeQuery("select users_teams.team_id, teams.name from teams" + " inner join users_teams "
					+ "on users_teams.team_id = teams.id" + " and users_teams.user_id = " + user.getId());
			if (!rs.next()) {
				return userTeams;
			} else {
				do {
						userTeams.add(new Team(rs.getInt(1), rs.getString(2)));
				}while(rs.next());
				return userTeams;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userTeams;
	}

	public boolean setTeamsForUser(User user, Team... teams) {
		try {
			for (Team team : teams) {
				PreparedStatement prepState = conn
						.prepareStatement("INSERT INTO users_teams(user_id, team_id)" + "VALUES (?,?)");
				prepState.setInt(1, user.getId());
				prepState.setInt(2, team.getId());
				prepState.execute();
			}
			conn.commit();
			return true;
		} catch (SQLException e) {
			try {
				conn.rollback();
				return false;
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return false;
	}

	public User getUser(String login) {
		try {
			rs = st.executeQuery("SELECT * FROM users WHERE users.login = '" + login + "'");
			if (rs.next()) {
				return new User(rs.getInt(1), rs.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Team getTeam(String name) {
		try {
			rs = st.executeQuery("SELECT * FROM teams WHERE teams.name = '" + name + "'");
			if (rs.next()) {
				return new Team(rs.getInt(1), rs.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Team> findAllTeams() {
		ArrayList<Team> array = new ArrayList<>();
		try {
			rs = st.executeQuery("SELECT * FROM teams ORDER BY id");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			while (rs.next()) {
				array.add(new Team(rs.getInt(1), rs.getString(2)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return array;
	}

	public List<User> findAllUsers() {
		ArrayList<User> array = new ArrayList<>();
		try {
			rs = st.executeQuery("SELECT * FROM users ORDER BY id");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			while (rs.next()) {
				array.add(new User(rs.getInt(1), rs.getString(2)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return array;
	}

	public void insertTeam(Team team) throws SQLException {
		try {
			st.executeUpdate("INSERT INTO teams (name) VALUES ('" + team.getName() + "')",
					Statement.RETURN_GENERATED_KEYS);
			ResultSet result = st.getGeneratedKeys();
			if (result.last()) {
				team.setId(result.getInt(1));
				conn.commit();
			}
		} catch (SQLException e) {
			conn.rollback();
			throw e;
		}
	}

	public void insertUser(User user) throws SQLException {
		try {
			st.executeUpdate("INSERT INTO users (login) VALUES ('" + user.getLogin() + "')",
					Statement.RETURN_GENERATED_KEYS);
			ResultSet result = st.getGeneratedKeys();
			if (result.last()) {
				user.setId(result.getInt(1));
				conn.commit();
			}
		} catch (SQLException e) {
			conn.rollback();
			throw e;
		}
	}

	public void closeCon() {
		try {
			st.close();
			rs.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
