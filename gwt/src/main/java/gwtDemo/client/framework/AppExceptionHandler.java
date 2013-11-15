package gwtDemo.client.framework;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;

public class AppExceptionHandler implements GWT.UncaughtExceptionHandler {
	@Override
	public void onUncaughtException(Throwable e) {
		Window.alert("caught it!");
	}
}
