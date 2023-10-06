package fileupload;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/13FileUpload/TestProcess.do")
@MultipartConfig(maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 10)
public class TestProcess extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			List<String> result = new ArrayList<>();
			Collection<Part> parts = req.getParts();
			for (Part part : parts) {
				result.add("part.getName():[" + part.getName() + "]");
				result.add("part.getContentType():[" + part.getContentType() + "]");
				Collection<String> headers = part.getHeaderNames();
				for (String h : headers)
					result.add(String.format("part.getHeader(" + h + "):[" + part.getHeader(h) + "]"));
				result.add("-".repeat(40));
			}
			req.setAttribute("result", result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		req.getRequestDispatcher("MultiPartTest.jsp").forward(req, resp);
	}
}
