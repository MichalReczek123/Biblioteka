package techniczne;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import beans.InfoBean;

@WebFilter("/zakresy_filtr.jsp")
public class FiltrZwiekszajacy implements Filter {
	public void init(FilterConfig fConfig) throws ServletException {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("filtr - początek");
		InfoBean obiekt = new InfoBean();
		obiekt.setLicznik(100);
		request.setAttribute("req", obiekt);
		
		// pass the request along the filter chain
		chain.doFilter(request, response);

		// sterowanie wraca do filtru już po wysłaniu treści z jsp (w tym przypadku)
		System.out.println("licznik = " + obiekt.getLicznik());
	}


}
