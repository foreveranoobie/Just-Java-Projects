package ua.nure.storozhuk.SummaryTask4;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import javax.servlet.jsp.tagext.TagSupport;

public class LocalizerTag extends SimpleTagSupport {
	private static final long serialVersionUID = 1L;
	String locale;
	String word;

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public void doTag() throws JspException, IOException {
		if (locale == null || locale == "en") {
			JspWriter out = getJspContext().getOut();
			out.print(word);
		} else {
			Locale locale2 = new Locale(locale);
			ResourceBundle exampleBundle = ResourceBundle.getBundle("ua.nure.storozhuk.SummaryTask4.Words", locale2);
			String result = "";
			try {
				result = exampleBundle.getString("en." + word);
			} catch (Exception e) {
				result = word;
			}
			JspWriter out = getJspContext().getOut();
			out.print(result);
		}
	}
}
