package gwtDemo.client.service;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.json.client.*;
import com.google.gwt.user.client.rpc.AsyncCallback;

import gwtDemo.shared.domain.LocalMessage;
import gwtDemo.shared.domain.LocalMessageGroup;

/**
 * Fetch messages stored on server in JSON format.
 * 
 * @author Lars Tackmann
 */
public class LanguageService implements LanguageServiceAsync {
	@Override
	public void getLocalMessageGroup(String groupId, AsyncCallback<LocalMessageGroup> callback) {
		ResponseMapper<LocalMessageGroup> mapper = new ResponseMapper<LocalMessageGroup>() {
			@Override
			public LocalMessageGroup mapJsonObject(JSONObject jsonObject) {
				return LocalMessageGroup.fromJsonObject(jsonObject);
			}
		};
		executeRequest("messages/groups/" + groupId, callback, mapper);
	}

	@Override
	public void getLocalMessage(String messageId, AsyncCallback<LocalMessage> callback) {
		ResponseMapper<LocalMessage> handler = new ResponseMapper<LocalMessage>() {
			@Override
			public LocalMessage mapJsonObject(JSONObject jsonObject) {
				return LocalMessage.fromJsonObject(jsonObject);
			}
		};
		executeRequest("messages/" + messageId, callback, handler);
	}

	private <T> void executeRequest(String resourceUrl, final AsyncCallback<T> callback, final ResponseMapper<T> mapper) {
		final String restService = "/service/v1/";

		try {
			getRequestBuilder(restService + resourceUrl).sendRequest(null, new RequestCallback() {
				public void onResponseReceived(Request request, Response response) {
					if (response.getStatusCode() == Response.SC_OK) {
						T result = mapper.mapResponse(response.getText());
						callback.onSuccess(result);
					}
				}

				public void onError(Request request, Throwable exception) {
					callback.onFailure(exception);
				}
			});
		} catch (RequestException e) {
			e.printStackTrace();
		}
	}

	// create HTTP request in public method so we can override it
	public <T> RequestBuilder getRequestBuilder(String url) {
		return new RequestBuilder(RequestBuilder.GET, url);
	}

	private abstract class ResponseMapper<T> {
		public T mapResponse(String jsonText) {
			JSONValue jsonValue = JSONParser.parseStrict(jsonText);
			return mapJsonObject(jsonValue.isObject());
		}

		protected abstract T mapJsonObject(JSONObject jsonObject);
	}
}
