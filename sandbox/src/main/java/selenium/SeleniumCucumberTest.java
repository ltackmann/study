package selenium;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.server.RemoteControlConfiguration;
import org.openqa.selenium.server.SeleniumServer;

import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@cucumber.api.CucumberOptions(format = { "json:target/report.json" })
public class SeleniumCucumberTest {
	public static SeleniumServer seleniumserver;

	@BeforeClass
	public static void setUp() throws Exception {
		RemoteControlConfiguration remoteConfiguration = new RemoteControlConfiguration();
		remoteConfiguration.setPort(4465); 

		// create and start proxy server
		seleniumserver = new SeleniumServer(remoteConfiguration);
		seleniumserver.start();
	}
	
	@AfterClass
	public static void tearDown() throws InterruptedException {
		// stop server
		seleniumserver.stop();
	}
}


