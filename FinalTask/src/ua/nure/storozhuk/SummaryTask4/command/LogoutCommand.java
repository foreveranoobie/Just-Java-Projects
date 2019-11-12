package ua.nure.storozhuk.SummaryTask4.command;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutCommand extends Command{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String lang = null;
	    if(request.getSession().getAttribute("language")!=null) {
	    	lang = request.getSession().getAttribute("language").toString();
	    }
		request.getSession().invalidate();
		if(lang != null) {
		    request.setAttribute("language", lang);	
		}
		String forward="";
		try {
			forward = "redirect";
			response.sendRedirect("index.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return forward;
	}

}
