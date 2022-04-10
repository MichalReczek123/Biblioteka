package dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class AdressInfo {
	private int employeeId;
	private String firstName, lastName;
	private String jobTitle;
	private BigDecimal salary;
	private LocalDate hireDate;
	private String departmentName;
	private String streetAddress;
	private String postalCode;
	private String city;
	private String country;
	public AdressInfo() {
	}
	public AdressInfo(int employeeId, String firstName, String lastName, String jobTitle, BigDecimal salary,
			LocalDate hireDate, String departmentName, String streetAddress, String postalCode, String city,
			String country) {
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.jobTitle = jobTitle;
		this.salary = salary;
		this.hireDate = hireDate;
		this.departmentName = departmentName;
		this.streetAddress = streetAddress;
		this.postalCode = postalCode;
		this.city = city;
		this.country = country;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
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
	public LocalDate getHireDate() {
		return hireDate;
	}
	public void setHireDate(LocalDate hireDate) {
		this.hireDate = hireDate;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getStreetAddress() {
		return streetAddress;
	}
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	@Override
	public String toString() {
		return "AdressInfo [employeeId=" + employeeId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", jobTitle=" + jobTitle + ", salary=" + salary + ", hireDate=" + hireDate + ", departmentName="
				+ departmentName + ", streetAddress=" + streetAddress + ", postalCode=" + postalCode + ", city=" + city
				+ ", country=" + country + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(city, country, departmentName, employeeId, firstName, hireDate, jobTitle, lastName,
				postalCode, salary, streetAddress);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AdressInfo other = (AdressInfo) obj;
		return Objects.equals(city, other.city) && Objects.equals(country, other.country)
				&& Objects.equals(departmentName, other.departmentName) && employeeId == other.employeeId
				&& Objects.equals(firstName, other.firstName) && Objects.equals(hireDate, other.hireDate)
				&& Objects.equals(jobTitle, other.jobTitle) && Objects.equals(lastName, other.lastName)
				&& Objects.equals(postalCode, other.postalCode) && Objects.equals(salary, other.salary)
				&& Objects.equals(streetAddress, other.streetAddress);
	}
}
