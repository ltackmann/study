package org.randompage.bookmarking.frontend.client.resource.i18n;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;
import static org.randompage.bookmarking.frontend.testUtils.RequestCallbackTester.returnResponse;

import org.junit.Test;

import co.tackmann.gwt.client.resource.i18n.RemoteMessages;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.Response;

/**
 * Test JSON message handler
 *
 * @author Lars Tackmann
 * @see org.randompage.bookmarking.frontend.client.resource.i18n.JSONMessages
 */
public class RemoteMessagesTest {
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

        RemoteMessages remoteMessages = spy(new RemoteMessages());
        doAnswer(returnResponse(request, response)).when(remoteMessages.getRequestBuilder(anyString()));

        assert false;
    }

    @Test
    public void testMessage() {
        assert false;
    }
}
