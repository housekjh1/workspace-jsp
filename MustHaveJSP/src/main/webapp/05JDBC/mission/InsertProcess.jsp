<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="common.JDBConnect"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	try {
	String id = request.getParameter("id");
	String pwd = request.getParameter("pwd");
	String name = request.getParameter("name");
	if (id == null || id.isEmpty() || pwd == null || pwd.isEmpty()) {
// 		response.sendRedirect("InsertFail.jsp?msg=" + "Id 또는 Password가 적쩔하지 않습니다.");
		request.setAttribute("msg", "Id 또는 Password가 적절하지 않습니다.");
		request.getRequestDispatcher("InsertFail.jsp").forward(request, response);
		return;
	}
	
	JDBConnect jdbc = new JDBConnect(application);
	Connection con = jdbc.getConnection();
	Statement st = con.createStatement();
	
	String sql = "insert into member (id, pass, name) values ('" + id + "', '" + pwd + "', '" + name + "')";
	int c = st.executeUpdate(sql);
	
	request.setAttribute("msg", c + "행이 입력되었습니다.");
	request.getRequestDispatcher("InsertSuccess.jsp").forward(request, response);
	
	st.close();
	con.close();
	jdbc.close();
	} catch (Exception e) {
		request.setAttribute("msg", e.toString());
		request.getRequestDispatcher("InsertFail.jsp").forward(request, response);
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>