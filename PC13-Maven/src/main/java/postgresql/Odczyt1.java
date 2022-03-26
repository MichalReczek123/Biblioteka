package postgresql;

import java.sql.*;

// JDBC - Java Database Connectivity
public class Odczyt1 {
    public static void main(String[] args) throws SQLException {
        Connection c = DriverManager.getConnection("jdbc:postgresql://localhost/hr", "kurs", "abc123");
        System.out.println("Udało się połączyć.  c = " + c);

        PreparedStatement stmt = c.prepareStatement("SELECT * FROM employees");
        ResultSet rs = stmt.executeQuery();

        //System.out.println(rs);
        while(rs.next()) {
            // pola z wynikowych rekordów można odczytywać podając nr kolumny, licząc od 1
            int id = rs.getInt(1);
            String firstName = rs.getString(2);
            String lastName = rs.getString(3);
            System.out.printf("Pracownik nr %d to %s %s\n", id, firstName, lastName);
        }
        rs.close();
        stmt.close();
        c.close();
    }
}
