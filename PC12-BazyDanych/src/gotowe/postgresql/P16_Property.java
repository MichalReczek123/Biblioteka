package gotowe.postgresql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class P16_Property {
	public static void main(String[] args) throws SQLException {
		Properties props = new Properties();
		props.setProperty("user", "hr");
		props.setProperty("password", "abc123");
		// props.setProperty("ssl", "true");
		props.setProperty("tcpKeepAlive", "true");

		String url = "jdbc:postgresql://localhost/hr";
		
		Connection c = DriverManager.getConnection(url, props);
		
		Statement stmt = c.createStatement();
		// Statementy można trochę skonfigurować
		stmt.setFetchSize(10);
		
		ResultSet rs = stmt.executeQuery("SELECT SUM(salary) FROM employees");
		while (rs.next()) {
			System.out.print("Wynik: ");
			System.out.println(rs.getString(1));
		}
		rs.close();
		stmt.close();
		c.close();
	}
}
