package emps.funkcyjnie;

import java.util.List;
import java.util.function.Predicate;

public class F03_WypiszBogatych_Alt {
    public static void main(String[] args) {
        List<Employee> employees = ObslugaCSV.wczytaj("employees.csv");

        Predicate<Employee> predykat = new CzyJestBogaty();

        employees.stream()
                .filter(predykat)
                .forEach(emp -> System.out.println(emp));
    }
}
