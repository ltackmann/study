package gwtDemo.client;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;

public class AppModule extends AbstractGinModule {
	protected void configure() {
		bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);
		//bind(MyRemoteService.class).toProvider(MyRemoteServiceProvider.class);
	}
}
