package techniczne;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Można spróbować z takimi parametrami:
// http://localhost:8080/PC24-Serwlety/Info?x=Ala&y=Ola&x=Ela&x=Ula&z=Asia

@WebServlet("/Info")   // mogłby być też wpis w web.xml
public class Info extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String teraz = LocalTime.now().toString();

		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		response.addCookie(new Cookie("czas", teraz));

		PrintWriter out = response.getWriter();
		out.println("Halo halo");

		out.println("ContextPath: " + request.getContextPath());
		out.println("RequestURI: " + request.getRequestURI());
		out.println("QueryString: " + request.getQueryString());

		out.println();
		out.println("LocalName: " + request.getLocalName());
		out.println("LocalAddr: " + request.getLocalAddr());
		out.println("LocalPort: " + request.getLocalPort());
		out.println();
		out.println("RemoteHost: " + request.getRemoteHost());
		out.println("RemoteAddr: " + request.getRemoteAddr());
		// itd.

		out.println();
		out.println("parametr x: " + request.getParameter("x"));
		out.println("parametr y: " + request.getParameter("y"));
		
		String[] wartosciX = request.getParameterValues("x");
		out.println("x jako tablica: " + Arrays.toString(wartosciX));
		
		out.println("Wszystkie parametry:");
		for (Map.Entry e : request.getParameterMap().entrySet()) {
			String[] v = (String[]) e.getValue();
			out.println(" * " + e.getKey() + " : " + Arrays.toString(v));
		}
		out.println();

		out.println("nagłówek Accept: " + request.getHeader("Accept"));
		out.println("nagłówek User-Agent: " + request.getHeader("User-Agent"));
		out.println("Wszystkie nagłówki:");
		Enumeration<String> headerNames = request.getHeaderNames();
		while(headerNames.hasMoreElements()) {
			String nm = headerNames.nextElement();
			out.println(nm + ": " + request.getHeader(nm));
		}
		out.println();

		out.println("Wszystkie ciastka:");
		String poprzedniCzas = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null)
			for (Cookie cooky : cookies) {
				out.println("cookie " + cooky.getName() + " " + cooky.getValue());
				if ("czas".equals(cooky.getName())) {
					poprzedniCzas = cooky.getValue();
				}
			}
		out.println("czas teraz    : " + teraz);
		out.println("czas poprzedni: " + poprzedniCzas);
	}
}
