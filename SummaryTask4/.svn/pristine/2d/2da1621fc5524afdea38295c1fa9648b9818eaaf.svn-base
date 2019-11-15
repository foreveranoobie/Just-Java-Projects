package ua.nure.storozhuk.SummaryTask4.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.storozhuk.SummaryTask4.sql.entity.User;

/**
 * Servlet Filter implementation class ControllerFilter Filter catches requests
 * created by blocked or unlogged users to prevent access to valuable sources
 */
@WebFilter(urlPatterns = { "/controller" })
public class ControllerFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public ControllerFilter() {
		// TODO Auto-generated constructor stub
	}

	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * Filtrates the User information to prevent using requests to controller
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		User user = (User) req.getSession().getAttribute("user");
		HttpServletResponse res = (HttpServletResponse) response;
		if (user != null) {
			if (user.getStatus().equals("studentBlocked") && !req.getParameter("command").equals("logout")
					&& !req.getParameter("command").equals("changeLanguage")) {
				res.sendRedirect("studentBlocked.jsp");
				return;
			} else if (user.getStatus().equals("waiting") && !req.getParameter("command").equals("logout")
					&& !req.getParameter("command").equals("changeLanguage")) {
				res.sendRedirect("index.jsp");
				return;
			}
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
