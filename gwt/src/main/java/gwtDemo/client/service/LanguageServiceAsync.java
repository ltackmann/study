package gwtDemo.client.service;

import gwtDemo.shared.domain.LocalMessage;
import gwtDemo.shared.domain.LocalMessageGroup;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface LanguageServiceAsync {
	void getLocalMessageGroup(String groupId, AsyncCallback<LocalMessageGroup> localMessageGroup); 

    void getLocalMessage(String messageId,  AsyncCallback<LocalMessage> localMessage);
}
