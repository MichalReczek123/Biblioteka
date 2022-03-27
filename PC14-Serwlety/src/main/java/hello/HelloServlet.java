package hello;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/HelloServlet")
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// request - informacje o zapytaniu (nagłówki, adresy itp.)
		// response - obiekt, za pomocą którego mamy przygotować odpowiedź
		
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.println("Witaj kliencie, tu serwlet.");
		System.out.println("To pojawi się w konsoli serwera.");
		out.println("Czas: " + LocalTime.now());
	}

}
