package jpa.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue("1")
@SecondaryTable(name = "EMPLOYEE_DETAILS", pkJoinColumns = @PrimaryKeyJoinColumn(name = "EMP_ID"))
public class Employee extends User {
	private static final long serialVersionUID = 4750283475949416432L;

    @Column(name = "PHONE_NUMBER")
	private String phoneNumber;

	@Column(table = "EMPLOYEE_DETAILS", name="MIN_BUDGET", nullable = false)
	private BigDecimal minimumBudget;
	
	@Column(table = "EMPLOYEE_DETAILS", nullable = false)
	private BigDecimal salary;
	
	@Enumerated(EnumType.STRING)
	@Column(table = "EMPLOYEE_DETAILS", name="EMP_TYPE", nullable = false)
	private EmployeeType employeeType;
	
	@Column(table = "EMPLOYEE_DETAILS", name="ACCOUNT", nullable = false)
	private String accountNumber;
	
	@ManyToOne
	@JoinColumn(table = "EMPLOYEE_DETAILS", name="COUNTRIES_ID", nullable = false)
	private Country country;

	@OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
	private Set<Order> orders = new HashSet<Order>();

    public String getAccountNumber() {
		return accountNumber;
	}

	public EmployeeType getEmployeeType() {
		return employeeType;
	}

	public BigDecimal getMinimumBudget() {
		return minimumBudget;
	}

	/**
	 * @return the orders
	 */
	public Set<Order> getOrders() {
		return orders;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public void setEmployeeType(EmployeeType employeeType) {
		this.employeeType = employeeType;
	}

	public void setMinimumBudget(BigDecimal minimumBudget) {
		this.minimumBudget = minimumBudget;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}
}