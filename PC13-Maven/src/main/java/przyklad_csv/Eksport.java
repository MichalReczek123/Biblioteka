package przyklad_csv;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

public class Eksport {

	public static void main(String[] args) {
		final String sql = "SELECT employee_id, first_name, last_name, job_title,"
				+ " lower(email) || '@alx.pl' AS email, replace(phone_number, '.', '') AS tel,"
				+ " salary, hire_date, department_name, street_address AS address, postal_code, city, country_name AS country"
				+ " FROM employees"
				+ " LEFT JOIN jobs USING(job_id)"
				+ " LEFT JOIN departments USING(department_id)"
				+ " LEFT JOIN locations USING(location_id)"
				+ " LEFT JOIN countries USING(country_id)"
				+ " ORDER BY employee_id";

		try(Connection c = DriverManager.getConnection("jdbc:postgresql://localhost/hr", "kurs", "abc123")) {
			try (PreparedStatement stmt = c.prepareStatement(sql)) {
                try (ResultSet rs = stmt.executeQuery()) {
                	zapiszCSV(rs, "eksport.csv");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

	private static void zapiszCSV(ResultSet rs, String plik) throws SQLException {
		CSVFormat format = CSVFormat.Builder.create()
				.setDelimiter(';')
				.setHeader(rs)
				.setQuote('"')
				.build();
				
		try(PrintWriter out = new PrintWriter(plik);
			CSVPrinter printer = new CSVPrinter(out, format)) {
			
			printer.printRecords(rs);
			System.out.println("Gotowe");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}












