package gwtDemo.client.resource;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ImageResource;

public interface Resources extends ClientBundle {
    public static final Resources INSTANCE = GWT.create(Resources.class);

    @Source("images/logo.png")
    ImageResource logo();

    @Source("css/style.css")
    Style style();

    public interface Style extends CssResource {
        String alert();

        String error();
    }
}
