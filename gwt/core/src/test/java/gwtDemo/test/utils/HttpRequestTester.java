package gwtDemo.test.utils;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class HttpRequestTester {
	/**
     * Create a RequestBuilder that will execute a HTTP request successfully and call onResponseRecieved 
     * on the callback using with the passed request and response parameters 
     */
	public static RequestBuilder mockSuccessfulHttpRequest(final Request request, final Response response) {
		RequestBuilder requestBuilder = mock(RequestBuilder.class);
		try {
			doAnswer(new Answer<Object>() {
			    public Object answer(InvocationOnMock invocation) {
			    	RequestCallback callback = getCallback(invocation);
			    	callback.onResponseReceived(request, response);
			        return callback;
			    }
			}).when(requestBuilder).sendRequest(any(String.class), any(RequestCallback.class));
		} catch (RequestException e) {
			e.printStackTrace();
		}
		return requestBuilder;
	}
	
    /**
     * Create a RequestCallback that will fail if any method is executed on it
     *
     * @param request Request that should fail 
     * @param throwable Error to throw by failed call
     * @see com.google.gwt.http.client.RequestCallback#onError(com.google.gwt.http.client.Request, Throwable)
     */
    public static Answer<RequestCallback> mockRequestCallbackError(final Request request, final Throwable throwable) {
        return new Answer<RequestCallback>() {
            public RequestCallback answer(InvocationOnMock invocation) throws Throwable {
                RequestCallback callback = getCallback(invocation);
                callback.onError(request, throwable);
                return callback;
            }
        };
    }

    /**
     * Create a RequestCallback that will succeed if any method is executed on it
     *
     * @param request  Request that should succeed
     * @param response Response to return on successful call
     * @see com.google.gwt.http.client.RequestCallback#onResponseReceived(com.google.gwt.http.client.Request, com.google.gwt.http.client.Response)
     */
    public static Answer<RequestCallback> mockRequestCallbackSuccess(final Request request, final Response response) {
        return new Answer<RequestCallback>() {
            public RequestCallback answer(InvocationOnMock invocation) throws Throwable {
                RequestCallback callback = getCallback(invocation);
                callback.onResponseReceived(request, response);
                return callback;
            }
        };
    }
    
    private static RequestCallback getCallback(InvocationOnMock invocation) {
        for (Object object : invocation.getArguments()) {
            if (object instanceof RequestCallback) {
                return (RequestCallback) object;
            }
        }
        throw new IllegalArgumentException("Missing RequestCallback object");
    }
}