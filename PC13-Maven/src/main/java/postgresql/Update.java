package postgresql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Update {

	public static void main(String[] args) {
		try(Connection c = DriverManager.getConnection("jdbc:postgresql://localhost/hr", "kurs", "abc123")) {
			String job = JOptionPane.showInputDialog("Podaj kod stanowiska:");
			int podwyzka = Integer.parseInt(JOptionPane.showInputDialog("Podaj kwotę podwyżki:"));
			
			try(PreparedStatement stmt = c.prepareStatement("UPDATE employees SET salary = salary + ? WHERE job_id = ?")) {
				stmt.setInt(1, podwyzka);
				stmt.setString(2, job);
				int ile = stmt.executeUpdate();
				JOptionPane.showMessageDialog(null, "Zmodyfikowano " + ile + " rekordów");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
