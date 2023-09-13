package edu.pnu.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/binary01")
public class Binary01 extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
		System.out.println("Binary01");
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out = res.getWriter();
		
		String num = req.getParameter("num");
		if (num == null) {
			num = "2";
		}
		int n = Integer.parseInt(num);
		String binaryString = Integer.toBinaryString(n);
		out.print("입력 : " + n + " -> " + binaryString);
		out.close();
	}
}
