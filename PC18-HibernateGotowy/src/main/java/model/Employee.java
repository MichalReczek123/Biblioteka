package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the employees database table.
 * 
 */
@Entity
@Table(name="employees")
@NamedQuery(name="Employee.findAll", query="SELECT e FROM Employee e")
public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="EMPLOYEES_EMPLOYEEID_GENERATOR", sequenceName="EMPLOYEES_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EMPLOYEES_EMPLOYEEID_GENERATOR")
	@Column(name="employee_id")
	private Integer employeeId;

	@Column(name="commission_pct")
	private BigDecimal commissionPct;

	private String email;

	@Column(name="first_name")
	private String firstName;

	@Temporal(TemporalType.DATE)
	@Column(name="hire_date")
	private Date hireDate;

	@Column(name="last_name")
	private String lastName;

	@Column(name="phone_number")
	private String phoneNumber;

	private BigDecimal salary;

	//bi-directional many-to-one association to Department
	@ManyToOne
	@JoinColumn(name="department_id")
	private Department department;

	//uni-directional many-to-one association to Employee
	@ManyToOne
	@JoinColumn(name="manager_id")
	private Employee manager;

	//bi-directional many-to-one association to Job
	@ManyToOne
	@JoinColumn(name="job_id")
	private Job job;

	public Employee() {
	}

	public Integer getEmployeeId() {
		return this.employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public BigDecimal getCommissionPct() {
		return this.commissionPct;
	}

	public void setCommissionPct(BigDecimal commissionPct) {
		this.commissionPct = commissionPct;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Date getHireDate() {
		return this.hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public BigDecimal getSalary() {
		return this.salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Employee getManager() {
		return this.manager;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}

	public Job getJob() {
		return this.job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

}