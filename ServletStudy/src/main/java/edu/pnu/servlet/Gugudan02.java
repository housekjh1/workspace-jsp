package edu.pnu.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/gugudan02")
public class Gugudan02 extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		System.out.println("Gugudan02");
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out = res.getWriter();

		String dir = req.getParameter("dir");
		if (dir == null) {
			dir = "ver";
		}
		
		if (dir.equals("ver")) {
			for (int i = 2; i <= 9; i++) {
				out.println("<ul>");
				for (int j = 1; j <= 9; j++) {
					out.println("<li>" + i + " * " + j + " = " + (i * j) + "</li>");
				}
				out.println("</ul><br>");
			}
		}

		if (dir.equals("hor")) {
			out.print("<table border='1'>");
			for (int n = 1; n <= 9; n++) {
				out.print("<tr>");
				for (int m = 2; m <= 9; m++) {
					out.print("<td>" + m + " * " + n + " = " + (m * n) + "</td>");
				}
				out.print("</tr>");
			}
			out.print("</table>");
		}
		out.close();
	}
}
