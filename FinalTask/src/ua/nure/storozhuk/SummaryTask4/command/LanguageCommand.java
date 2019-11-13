package ua.nure.storozhuk.SummaryTask4.command;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Command type object to change interface language
 *
 */
public class LanguageCommand extends Command {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(LanguageCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		LOG.debug("started");
		String forward = "";
		if ("en".equals(request.getSession().getAttribute("language"))) {
			request.getSession().setAttribute("language", "ru");
			LOG.trace("Session language has been changed to russian");
		} else {
			request.getSession().setAttribute("language", "en");
			LOG.trace("Session language has been changed to english");
		}
		try {
			response.sendRedirect("index.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
		forward = "redirect";
		LOG.debug("finished");
		return forward;
	}

}
