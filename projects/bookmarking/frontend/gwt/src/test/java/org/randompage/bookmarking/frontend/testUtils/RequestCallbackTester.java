package org.randompage.bookmarking.frontend.testUtils;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.Response;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class RequestCallbackTester {
    private static RequestCallback getCallback(InvocationOnMock invocation) {
        for (Object object : invocation.getArguments()) {
            if (object instanceof RequestCallback) {
                return (RequestCallback) object;
            }
        }
        throw new IllegalArgumentException("Missing RequestCallback object");
    }

    /**
     * Mock failed RequestCallback
     *
     * @param request Request that should fail 
     * @param throwable Error to throw by failed call
     * @see com.google.gwt.http.client.RequestCallback#onError(com.google.gwt.http.client.Request, Throwable)
     */
    public static Answer requestError(final Request request, final Throwable throwable) {
        return new Answer() {
            public RequestCallback answer(InvocationOnMock invocation)
                    throws Throwable {
                RequestCallback callback = getCallback(invocation);
                callback.onError(request, throwable);
                return callback;
            }
        };
    }

    /**
     * Mock successful RequestCallback
     *
     * @param request  Request that should succeed
     * @param response Response to return on successful call
     * @see com.google.gwt.http.client.RequestCallback#onResponseReceived(com.google.gwt.http.client.Request, com.google.gwt.http.client.Response)
     */
    public static Answer returnResponse(final Request request, final Response response) {
        return new Answer() {
            public RequestCallback answer(InvocationOnMock invocation)
                    throws Throwable {
                RequestCallback callback = getCallback(invocation);
                callback.onResponseReceived(request, response);
                return callback;
            }
        };
    }
}