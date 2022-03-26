package gotowe.postgresql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class P09a_Procedura_BrakWyniku {
	
	public static void main(String[] args) {
		try(Connection c = DriverManager.getConnection(Ustawienia.URL, Ustawienia.USER, Ustawienia.PASSWD);
			CallableStatement stmt = c.prepareCall("{call przenies_pracownika(?,?,?)}")) {
			stmt.setInt(1, 112);
			stmt.setInt(2, 60);
			stmt.setString(3, "IT_PROG");
			
			// gdyby byl parametr INOUT albo OUT
			// stmt.registerOutParameter(1, Types.VARCHAR);
			
			// a jeśli funkcja zwraca wynik (wartość skalarną, a nie tabelę), to piszemy tak:
			// "{?= call funkcja(?,?,?)}"
			
			// i zarejestrować parametr wynikowy, aby Java odebrała ten wynik
			// stmt.registerOutParameter(1, Types.VARCHAR);
			
			// teraz jakaś wersja execute
			
			stmt.executeUpdate();

			// i po execute zrobic String zmiennaWJavie = stmt.getString(1);

			// gdyby funkcja zwracała wyniuk typu tabela, to wtedy nie rejestrujemy tego wyniku jako parametru
			// tylko stosujemy ResultSet rs = stmt.executeQuery();
			
			System.out.println("Wykonane");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
