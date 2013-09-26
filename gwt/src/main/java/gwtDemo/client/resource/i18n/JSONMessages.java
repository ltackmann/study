package gwtDemo.client.resource.i18n;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.Response;
import com.google.gwt.json.client.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Fetch messages stored on server in JSON format.
 * 
 * TODO figure out if there is a more modern way of doing JSON in GWT
 *
 * @author Lars Tackmann
 */
abstract class JSONMessages {
    private <T> T getResource(String resourceUrl, final ResponseHandler<T> responseHandler) {
        final String restService = "/service/v1/";

        try {
            getRequestBuilder(restService + resourceUrl).sendRequest(null, new RequestCallback() {
                public void onResponseReceived(Request request, Response response) {
                    if (response.getStatusCode() == Response.SC_OK) {
                        responseHandler.setResponse(response.getText());
                    }
                }

                public void onError(Request request, Throwable exception) {
                    throw new UnsupportedOperationException("Not supported yet.");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseHandler.handleResponse();
    }

    protected Map<String, String> getMessages(String groupId) {
        ResponseHandler<Map<String, String>> handler = new ResponseHandler<Map<String, String>>() {
            @Override
            public Map<String, String> handleResponse() {
                Map<String, String> messages = new HashMap<String, String>();

                // retrieve message group
                if (jsonResponse.get("group") != null) {
                    JSONArray msgArray = jsonResponse.get("messages").isArray();
                    for (int i = 0; i < msgArray.size(); i++) {
                        JSONObject message = msgArray.get(i).isObject();
                        final JSONString messageId = message.get("id").isString();

                        messages.put(messageId.stringValue(), getJSONMsg(message));
                    }
                }
                return messages;
            }
        };
        return getResource("messages/groups/" + groupId, handler);
    }

    protected String getMessage(String messageId) {
        ResponseHandler<String> handler = new ResponseHandler<String>() {
            @Override
            public String handleResponse() {
                return getJSONMsg(jsonResponse);
            }
        };
        return getResource("messages/" + messageId, handler);
    }
    
    // fetch request builder in own method to ease unit testing
    protected RequestBuilder getRequestBuilder(String url) {
        return new RequestBuilder(RequestBuilder.GET, url);
    }
    
    private String getJSONMsg(JSONObject jsonObject) {
        JSONString jsonString = jsonObject.get("text").isString();
        return jsonString.stringValue();
    }
    
    private abstract class ResponseHandler<T> {
        JSONObject jsonResponse;

        void setResponse(String responseText) {
            JSONValue jsonValue = JSONParser.parseStrict(responseText);
            jsonResponse = jsonValue.isObject();
        }

        abstract T handleResponse();
    }
}
