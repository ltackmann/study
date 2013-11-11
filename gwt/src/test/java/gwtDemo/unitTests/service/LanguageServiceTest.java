package gwtDemo.unitTests.service;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.util.Map;

import gwtDemo.client.service.LanguageServiceClient;
import gwtDemo.shared.LocalMessageGroup;

import org.junit.Test;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.Response;

import static gwtDemo.unitTests.testUtils.RequestCallbackTester.returnResponse;

/**
 * Test JSON message handler
 *
 * @author Lars Tackmann
 */
public class LanguageServiceTest {
    @Test
    public void testMessageGroup() {
        final String json = "" +
                "{ \"group\": \"Group One\", \"messages\": [\n" +
                "         { \"id\": \"msg-1\", \"text\": \"Text One\" },\n" +
                "         { \"id\": \"msg-2\", \"text\": \"Text Two\" }\n" +
                "]}";
        // build mock response
        Request request = mock(Request.class);
        Response response = mock(Response.class);
        when(response.getStatusCode()).thenReturn(Response.SC_OK);
        when(response.getText()).thenReturn(json);

        LanguageServiceClient languageService = spy(new LanguageServiceClient());
        doAnswer(returnResponse(request, response)).when(languageService.getRequestBuilder(anyString()));
        // TODO use JavaScript object to share between server and client
        // http://www.gwtproject.org/doc/latest/tutorial/JSON.html JsonUtils.safeEval
        LocalMessageGroup messageGroup = languageService.getLocalMessages("");
        assertEquals(messageGroup.getMessage("msg-1").getMessageText(), "Text One");
        assertEquals(messageGroup.getMessage("msg-2").getMessageText(), "Text Two");
        assertEquals(messageGroup.getLanguage(), "English");
    }

    @Test
    public void testMessage() {
        assert false;
    }
}
