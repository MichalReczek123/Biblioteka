package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the jobs database table.
 * 
 */
@Entity
@Table(name="jobs")
@NamedQuery(name="Job.findAll", query="SELECT j FROM Job j")
public class Job implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="job_id")
	private String jobId;

	@Column(name="job_title")
	private String jobTitle;

	@Column(name="max_salary")
	private BigDecimal maxSalary;

	@Column(name="min_salary")
	private BigDecimal minSalary;

	//bi-directional many-to-one association to Employee
	@OneToMany(mappedBy="job")
	private List<Employee> employees;

	public Job() {
	}

	public String getJobId() {
		return this.jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getJobTitle() {
		return this.jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public BigDecimal getMaxSalary() {
		return this.maxSalary;
	}

	public void setMaxSalary(BigDecimal maxSalary) {
		this.maxSalary = maxSalary;
	}

	public BigDecimal getMinSalary() {
		return this.minSalary;
	}

	public void setMinSalary(BigDecimal minSalary) {
		this.minSalary = minSalary;
	}

	public List<Employee> getEmployees() {
		return this.employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public Employee addEmployee(Employee employee) {
		getEmployees().add(employee);
		employee.setJob(this);

		return employee;
	}

	public Employee removeEmployee(Employee employee) {
		getEmployees().remove(employee);
		employee.setJob(null);

		return employee;
	}

}