package org.randompage.samples.ws.backend;

import org.randompage.samples.backend.domain.Employee;

/**
 * @author Lars Tackmann
 */
public interface EmployeeManager {
	Employee getEmployee(String username);
}
