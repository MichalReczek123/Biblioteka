package emps.funkcyjnie;

import javax.swing.*;
import java.util.List;
import java.util.OptionalDouble;

public class F05_SredniaJedenJob {
    public static void main(String[] args) {
        List<Employee> employees = ObslugaCSV.wczytaj("employees.csv");

        String job = JOptionPane.showInputDialog("Podaj nazwę stanowiska, np. Programmer");

        // TODO oblicz średnią pensję na wskazanym stanowisku (jobTitle)
        OptionalDouble avg = employees.stream()
                .filter(emp -> emp.getJobTitle().equals(job))
                .mapToInt(Employee::getSalary)
                .average();

        System.out.println(avg);

        // Prawidłowa obsługa obiektu Optional powinna obejmować sytuację braku wyniku.
        // Pierwszy sposób: sprawdzamy to za pomocą if-a
        if(avg.isPresent()) {
            System.out.println("Średnia pensja: " + avg.getAsDouble());
        } else {
            System.out.println("Nie ma pracowników na takim stanowisku");
        }

        // Drugi sposób: podać wartość domyślną, np. 0
        System.out.println("Wynik: " + avg.orElse(0.0));
    }
}
