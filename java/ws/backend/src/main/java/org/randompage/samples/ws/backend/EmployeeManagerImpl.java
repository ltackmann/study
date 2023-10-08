package org.randompage.samples.ws.backend;

import java.util.HashMap;
import java.util.Map;

import org.randompage.samples.backend.domain.Employee;
import org.springframework.stereotype.Repository;

/**
 * @author Lars Tackmann
 */
@Repository
public class EmployeeManagerImpl implements EmployeeManager {
	private static Map<String,Employee> emplyees = new HashMap<String,Employee>();
	static {
		Employee employee = new Employee("ltackmann");
		emplyees.put(employee.getUsername(), employee);
	}
	
	public Employee getEmployee(String username) {
		return emplyees.get(username);
	}
}
