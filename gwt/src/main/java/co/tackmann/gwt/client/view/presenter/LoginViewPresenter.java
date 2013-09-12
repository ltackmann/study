package co.tackmann.gwt.client.view.presenter;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class LoginViewPresenter implements ViewPresenter {
    private final Display display;

    public interface Display {
        Widget asWidget();
    }

    public LoginViewPresenter(Display display) {
        this.display = display;
    }

    @Override
    public void go(HasWidgets container) {
        container.clear();
        container.add(display.asWidget());
    }
}
