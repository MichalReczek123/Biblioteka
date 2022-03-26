package emps.obiektowo;

import java.util.List;

public class P01_Odczyt {
    public static void main(String[] args) {
        List<Employee> employees = ObslugaCSV.wczytaj("employees.csv");
        System.out.println("Odczytano " + employees.size() + " rekordów:");
        for (Employee emp : employees) {
            System.out.println(emp);
        }
    }
}
