package hello;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/hello.html")
public class HelloHtml extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Przykładowa stronka</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1 style='color: blue'>Witamy w aplikcaji serwletowej</h1>");
		out.println("<p>To jest zwykły tekst</p>");
		out.println("<p>Teraz jest <strong style='color:green'>" + LocalDateTime.now() + "</strong></p>");
		out.println("</body>");
		out.println("</html>");
	}

}
