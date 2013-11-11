package gwtDemo.client.service;

import gwtDemo.shared.LocalMessage;
import gwtDemo.shared.LocalMessageGroup;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface LanguageServiceAsync {
	void getLocalMessageGroup(String groupId, AsyncCallback<LocalMessageGroup> localMessageGroup); 

    void getLocalMessage(String messageId,  AsyncCallback<LocalMessage> localMessage);
}
