package emps.obiektowo;

import java.util.List;

public class P02_WypiszWybranePola {
    public static void main(String[] args) {
        List<Employee> employees = ObslugaCSV.wczytaj("employees.csv");
        System.out.println("Odczytano " + employees.size() + " rekordów:");
        for (Employee emp : employees) {
            System.out.printf("%s %s zarabia %d\n", emp.getFirstName(), emp.getLastName(), emp.getSalary());
        }
    }
}
