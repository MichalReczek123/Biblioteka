package dto;

import java.math.BigDecimal;
import java.util.Locale;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PracownikWDepartamencie {
	@Id
	private int id;
	private String departmentName, firstName, lastName, jobTitle;
	private BigDecimal salary, depAvg;
	private int depPos, globalPos;
	
	public PracownikWDepartamencie() {
	}

	public PracownikWDepartamencie(int id, String departmentName, String firstName, String lastName, String jobTitle,
			BigDecimal salary, BigDecimal depAvg, int depPos, int globalPos) {
		this.id = id;
		this.departmentName = departmentName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.jobTitle = jobTitle;
		this.salary = salary;
		this.depAvg = depAvg;
		this.depPos = depPos;
		this.globalPos = globalPos;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
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

	public BigDecimal getDepAvg() {
		return depAvg;
	}

	public void setDepAvg(BigDecimal depAvg) {
		this.depAvg = depAvg;
	}

	public int getDepPos() {
		return depPos;
	}

	public void setDepPos(int depPos) {
		this.depPos = depPos;
	}

	public int getGlobalPos() {
		return globalPos;
	}

	public void setGlobalPos(int globalPos) {
		this.globalPos = globalPos;
	}

	@Override
	public String toString() {
		return "PracownikWDepartamencie [id=" + id + ", departmentName=" + departmentName + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", jobTitle=" + jobTitle + ", salary=" + salary + ", depAvg=" + depAvg
				+ ", depPos=" + depPos + ", globalPos=" + globalPos + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(depAvg, depPos, departmentName, firstName, globalPos, id, jobTitle, lastName, salary);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PracownikWDepartamencie other = (PracownikWDepartamencie) obj;
		return Objects.equals(depAvg, other.depAvg) && depPos == other.depPos
				&& Objects.equals(departmentName, other.departmentName) && Objects.equals(firstName, other.firstName)
				&& globalPos == other.globalPos && id == other.id && Objects.equals(jobTitle, other.jobTitle)
				&& Objects.equals(lastName, other.lastName) && Objects.equals(salary, other.salary);
	}

	public String dajInfo() {
		return String.format(Locale.US, "%3d | %-12s %-12s %-33s | %8.2f | %-20s | %8.2f | %2d | %3d |",
				id, firstName, lastName, "("+jobTitle+")", salary, departmentName, depAvg, depPos, globalPos);
	}
	
	public static String naglowek() {
		return String.format(Locale.US, " id | pracownik                                                   |   pensja | departament          | średnia  | nr | nr g|");
	}
	
}


