package gwtDemo.shared.validators;

public interface Validator<V> {
    /**
     * Validate value
     *
     * @param value
     * @return True if value is valid else false
     */
    boolean validate(V value);
}
