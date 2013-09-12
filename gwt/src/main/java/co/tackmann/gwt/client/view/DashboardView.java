package co.tackmann.gwt.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import org.randompage.bookmarking.frontend.client.resource.i18n.Messages;
import org.randompage.bookmarking.frontend.client.view.presenter.DashboardViewPresenter;
import org.randompage.bookmarking.frontend.shared.UserDTO;

public class DashboardView extends Composite implements DashboardViewPresenter.Display {
    private static DashboardViewUiBinder uiBinder = GWT.create(DashboardViewUiBinder.class);

    @UiTemplate("dashboardView.ui.xml")
    interface DashboardViewUiBinder extends UiBinder<HTMLPanel, DashboardView> {
    }
    private final Messages messages;

    @UiField
    Label account;

    public DashboardView() {
        initWidget(uiBinder.createAndBindUi(this));
        messages = GWT.create(Messages.class);
    }

    @Override
    public void showAccount(UserDTO user) {
        account.setText(messages.account(user.getEmail()));
    }

    @Override
    public Widget asWidget() {
        return this;
    }
}
