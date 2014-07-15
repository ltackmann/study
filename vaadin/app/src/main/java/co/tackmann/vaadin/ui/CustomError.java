package co.tackmann.vaadin.ui;

import com.vaadin.server.ErrorMessage;

@SuppressWarnings("serial")
public class CustomError implements ErrorMessage {
	private final String errorMessage;
	
	public CustomError(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	@Override
	public ErrorLevel getErrorLevel() {
		return ErrorLevel.INFORMATION;
	}

	@Override
	public String getFormattedHtmlMessage() {
		return  "<p>" + errorMessage + "</p>";
	}
	
}