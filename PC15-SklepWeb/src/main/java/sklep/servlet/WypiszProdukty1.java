package sklep.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/WypiszProdukty1")
public class WypiszProdukty1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.println("Początek...");
		
		String url = "jdbc:postgresql://localhost/sklep";
		String sql = "SELECT * FROM products ORDER BY product_id";
		try(Connection c = DriverManager.getConnection(url, "kurs", "abc123");
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(sql)) {
			
			while(rs.next()) {
				out.printf("Produkt nr %d: %s za %s\n", rs.getInt("product_id"),
						rs.getString("product_name"), rs.getBigDecimal("price"));
			}
		} catch (SQLException e) {
			e.printStackTrace(out);
		}	
	}

}


