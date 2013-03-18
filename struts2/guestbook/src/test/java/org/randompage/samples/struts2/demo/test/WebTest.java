package org.randompage.samples.struts2.demo.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.notNullValue;

import org.randompage.samples.struts2.demo.test.utils.WebTester;
import org.testng.annotations.Test;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * Test web interface
 * 
 * @author Lars Tackmann
 */
public class WebTest extends WebTester {
	@Test(description = "should assert navigation rules")
	public void navigationTest() {
		throw new AssertionError();
	}

	@Test(description = "should assert that we do not have raw JSP access")
	public void jspTest() {
		throw new AssertionError();
	}

	@Test(description = "should access input action")
	public void test() throws Exception {
		WebClient wc = new WebClient();
		// TODO use relative URL
		HtmlPage page = (HtmlPage) wc
				.getPage("http://localhost:8080/guestbook/GuestBook_list.action");
		assertThat(page, notNullValue());
		assertThat(page.asXml(), containsString("stuff"));

		// HtmlForm form = page.getFormByName("f");
		// HtmlTextInput searchValue = (HtmlTextInput) form.getInputByName("q");
		// searchValue.setValueAttribute("Coca-Cola");
		// HtmlSubmitInput button = (HtmlSubmitInput) form.getInputByName("btnG"
		// );
		// HtmlPage searchResult = (HtmlPage) form.submit(button);
	}
}
