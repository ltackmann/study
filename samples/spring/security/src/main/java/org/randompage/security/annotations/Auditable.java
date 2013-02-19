package org.randompage.security.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.randompage.security.ArgumentAuditor;
import org.randompage.security.ResultAuditor;

/**
 * Mark a class or a method for auditing
 * 
 * @author Lars Tackmann
 */
@Retention(RetentionPolicy.RUNTIME)
@Target( { ElementType.METHOD, ElementType.TYPE })
public @interface Auditable {
	/**
	 * The class to use as the result auditor. If not specified, then no
	 * auditing will be performed. If this attribute is specified, the specified
	 * class must implement a ResultAuditor.
	 * 
	 * @see org.randompage.security.ResultAuditor
	 */
	Class<? extends ResultAuditor> resultAuditor() default ResultAuditor.class;

	Class<? extends ArgumentAuditor> argumentAuditor() default ArgumentAuditor.class;

	/**
	 * List of roles allowed to invoke this method
	 */
	String[] rolesAllowed() default {};

	/**
	 * If true then any authenticated role can invoke this method
	 */
	boolean permitAll() default false;
}
