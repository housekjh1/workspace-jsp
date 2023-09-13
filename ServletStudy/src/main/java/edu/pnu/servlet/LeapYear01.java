package edu.pnu.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/leapyear01")
public class LeapYear01 extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		System.out.println("LeapYear01");
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out = res.getWriter();
		String year = req.getParameter("year");

		if (year == null) {
			year = "2023";
		}
		int y = Integer.parseInt(year);

		if ((y % 4) == 0) {// 윤년

			if ((y % 100) == 0) {// 평년

				if ((y % 400) == 0) {// 윤년

					out.print(y + "년은 윤년입니다.");

				} else {// 평년

					out.print(y + "년은 평년입니다.");
				}
			} else {// 윤년
				out.print(y + "년은 윤년입니다.");
			}

		} else {// 평년

			out.print(y + "년은 평년입니다.");
		}
		out.close();
	}
}
