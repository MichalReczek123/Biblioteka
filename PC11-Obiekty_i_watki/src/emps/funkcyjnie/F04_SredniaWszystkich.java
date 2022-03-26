package emps.funkcyjnie;

import java.util.List;
import java.util.OptionalDouble;

public class F04_SredniaWszystkich {
    public static void main(String[] args) {
        List<Employee> employees = ObslugaCSV.wczytaj("employees.csv");

        OptionalDouble avg = employees.stream()
                .mapToInt(Employee::getSalary)
                .average();

        System.out.println("średnia pensja: " + avg.getAsDouble());
    }
}
