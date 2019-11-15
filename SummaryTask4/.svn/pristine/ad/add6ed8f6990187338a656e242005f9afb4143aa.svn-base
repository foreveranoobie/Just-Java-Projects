package ua.nure.storozhuk.SummaryTask4.listeners;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import org.apache.log4j.Logger;

/**
 * Application Lifecycle Listener implementation class SessionListenerImpl
 * Listener to log changes with session attributes
 */
@WebListener
public class SessionListenerImpl implements HttpSessionAttributeListener {
	private static final Logger LOG = Logger.getLogger(SessionListenerImpl.class);

	/**
	 * Default constructor.
	 */
	public SessionListenerImpl() {
		// TODO Auto-generated constructor stub
	}

	public void attributeAdded(HttpSessionBindingEvent arg0) {
		LOG.trace("Attribute added " + arg0.getName() + " " + arg0.toString());
	}

	public void attributeRemoved(HttpSessionBindingEvent arg0) {
		LOG.trace("Attribute removed " + arg0.getName() + " " + arg0.toString());
	}

	public void attributeReplaced(HttpSessionBindingEvent arg0) {
		LOG.trace("Attribute replaced " + arg0.getName() + " " + arg0.toString());
	}

}
