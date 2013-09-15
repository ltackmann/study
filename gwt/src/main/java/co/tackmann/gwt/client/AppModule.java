package co.tackmann.gwt.client;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;
import com.google.web.bindery.event.shared.EventBus;

public class AppModule extends AbstractGinModule {
	protected void configure() {
		bind(EventBus.class).in(Singleton.class);
		//bind(MyRemoteService.class).toProvider(MyRemoteServiceProvider.class);
	}
}
