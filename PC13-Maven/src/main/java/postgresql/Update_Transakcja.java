package postgresql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Update_Transakcja {

	public static void main(String[] args) {
		try(Connection c = DriverManager.getConnection("jdbc:postgresql://localhost/hr", "kurs", "abc123")) {
			c.setAutoCommit(false);
			
			String job = JOptionPane.showInputDialog("Podaj kod stanowiska:");
			int podwyzka = Integer.parseInt(JOptionPane.showInputDialog("Podaj kwotę podwyżki:"));
			
			try(PreparedStatement stmt = c.prepareStatement("UPDATE employees SET salary = salary + ? WHERE job_id = ?")) {
				stmt.setInt(1, podwyzka);
				stmt.setString(2, job);
				int ile = stmt.executeUpdate();
				JOptionPane.showMessageDialog(null, "Zmodyfikowano " + ile + " rekordów");
			}
			
			int wybor = JOptionPane.showConfirmDialog(null, "Czy zapisać zmiany?");
			if(wybor == JOptionPane.YES_OPTION) {
				c.commit(); // zatwierdzenie transakcji - zmiany zapisane trwale
			} else {
				c.rollback(); // wycofanie zmian - wracamy do stanu z czasu rozpoczęcia transakcji
				// tutaj gdybyśmy nie wykonali rollback , to zmiany i tak by się nie zapisały
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
