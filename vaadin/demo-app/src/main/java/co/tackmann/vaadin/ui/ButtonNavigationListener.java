package co.tackmann.vaadin.ui;


import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.navigator.Navigator;

public class ButtonNavigationListener implements Button.ClickListener {
    private static final long serialVersionUID = -4941184695301907995L;
    private final Navigator navigator;
    private final String targetUrl;
    
    public ButtonNavigationListener(Navigator navigator, String viewUrl, String subViewUrl) {
    	String url = viewUrl;
    	if(subViewUrl != null) {
    		url += ("/" + subViewUrl);
    	}
    	System.out.println("------------ URL IS " + url);
        this.targetUrl = url;
        this.navigator = navigator;
    }

	@Override
	public void buttonClick(ClickEvent event) {
		// Navigate to a specific state
        navigator.navigateTo(targetUrl);
	}
}