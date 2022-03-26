package emps.obiektowo;

import java.util.List;
import java.util.Locale;

public class P04_SredniaWszystkich {
    public static void main(String[] args) {
        List<Employee> employees = ObslugaCSV.wczytaj("employees.csv");
        int suma = 0;
        for (Employee emp : employees) {
            suma += emp.getSalary();
        }

        double srednia = 1.0 * suma / employees.size();
        System.out.println("średnia pensja: " + srednia);
        System.out.printf("średnia pensja: %s\n", srednia);
        System.out.printf("średnia pensja: %f\n", srednia);
        System.out.printf("średnia pensja: %.2f\n", srednia);
        System.out.printf(Locale.US, "średnia pensja: %.2f\n", srednia);
    }
}
