package gwtDemo.client.view.presenter;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class MainViewPresenter implements ViewPresenter {
    private final Display display;

    public interface Display {
        Widget asWidget();
    }

    public MainViewPresenter(Display display) {
        this.display = display;
    }

    @Override
    public void go(HasWidgets container) {
        container.clear();
        container.add(display.asWidget());
    }
}
