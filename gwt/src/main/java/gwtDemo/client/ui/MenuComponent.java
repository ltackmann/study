package gwtDemo.client.ui;

import gwtDemo.client.resource.i18n.Messages;
import gwtDemo.client.ui.presenter.MenuPresenter;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.inject.Inject;

public class MenuComponent  extends Composite implements MenuPresenter.Display {
	private MenuPresenter presenter;
	   
	@UiTemplate("menuComponent.ui.xml")
    interface MenuComponentUiBinder extends UiBinder<HTMLPanel, ContentComponent> { }

    @UiConstructor
    public MenuComponent(String identifier) {
        getElement().setId(identifier);
    }
    
    @Inject 
    void init(MenuComponentUiBinder binder, MenuPresenter presenter, Messages messages) {
      initWidget(binder.createAndBindUi(this));
      this.presenter = presenter;
      this.messages = messages;
    }
}
