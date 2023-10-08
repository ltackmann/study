package org.randompage.bookmarking.frontend.action;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

@ManagedBean
@RequestScoped
public class LogoutAction {
    // managed properties
    @ManagedProperty(value = "#{facesContext}")
    private FacesContext facesContext;

    public String logout() {
        // invalidate session
        HttpServletRequest req = (HttpServletRequest) facesContext
                .getExternalContext().getRequest();
        req.getSession().invalidate();
        return "logout";
    }

    public void setFacesContext(FacesContext facesContext) {
        this.facesContext = facesContext;
    }
}
