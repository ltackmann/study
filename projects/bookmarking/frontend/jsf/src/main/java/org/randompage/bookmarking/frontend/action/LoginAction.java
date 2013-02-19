package org.randompage.bookmarking.frontend.action;

import org.apache.commons.codec.digest.DigestUtils;
import org.randompage.bookmarking.api.UserManager;
import org.randompage.bookmarking.api.domain.User;
import org.randompage.bookmarking.frontend.helper.ViewUtils;
import org.randompage.bookmarking.frontend.model.Identity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import java.util.Date;
import java.util.Map;


@ManagedBean
@RequestScoped
public class LoginAction {
    final Logger logger = LoggerFactory.getLogger(LoginAction.class);

    // managed properties
    @ManagedProperty(value = "#{identity}")
    private Identity identity;
    @ManagedProperty(value = "#{sessionScope}")
    private Map<String, Object> sessionMap;
    @ManagedProperty(value = "#{userManager}")
    private UserManager userManager;
    @ManagedProperty(value = "#{viewUtils}")
    private ViewUtils viewUtils;

    public String login() {
        final String email = identity.getEmail();
        final String pwdMD5 = DigestUtils.md5Hex(identity.getPassword());
        User user = userManager.getUser(email);
        if (user == null || !(user.getPassword().equals(pwdMD5))) {
            logger.info("failed to log in as user: {}", email);
            viewUtils.addGlobalMessage("loginError", FacesMessage.SEVERITY_FATAL);
            return null;
        }

        // bind user to session
        sessionMap.put("user", user);
        // update users login status
        user.setLastLogin(new Date());
        userManager.modifyUser(user);

        logger.info("logged in as user: " + email);
        return "login";
    }

    public void setIdentity(Identity identity) {
        this.identity = identity;
    }

    public void setSessionMap(Map<String, Object> sessionMap) {
        this.sessionMap = sessionMap;
    }

    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }

    public void setViewUtils(ViewUtils viewUtils) {
        this.viewUtils = viewUtils;
    }
}
