package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet(
		urlPatterns = {"/JSPMission/freefall.do"}
		)
public class Freefall extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		double result = 0;
		double a = 9.81;
		int t = Integer.parseInt(req.getParameter("time"));
		double v0 = 0;
		double x0 = Double.parseDouble(req.getParameter("position"));

		result = (x0 - (0.5*a*t*t) + (v0*t));
	
		req.setAttribute("result", result);
		req.getRequestDispatcher("/JSPMission/freefall.jsp").forward(req, resp);
	}
	
	@Override
	public void destroy() {
		super.destroy();
	}
}
