package org.randompage.samples.struts2.demo.test.utils;

import org.mortbay.jetty.Server;

import org.mortbay.jetty.webapp.WebAppContext;
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
		server.addHandler(webAppContext);
		server.start();
	}

	@AfterClass
	public void tearDown() throws Exception {
		if (server != null)
			server.stop();
	}
}
