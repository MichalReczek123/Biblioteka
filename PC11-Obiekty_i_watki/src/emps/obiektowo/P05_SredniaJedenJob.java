package emps.obiektowo;

import javax.swing.*;
import java.util.List;
import java.util.Locale;

public class P05_SredniaJedenJob {
    public static void main(String[] args) {
        List<Employee> employees = ObslugaCSV.wczytaj("employees.csv");
        String job = JOptionPane.showInputDialog("Podaj nazwę stanowiska, np. Programmer");

        double suma = 0;
        int ilosc = 0;
        for (Employee emp : employees) {
            suma += emp.getSalary();
            ilosc++;
        }

        double srednia = suma / ilosc;
        JOptionPane.showMessageDialog(null, "Średnia = " + srednia);
    }
}
