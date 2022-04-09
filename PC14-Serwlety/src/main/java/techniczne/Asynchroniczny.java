package techniczne;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Ello
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/Async" })
public class Asynchroniczny extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Asynchroniczny() {
    	System.out.println("Ello konstr");
    }
    
    @Override
    public void init() {
    	System.out.println("Ello init");
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");
		
		final AsyncContext asyncContext = request.startAsync();
		System.out.println("Puszczone");
		
		asyncContext.start(new Runnable() {
			public void run() {
				try {
					PrintWriter out = asyncContext.getResponse().getWriter();
					Thread.sleep(1000);
					out.println("Jeden");
					out.flush();
					Thread.sleep(3000);
					out.println("Dwa");
					out.flush();
					Thread.sleep(3000);
					out.println("Trzy");
					Thread.sleep(1000);
					asyncContext.complete();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
