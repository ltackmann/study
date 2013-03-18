package org.randompage.bookmarking.frontend.client.resource.i18n;

import com.google.gwt.http.client.RequestBuilder;
import org.junit.Test;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.Response;

import static org.mockito.Mockito.*;
import static org.randompage.bookmarking.frontend.testUtils.RequestCallbackTester.returnResponse;

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
