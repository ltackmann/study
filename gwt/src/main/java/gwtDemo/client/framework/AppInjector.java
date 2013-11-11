package gwtDemo.client.framework;

import gwtDemo.client.framework.api.ClientSession;
import gwtDemo.client.framework.api.NavigationManager;
import gwtDemo.client.resource.i18n.ClientMessages;
import gwtDemo.client.service.LanguageServiceAsync;
import gwtDemo.client.service.UserServiceAsync;

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.google.web.bindery.event.shared.EventBus;

@GinModules(AppModule.class)
public interface AppInjector extends Ginjector {
	ClientSession getClientSession();
	
	ClientMessages getClientMessages();
	
	EventBus getEventBus();
	
	NavigationManager getNavigationManager();
	
	UserServiceAsync getUserService();

	LanguageServiceAsync getLanguageService(); 
}
