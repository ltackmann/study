package gwtDemo.client.app;

import gwtDemo.client.event.LanguageChanged;
import gwtDemo.client.event.LanguageChangedHandler;
import gwtDemo.client.framework.AppController;
import gwtDemo.client.framework.Page;
import gwtDemo.client.pages.MainPage;

public class DemoAppController extends AppController {
	@Override
    protected void registerEventHandlers() {
        // configure operations to be carried out when events are fired
        addHandler(LanguageChanged.TYPE,
                new LanguageChangedHandler() {
					@Override
					public void onLanguageChanged(LanguageChanged event) {
						// reload current page with new language texts
						navigationManager.reloadPage();
					}
                });
    }
    
	@Override
	protected Class<? extends Page> getDefaultPage() {
		return MainPage.class;
	}
}
