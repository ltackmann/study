package selenium;

import static org.junit.Assert.assertTrue;

import com.thoughtworks.selenium.DefaultSelenium;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class SeleniumCucumberStepDef {
	private DefaultSelenium seleniumClient = new DefaultSelenium("localhost", 4465, "*firefox", "http://google.com");

	@Before
	public void initSelenium() throws Exception {
		seleniumClient.start();
	}

	@Given("^I am a person interested in Schantz$")
	public void step1() {
	}

	@When("^When I google for Schantz$")
	public void step2() {
		seleniumClient.open("/#q=schantz");
		seleniumClient.waitForPageToLoad("2000");
	}

	@Then("^Then I should see Schantz byline")
	public void step3() {
		System.out.println(seleniumClient.getBodyText());
		assertTrue(seleniumClient.isTextPresent("Schantz: New standards"));
	}

	@After
	public void destroySelenium() {
		seleniumClient.stop();
	}
}