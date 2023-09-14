<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Gugudan1</title>
</head>
<body>
	<ul>
		<%
		int n = 2;
		String dan = (String)request.getParameter("dan");
		System.out.println("dan : " + dan);
		if (dan != null) 
		n = Integer.parseInt(dan);
		for (int i = 1; i <= 9; i++) {
		%>
		<li><%=n%> * <%=i%> = <%=(n * i)%></li>
		<%
		}
		%>
		<br/>
	</ul>
</body>
</html>