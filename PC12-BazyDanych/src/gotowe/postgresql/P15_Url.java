package gotowe.postgresql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class P15_Url {
	public static void main(String[] args) throws SQLException {
		String url = "jdbc:postgresql://localhost/hr?user=kurs&password=abc123&tcpKeepAlive=true";
		String name = "Steven";
		String last = "King";
		Connection c = DriverManager.getConnection(url);
		c.setAutoCommit(false);
		PreparedStatement stmt = c.prepareStatement("SELECT first_name,last_name FROM EMPLOYEES "
											+ "WHERE first_name = ?");
		stmt.setString(1, name);
		stmt.setFetchSize(10);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			System.out.print("Wynik: ");
			System.out.print(rs.getString(1) + " ");
			System.out.println(rs.getString(2));
		}
		stmt.setFetchSize(0);
		rs.close();
		stmt.close();
	}
}
