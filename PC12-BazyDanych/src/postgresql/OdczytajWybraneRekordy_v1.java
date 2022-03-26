package postgresql;

import javax.swing.*;
import java.math.BigDecimal;
import java.sql.*;
import java.util.Objects;

// wersja 1 - mało wydajna, bo program ściąga dane całej tabeli i dopiero potem sprawdza if-em
public class OdczytajWybraneRekordy_v1 {
    public static void main(String[] args) {
        String szukanyJob = JOptionPane.showInputDialog("Podaj kod stanowiska, np. IT_PROG");
        // TODO wypisz tylko tych pracowników, którzy mają job_id równy podanej wartości

        try(Connection c = DriverManager.getConnection("jdbc:postgresql://localhost/hr", "kurs", "abc123")) {
            try (PreparedStatement stmt = c.prepareStatement("SELECT * FROM employees")) {
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        int id = rs.getInt("employee_id");
                        String firstName = rs.getString("first_name");
                        String lastName = rs.getString("last_name");
                        String jobId = rs.getString("job_id");
                        BigDecimal salary = rs.getBigDecimal("salary");
                        if(Objects.equals(jobId, szukanyJob)) {
                            System.out.printf("%d: %s %s (%s) zarabia %8.2f\n", id, firstName, lastName, jobId, salary);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
