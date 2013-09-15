package co.tackmann.gwt.client;

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.google.web.bindery.event.shared.EventBus;

@GinModules(AppModule.class)
public interface AppInjector extends Ginjector {
	EventBus getEventBus();
}
