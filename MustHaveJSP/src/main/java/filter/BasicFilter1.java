package filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns = "/15FilterListener/BasicFilter.jsp"

)
public class BasicFilter1 implements Filter {
	FilterConfig config;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		System.out.println("before - BasicFilter1");

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String uri = req.getRequestURI();

		if (uri.equals("/MustHaveJSP/HelloJSP.jsp"))
			chain.doFilter(request, response);
		else
			resp.sendRedirect("/MustHaveJSP/HelloJSP.jsp");

		System.out.println("after - BasicFilter1");
	}
}
