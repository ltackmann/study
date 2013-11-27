package servlets;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

/**
 * Web container based testing
 * 
 * @author Lars Tackmann
 */
public abstract class WebTester {
	private final int port;
	private Server server;

	protected WebTester() {
		this(9009);
	}

	protected WebTester(int port) {
		this.port = port;
	}

	@BeforeClass
	public void setUp() throws Exception {
		server = new Server(port);
		WebAppContext webAppContext = new WebAppContext("src/main/webapp", "/");
		webAppContext.setConfigurationClasses(new String[] {
				"org.mortbay.jetty.webapp.WebInfConfiguration",
				"org.mortbay.jetty.webapp.WebXmlConfiguration", });
		server.setHandler(webAppContext);
		server.start();
	}

	@AfterClass
	public void tearDown() throws Exception {
		if (server != null)
			server.stop();
	}
}
