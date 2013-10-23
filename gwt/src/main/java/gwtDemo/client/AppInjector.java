package gwtDemo.client;

import gwtDemo.client.resource.i18n.ClientMessages;
import gwtDemo.client.service.UserServiceAsync;

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.google.web.bindery.event.shared.EventBus;

@GinModules(AppModule.class)
public interface AppInjector extends Ginjector {
	EventBus getEventBus();
	
	UserServiceAsync getUserService(); 
	
	ClientMessages getClientMessages();
}
