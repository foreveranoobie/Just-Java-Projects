package ua.nure.storozhuk.SummaryTask4.sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DataBaseMain {
	Connection conn;
	Statement st;
	ResultSet rs;

	public DataBaseMain() throws ClassNotFoundException {
		Context envContext = null;
		try {
			envContext = new InitialContext();
			Context initContext = (Context) envContext.lookup("java:/comp/env");
			DataSource ds = (DataSource) initContext.lookup("jdbc/finalDB");
			conn = ds.getConnection();
			st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		} catch (NamingException | SQLException e1) {
			e1.printStackTrace();
		}
		if (rs != null) {
			try {
				rs.next();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void closeCon() {
		try {
			st.close();
			if (rs != null) {
				rs.close();
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
