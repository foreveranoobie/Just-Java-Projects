package ua.nure.storozhuk.SummaryTask4.command;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Command object to logout user
 * 
 * @author Alex
 *
 */
public class LogoutCommand extends Command {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(LogoutCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		LOG.debug("started");
		String lang = null;
		if (request.getSession().getAttribute("language") != null) {
			lang = request.getSession().getAttribute("language").toString();
		}
		request.getSession().invalidate();
		if (lang != null) {
			LOG.debug("languade has been saved");
			request.setAttribute("language", lang);
		}
		String forward = "";
		try {
			forward = "redirect";
			response.sendRedirect("index.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
		LOG.debug("finished");
		return forward;
	}

}
