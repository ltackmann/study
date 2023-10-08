package gwtDemo.shared.validators;

public class PasswordValidator implements Validator<String> {
	@Override
	public boolean validate(String value) {
		return (value != null) && value.trim().length() > 5;
	}
}
