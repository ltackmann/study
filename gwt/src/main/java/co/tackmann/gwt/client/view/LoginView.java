package co.tackmann.gwt.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.*;
import org.randompage.bookmarking.frontend.client.resource.i18n.Messages;
import org.randompage.bookmarking.frontend.client.resource.i18n.RemoteMessages;
import org.randompage.bookmarking.frontend.client.view.presenter.LoginViewPresenter;

import java.util.Collection;
import java.util.List;

public class LoginView extends Composite implements LoginViewPresenter.Display {
    private static LoginViewUiBinder uiBinder = GWT.create(LoginViewUiBinder.class);

    @UiTemplate("loginView.ui.xml")
    interface LoginViewUiBinder extends UiBinder<HTMLPanel, LoginView> {
    }

    private final Messages messages;
    private final RemoteMessages remoteMessages;

    @UiField
    DeckPanel bannerPanel;

    public LoginView() {
        initWidget(uiBinder.createAndBindUi(this));
        messages = GWT.create(Messages.class);
        remoteMessages = new RemoteMessages();

        populateBanner();
    }

    private void populateBanner() {
        Collection<String> bannerMessages = remoteMessages.bannerMessages();
        
        // add messages to banner
        for(String bannerMsg : bannerMessages) {
            bannerPanel.add(HTMLHelper.asH2(bannerMsg));
        }
        bannerPanel.showWidget(0);
        // create timer that shifts messages
        Timer timer = new Timer() {
            public void run() {
                int index = bannerPanel.getVisibleWidget();
                index++;
                if (index == bannerPanel.getWidgetCount()) {
                    index = 0;
                }
                bannerPanel.showWidget(index);
            }
        };
        timer.scheduleRepeating(10000);
    }

    @Override
    public Widget asWidget() {
        return this;
    }
}







