package ua.nure.storozhuk.Practice10;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

@WebServlet("/controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Controller() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("command") != null) {
			HttpSession session = request.getSession();
			session.removeAttribute("role");
			session.removeAttribute("login");
			session.removeAttribute("password");
			response.sendRedirect("index.jsp");
		} else {
			response.getWriter().append("removing users");
			request.getSession().removeAttribute("users");
			response.sendRedirect("part3_1.jsp");
		}
	}

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("command") != null) {
			Context envContext = null;
			try {
				envContext = new InitialContext();
				Context initContext = (Context) envContext.lookup("java:/comp/env");
				DataSource ds = (DataSource) initContext.lookup("jdbc/testDB");
				Connection con = ds.getConnection();
				Statement stmt = con.createStatement();
				String query = "select * from users where login='" + request.getParameter("login") + "' and password='"
						+ request.getParameter("password") + "'";
				ResultSet rs = stmt.executeQuery(query);
				if (rs.next()) {
					request.getSession().setAttribute("login", rs.getString(1));
					request.getSession().setAttribute("password", rs.getString(2));
					if (rs.getInt(3) == 1) {
						request.getSession().setAttribute("role", "admin");
					} else {
						request.getSession().setAttribute("role", "client");
					}
					response.sendRedirect("index.jsp");
				} else {
					response.sendRedirect("part4.jsp");
				}
			} catch (NamingException | SQLException e) {
				e.printStackTrace();
			}
		} else {
			List<String> list;
			list = (List<String>) request.getSession().getAttribute("users");
			if (list == null) {
				list = new LinkedList<String>();
				list.add(request.getParameter("login"));
				request.getSession().setAttribute("users", list);
			} else {
				list.add(request.getParameter("login"));
			}
			response.sendRedirect("part3_1.jsp");
		}
	}

}
