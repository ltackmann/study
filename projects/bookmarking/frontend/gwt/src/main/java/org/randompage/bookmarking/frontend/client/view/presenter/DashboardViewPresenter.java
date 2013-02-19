package org.randompage.bookmarking.frontend.client.view.presenter;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import org.randompage.bookmarking.api.domain.Role;
import org.randompage.bookmarking.frontend.shared.UserDTO;

public class DashboardViewPresenter implements ViewPresenter {
    private final Display display;

    public interface Display {
        void showAccount(UserDTO user);

        Widget asWidget();
    }

    public DashboardViewPresenter(Display display, UserDTO user) {
        this.display = display;
        if (user.getRole().equals(Role.USER)) {
            display.showAccount(user);
        }
    }

    @Override
    public void go(HasWidgets container) {
        container.clear();
        container.add(display.asWidget());
    }
}
