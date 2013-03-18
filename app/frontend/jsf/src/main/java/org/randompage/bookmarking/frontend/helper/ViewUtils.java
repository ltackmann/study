package org.randompage.bookmarking.frontend.helper;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.NoneScoped;
import javax.faces.context.FacesContext;

/**
 * Helper utilities for handling views
 *
 * @author Lars Tackmann
 */
@ManagedBean
@NoneScoped
public class ViewUtils {
    /**
     * Add global faces message (not associated with any component)
     *
     * @param messageId Id of message in message bundle
     * @param severity  Severity of message
     */
    public void addGlobalMessage(String messageId, Severity severity) {
        addMessage(messageId, severity, null);
    }

    /**
     * Add faces message to component
     *
     * @param messageId   Id of message in message bundle
     * @param severity    Severity of message
     * @param componentId Id of component who should own message
     */
    public void addMessage(String messageId, Severity severity,
                           String componentId) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        // look up message in resource bundle
        Locale locale = facesContext.getViewRoot().getLocale();
        Application application = facesContext.getApplication();
        String bundleName = application.getMessageBundle();
        ResourceBundle bundle = ResourceBundle.getBundle(bundleName, locale);
        // add internationalized message to faces
        FacesMessage message = new FacesMessage(bundle.getString(messageId));
        message.setSeverity(severity);
        facesContext.addMessage(componentId, message);
	}
}
