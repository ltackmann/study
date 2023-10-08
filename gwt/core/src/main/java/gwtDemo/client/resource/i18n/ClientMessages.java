package gwtDemo.client.resource.i18n;

import com.google.gwt.i18n.client.Messages;

/**
 * GWT client access to the i18n messages (the server can use a normal 
 * resource bundle to access the same messages).
 *
 * @author Lars Tackmann
 */
public interface ClientMessages extends Messages {
    String loginError();

    String systemError();

    String loginButton();

    String signupButton();

    String account(String username);

    String bannerText1();

    String bannerText2();

    String topicsTitle();

    String link(String url, String description);

    String h2(String text);

    String loginTitle();

    String loginEmail();

    String loginPassword();

    String signupTitle();

    String signupName();

    String signupEmail();

    String signupPassword();

    String signupConfirm();

    String signupErrorNameMissing();

    String signupErrorInvalidEmail();

    String signupErrorInvalidPassword();

    String signupErrorPasswordMismatch();

    String signupErrorEmailTaken(String email);
}
