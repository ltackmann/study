package gwtDemo.test;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.eclipse.jetty.webapp.WebAppContext;

public abstract class WebRunner {
	public WebRunner() {
		// Create an embedded Jetty server on port 8080
        Server server = new Server(8080);
 
        // If your app isn't packaged into a WAR, you can do this instead
        WebAppContext handler = new WebAppContext();
        handler.setResourceBase("./war");
        handler.setDescriptor("./war/WEB-INF/web.xml");
        handler.setContextPath("/");
        handler.setParentLoaderPriority(true);
 
        // Add it to the server
        server.setHandler(handler);
 
        // Other misc. options
        server.setThreadPool(new QueuedThreadPool());
 
        // And start it up
        try {
			server.start();
			server.join();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}


