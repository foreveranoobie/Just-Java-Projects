package ua.nure.storozhuk.SummaryTask4.command;

import java.io.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class Command implements Serializable {
	private static final long serialVersionUID = 8879403039606311780L;

	public abstract String execute(HttpServletRequest request,
			HttpServletResponse response);

	@Override
	public final String toString() {
		return getClass().getSimpleName();
	}
}