package jaxrsDemo.test.utils;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

import org.mortbay.jetty.Server;
import org.mortbay.jetty.webapp.WebAppContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

/**
 * Web container based testing
 * 
 * @author Lars Tackmann
 */
public abstract class WebRunner extends SpringTester {
	private final int port;
	protected final String url;
	private Server server;
	private Client client;

	protected WebRunner() {
		this(3000);
	}

	protected WebRunner(int port) {
		this.port = port;
		this.url = String.format("http://localhost:%s/resources/", port);
	}

	protected WebTarget getResource(String relativeUrl) {
		String realUrl = url + relativeUrl;
		return client.target(realUrl);
	}

	@BeforeClass
	protected final void setUp() throws Exception {
		server = new Server(port);
		WebAppContext webAppContext = new WebAppContext("src/main/webapp", "/");
		webAppContext.setConfigurationClasses(new String[] {
				"org.mortbay.jetty.webapp.WebInfConfiguration",
				"org.mortbay.jetty.webapp.WebXmlConfiguration", });
		server.addHandler(webAppContext);
		server.start();

		client = Client.create();
	}

	@AfterClass
	protected final void tearDown() throws Exception {
		if (server != null) {
			server.stop();
		}
	}
}
