package ua.nure.storozhuk.SummaryTask4.command;

import java.io.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Abstract basic hierarchy class to implement the "Command" design pattern.
 * Every inherited class must implement abstract method "execute"
 */
public abstract class Command implements Serializable {
	private static final long serialVersionUID = 8879403039606311780L;

	public abstract String execute(HttpServletRequest request, HttpServletResponse response);

	@Override
	public final String toString() {
		return getClass().getSimpleName();
	}
}