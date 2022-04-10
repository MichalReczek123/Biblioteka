package dto;

import java.math.BigDecimal;
import java.util.Objects;

public class MiniEmp {
	private String firstName, lastName;
	private String jobTitle;
	private BigDecimal salary;
	
	public MiniEmp() {
	}

	public MiniEmp(String firstName, String lastName, String jobTitle, BigDecimal salary) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.jobTitle = jobTitle;
		this.salary = salary;
		System.out.println("salary YES");
	}

	public MiniEmp(String firstName, String lastName, String jobTitle) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.jobTitle = jobTitle;
		System.out.println("salary NO");
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "MiniEmp [firstName=" + firstName + ", lastName=" + lastName
				+ ", jobTitle=" + jobTitle + ", salary=" + salary + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(firstName, jobTitle, lastName, salary);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MiniEmp other = (MiniEmp) obj;
		return Objects.equals(firstName, other.firstName)
				&& Objects.equals(jobTitle, other.jobTitle) && Objects.equals(lastName, other.lastName)
				&& Objects.equals(salary, other.salary);
	}
}
