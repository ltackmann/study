package org.randompage.samples.seam.wiki.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.digest.DigestUtils;
import org.testng.annotations.Test;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

public class UtilsTest {

	@Test(description = "should convert a string into a correct MD5")
	public void md5Test() {
		String password = "qwert";
		String commonsMd5 = DigestUtils.md5Hex(password);
		String md5Password = md5HashString(password);
		assertThat(commonsMd5, equalTo(md5Password));
	}

	@Test(description = "should search web using google.com")
	public void googleWebTest() throws Exception {
		// WebClient wc = new WebClient(BrowserVersion.FIREFOX_2, "10.17.8.10",
		// 8080);
		WebClient wc = new WebClient();
		// search for keyword
		HtmlPage page = (HtmlPage) wc.getPage("http://www.google.com");
		HtmlForm form = page.getFormByName("f");
		HtmlTextInput searchValue = (HtmlTextInput) form.getInputByName("q");
		searchValue.setValueAttribute("Coca-Cola");
		HtmlSubmitInput button = (HtmlSubmitInput) form.getInputByName("btnG");
		HtmlPage searchResult = (HtmlPage) form.submit(button);
		// match result
		assertThat(searchResult.asXml(),
				containsString("http://www.coca-cola.com"));
	}

	/**
	 * MD5 algorithm implementation
	 * 
	 * @param string
	 *            to be hashed
	 * @return hashed string
	 */
	private String md5HashString(String str) {
		// trim and convert string
		str = str.trim();
		str = str.replace(";", "\\;");
		byte[] utf8 = null;
		try {
			utf8 = str.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("failed to convert: '" + str
					+ "' to UTF-8", e);
		}

		// converter to md5 byte array
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("failed retriving MD5 converter", e);
		}
		md5.update(utf8);
		byte[] bs = md5.digest();

		// convert hex new md5 byte array back to string
		StringBuffer ret = new StringBuffer(bs.length);
		for (int i = 0; i < bs.length; i++) {
			String hex = Integer.toHexString(0x0100 + (bs[i] & 0x00FF))
					.substring(1);
			ret.append((hex.length() < 2 ? "0" : "") + hex);
		}
		return ret.toString();
	}
}
