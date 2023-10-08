package gwtDemo.test.utils;

import com.google.gwt.user.client.rpc.AsyncCallback;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

@SuppressWarnings("rawtypes")
public class AsyncCallbackTester {
    /**
     * Mock failed  AsyncCallback
     *
     * @param throwable Error to throw by failed call
     * @see com.google.gwt.user.client.rpc.AsyncCallback#onFailure(Throwable)
     */
    public static Answer throwError(final Throwable throwable) {
        return new Answer() {
            public AsyncCallback<?> answer(InvocationOnMock invocation)
                    throws Throwable {
                AsyncCallback callback = getCallback(invocation);
                callback.onFailure(throwable);
                return callback;
            }
        };
    }

    /**
     * Mock successful AsyncCallback
     *
     * @param result Value to return by successful call
     * @see com.google.gwt.user.client.rpc.AsyncCallback#onSuccess(Object)
     */
    @SuppressWarnings("unchecked")
    public static <T> Answer<T> returnValue(final T result) {
        return new Answer() {
            public AsyncCallback<T> answer(InvocationOnMock invocation)
                    throws Throwable {
                AsyncCallback<T> callback = getCallback(invocation);
                callback.onSuccess(result);
                return callback;
            }
        };
    }
    
    private static AsyncCallback getCallback(InvocationOnMock invocation) {
        for (Object object : invocation.getArguments()) {
            if (object instanceof AsyncCallback) {
                return (AsyncCallback) object;
            }
        }
        throw new IllegalArgumentException("Missing AsyncCallback object");
    }
}
