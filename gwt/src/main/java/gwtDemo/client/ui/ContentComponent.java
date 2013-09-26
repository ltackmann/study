package gwtDemo.client.ui;

import gwtDemo.client.ui.presenter.ContentPresenter;

import com.google.gwt.uibinder.client.*;
import com.google.gwt.user.client.ui.*;
import com.google.inject.Inject;

public class ContentComponent extends Composite implements ContentPresenter.Display {
	private ContentPresenter presenter;
   
	@UiTemplate("contentComponent.ui.xml")
    interface ContentComponentUiBinder extends UiBinder<HTMLPanel, ContentComponent> { }

    @UiConstructor
    public ContentComponent(String identifier) {
        getElement().setId(identifier);
    }
    
    @Inject 
    void init(UiBinder<HTMLPanel, ContentComponent> binder, ContentPresenter presenter) {
      initWidget(binder.createAndBindUi(this));
      this.presenter = presenter;
    }
}
