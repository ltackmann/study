package gwtDemo.client.ui;

import gwtDemo.client.resource.i18n.ClientMessages;
import gwtDemo.client.ui.presenter.MenuPresenter;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.inject.Inject;

public class MenuComponent extends Composite implements MenuPresenter.Display {
	private MenuPresenter presenter;
	   
	@UiTemplate("menuComponent.ui.xml")
    interface MenuComponentUiBinder extends UiBinder<HTMLPanel, MenuComponent> { }

    @UiConstructor
    public MenuComponent(String identifier) {
        getElement().setId(identifier);
    }
    
    @Inject 
    void init(MenuComponentUiBinder binder, MenuPresenter presenter, ClientMessages messages) {
      initWidget(binder.createAndBindUi(this));
      this.presenter = presenter;
    }
}
