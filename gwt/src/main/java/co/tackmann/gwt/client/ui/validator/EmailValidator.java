package co.tackmann.gwt.client.ui.validator;

/**
 * @author Lars Tackmann
 */
public class EmailValidator implements Validator<String> {
    @Override
    public boolean validate(String email) {
        return (email != null && email.matches(".+@.+\\.[a-z]+"));
    }
}
