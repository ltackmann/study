package servlets;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.notNullValue;

import org.testng.annotations.Test;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * Test web interface
 * 
 * @author Lars Tackmann
 */
public class WebTest extends WebTester {
	@Test
	public void test() throws Exception {
		WebClient wc = new WebClient();
		HtmlPage page = (HtmlPage) wc.getPage("http://localhost:9009/index.html");
		assertThat(page, notNullValue());
		assertThat(page.asXml(), containsString("HTML Test Page"));

		// HtmlForm form = page.getFormByName("f");
		// HtmlTextInput searchValue = (HtmlTextInput) form.getInputByName("q");
		// searchValue.setValueAttribute("Coca-Cola");
		// HtmlSubmitInput button = (HtmlSubmitInput) form.getInputByName("btnG"
		// );
		// HtmlPage searchResult = (HtmlPage) form.submit(button);
	}
}
