package ua.nure.storozhuk.practice9.vote;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/vote")
public class Vote extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		StatGames stat = new StatGames();
		if (!StatGames.sessions.contains(session.getId())) {
			String typeGame = request.getParameter("game");
			int value = stat.games.get(typeGame);
			stat.games.put(typeGame, value + 1);
			response.getWriter().append(stat.games.toString());
			StatGames.sessions.add(session.getId());
		}else {
			response.getWriter().append("Session used "+ session.getId());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
