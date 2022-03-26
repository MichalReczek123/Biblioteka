package emps.funkcyjnie;

import java.util.List;

public class F02_WypiszWybranePola {
    public static void main(String[] args) {
        List<Employee> employees = ObslugaCSV.wczytaj("employees.csv");
        employees.forEach(emp -> System.out.printf("%s %s zarabia %d\n", emp.getFirstName(), emp.getLastName(), emp.getSalary()));
    }
}
