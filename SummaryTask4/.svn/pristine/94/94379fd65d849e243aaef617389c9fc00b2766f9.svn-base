package ua.nure.storozhuk.SummaryTask4;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class LogoutTag extends SimpleTagSupport {
	String locale;

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public void doTag() throws JspException, IOException {
		JspWriter out = getJspContext().getOut();
		if (locale == null || locale == "en") {
			out.println("<form name=\"logout\" method=\"GET\" action=\"controller\">"
					+ "<input type=\"hidden\" name=\"command\" value=\"logout\" />"
					+ "<input type=\"submit\" name=\"buttOut\" value=\"Logout\" />" + "</form>");
		} else {
			out.println("<form name=\"logout\" method=\"GET\" action=\"controller\">"
					+ "<input type=\"hidden\" name=\"command\" value=\"logout\" />"
					+ "<input type=\"submit\" name=\"buttOut\" value=\"Выйти\" />" + "</form>");
		}
	}
}
