<%@page import="java.sql.ResultSetMetaData"%>
<%@page import="java.sql.Date"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="common.JDBConnect"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JDBC</title>
</head>
<body>
	<h2>회원 목록 조회 테스트(executeQuery() 사용)</h2>
	<%
	JDBConnect jdbc = new JDBConnect(application);
	Connection con = jdbc.getConnection();
	
	String sql = "select id, pass, name, regidate from member";
	Statement st = con.createStatement();
	ResultSet rs = st.executeQuery(sql);
	
	out.println("<table border='1'>");
	while (rs.next()) {
		String id = rs.getString(1);
		String pw = rs.getString(2);
		String name = rs.getString("name");
		Date regidate = rs.getDate("regidate");
		out.println("<tr>");
		out.println("<td>" + String.format("%s", id) + "</td>");
		out.println("<td>" + String.format("%s", pw) + "</td>");
		out.println("<td>" + String.format("%s", name) + "</td>");
		out.println("<td>" + String.format("%s", regidate) + "</td>");
		out.println("</tr>");
	}
	out.println("</table>");
	%>
	<h2>게시글 목록 조회 테스트(executeQuery() 사용)</h2>
	<%
	sql = "select num, title, content, id, postdate, visitcount from board";
	rs = st.executeQuery(sql);
	out.println("<table border='1'>");
	while (rs.next()) {
		String num = rs.getString(1);
		String title = rs.getString("title");
		String content = rs.getString("content");
		String id2 = rs.getString(4);
		Date postdate = rs.getDate("postdate");
		String visitcount = rs.getString("visitcount");
		out.println("<tr>");
		out.println("<td>" + String.format("%s", num) + "</td>");
		out.println("<td>" + String.format("%s", title) + "</td>");
		out.println("<td>" + String.format("%s", content) + "</td>");
		out.println("<td>" + String.format("%s", id2) + "</td>");
		out.println("<td>" + String.format("%s", postdate) + "</td>");
		out.println("<td>" + String.format("%s", visitcount) + "</td>");
		out.println("</tr>");
	}
	out.println("</table>");
	
	rs.close();	
	st.close();
	con.close();
	jdbc.close();
	%>
</body>
</html>