package przyklad_csv;

import java.io.FileNotFoundException;
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
		try(Connection c = DriverManager.getConnection("jdbc:postgresql://localhost/hr", "kurs", "abc123")) {
            try (PreparedStatement stmt = c.prepareStatement("SELECT * FROM employees ORDER BY employee_id")) {
                try (ResultSet rs = stmt.executeQuery()) {
                	
                	zapiszCSV(rs, "eksport.csv");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

	private static void zapiszCSV(ResultSet rs, String plik) {
		CSVFormat format = CSVFormat.EXCEL;
		
		try(PrintWriter out = new PrintWriter(plik);
			CSVPrinter printer = new CSVPrinter(out, format)) {
			
			printer.printRecords(rs);
			System.out.println("Gotowe");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}












