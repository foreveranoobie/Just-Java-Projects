package ua.nure.storozhuk.SummaryTask4.command;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LanguageCommand extends Command{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
        String forward = "";
		if("en".equals(request.getSession().getAttribute("language"))) {
        	request.getSession().setAttribute("language", "ru");
        } else {
        	request.getSession().setAttribute("language", "en");
        }
        try {
			response.sendRedirect("index.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
        forward = "redirect";
		return forward;
	}

}
