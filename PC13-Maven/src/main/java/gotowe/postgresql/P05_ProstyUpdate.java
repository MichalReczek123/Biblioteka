package gotowe.postgresql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class P05_ProstyUpdate {
	
	public static void podwyzka(int zmiana, int min, int max) {
		String sql = "UPDATE employees SET salary = salary + ? "
				+ "WHERE salary BETWEEN ? AND ?" ;
		
		try(Connection c = DriverManager.getConnection(Ustawienia.URL, Ustawienia.USER, Ustawienia.PASSWD);
			PreparedStatement stmt = c.prepareStatement(sql)) {
			
			stmt.setInt(1, zmiana);
			stmt.setInt(2, min);
			stmt.setInt(3, max);
			
			int ile = stmt.executeUpdate(); // uwaga, tego używamy także dla insert czy delete
			// Dokładnie mówiąc: ilu wierszy dotyczyło zapytanie.
			System.out.println("Zaktualizowano " + ile + " wierszy");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		podwyzka(333, 10000, 20000);
	}
}
