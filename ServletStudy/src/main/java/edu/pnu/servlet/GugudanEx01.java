package edu.pnu.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/gugudanex01")
public class GugudanEx01 extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		System.out.println("GugudanEx01");
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out = res.getWriter();
		
		String dan = req.getParameter("dan");
		if (dan == null) {
			dan = "2";
		}
		int num = Integer.parseInt(dan);
		
		for (int i = 2; i <= 9; i += num) {
			out.print("<table border='1'>");
			for (int j = 1; j <= 9; j++) {
				out.print("<tr>");
				for (int k = 0; k < num; k++) {
					if ((i + k) >= 10) break;
					out.print("<td>" + (i + k) + " * " + j + " = " + ((i + k) * j) + "</td>");
				}
				out.print("</tr>");
			}
			out.print("</table>");
		}
		out.close();
	}
}
