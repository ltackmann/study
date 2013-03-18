package org.randompage.bookmarking.frontend.action;

import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.randompage.bookmarking.api.UserManager;
import org.randompage.bookmarking.api.domain.Role;
import org.randompage.bookmarking.api.domain.User;
import org.randompage.bookmarking.frontend.helper.ViewUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean
@RequestScoped
public class SignupAction {
    final Logger logger = LoggerFactory.getLogger(SignupAction.class);
    private String status;

    // managed properties
    @ManagedProperty(value = "#{userManager}")
    private UserManager userManager;
    @ManagedProperty(value = "#{user}")
    private User user;
    @ManagedProperty(value = "#{viewUtils}")
    private ViewUtils viewUtils;

    @PreDestroy
    protected void preDestroy() {
        logger.info("signup request ended with status: {}", status);
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }

    public void setViewUtils(ViewUtils viewUtils) {
        this.viewUtils = viewUtils;
    }

    public String signup() {
        if (userManager.getUser(user.getEmail()) != null) {
            viewUtils.addMessage("alreadyRegistered",
                    FacesMessage.SEVERITY_FATAL, "signupForm");
            status = "user-exists";
        } else {
            user.setRole(Role.USER);
            userManager.createUser(user);
            status = "created";
        }
        return status;
    }
}
