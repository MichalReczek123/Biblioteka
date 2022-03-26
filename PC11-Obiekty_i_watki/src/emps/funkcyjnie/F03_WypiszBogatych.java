package emps.funkcyjnie;

import java.util.List;

public class F03_WypiszBogatych {
    public static void main(String[] args) {
        List<Employee> employees = ObslugaCSV.wczytaj("employees.csv");

        employees.stream()
                .filter(emp -> emp.getSalary() >= 10000)
                .forEach(emp -> System.out.println(emp));
    }
}
