package postgresql;

import java.math.BigDecimal;
import java.sql.*;

// JDBC - Java Database Connectivity
public class Odczyt2 {
    public static void main(String[] args) {
        try(Connection c = DriverManager.getConnection("jdbc:postgresql://localhost/hr", "kurs", "abc123")) {
            try (PreparedStatement stmt = c.prepareStatement("SELECT * FROM employees ORDER BY employee_id")) {
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        // pola z wynikowych rekordów można też odczytywać podając nazwy kolumn
                        int id = rs.getInt("employee_id");
                        String firstName = rs.getString("first_name");
                        String lastName = rs.getString("last_name");
                        String jobId = rs.getString("job_id");
                        BigDecimal salary = rs.getBigDecimal("salary");
                        System.out.printf("%d: %s %s (%s) zarabia %8.2f\n", id, firstName, lastName, jobId, salary);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
