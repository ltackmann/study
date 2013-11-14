package gwtDemo.client.test;

import gwtDemo.shared.domain.LocalMessageGroup;

import org.junit.Test;

import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.junit.client.GWTTestCase;

/**
 * Test JSON mapping in LanguageService
 *
 * @author Lars Tackmann
 */
//@RunWith(GwtMockitoTestRunner.class)
public class LanguageServiceTest extends GWTTestCase {
	static final String json = "" +
            "{ \"group\": \"group-1\", \"messages\": [\n" +
            "         { \"key\": \"msg-1\", \"text\": \"Text One\", \"language\": \"English\" },\n" +
            "         { \"key\": \"msg-2\", \"text\": \"Text Two\", \"language\": \"English\" }\n" +
            "]}";
	
	@Test
	public void testJsonParsing() {
		JSONValue jsonValue = JSONParser.parseStrict(json);
		LocalMessageGroup result = LocalMessageGroup.fromJsonObject(jsonValue.isObject());
		assertGroup(result);
	}
    
    private void assertGroup(LocalMessageGroup group) {
    	assertEquals(group.getMessage("msg-1").getText(), "Text One");
	    assertEquals(group.getMessage("msg-2").getText(), "Text Two");
	    assertEquals(group.getLanguage(), "English");
    }

	@Override
	public String getModuleName() {
		return "gwtDemo.DemoApp";
	}
}
