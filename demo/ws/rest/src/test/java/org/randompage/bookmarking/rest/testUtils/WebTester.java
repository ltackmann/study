package org.randompage.bookmarking.rest.testUtils;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.webapp.WebAppContext;
import org.randompage.bookmarking.testUtils.IntegrationTester;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

/**
 * Run tests using a container as configured by this applications web.xml
 * 
 * @author Lars Tackmann
 */
public abstract class WebTester {
	private Logger logger = LoggerFactory.getLogger(WebTester.class);
	private static final int port = 3000;
	private static final String baseUrl = "/resources";
	private static final String url = "http://localhost:" + port + baseUrl;
	private static Server server;
	private static Client client;

	protected WebResource url(String relativeUrl) {
		String realUrl = url + relativeUrl;
		logger.debug("using url: " + realUrl);
		return client.resource(realUrl);
	}

	// start server
	@BeforeClass
	public static void setUp() throws Exception {
		client = Client.create();
		server = new Server(port);
		WebAppContext webAppContext = new WebAppContext("src/test/webapp", "/");
		webAppContext.setConfigurationClasses(new String[] {
				"org.mortbay.jetty.webapp.WebInfConfiguration",
				"org.mortbay.jetty.webapp.WebXmlConfiguration" });
		server.addHandler(webAppContext);
		server.start();
	}

	// shutdown server
	@AfterClass
	public static void tearDown() throws Exception {
		if (server != null)
			server.stop();
	}
}
