<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	String val = (String) request.getParameter("val");
	int n1 = Integer.parseInt((String) request.getParameter("val"));
	int n2 = Integer.parseInt((String) request.getParameter("val"));
// 	String dan = (String) request.getParameter("dan");
// 	String col = (String) request.getParameter("col");
	if (val != null)
		n1 = Integer.parseInt(val);
		n2 = Integer.parseInt(val);
	String sel = (String) request.getParameter("sel");
	if (sel.equals("type1")) {
	%>
	<ul>
		<%
		for (int i = 1; i <= 9; i++) {
		%>
		<li><%=n1%> * <%=i%> = <%=(n1 * i)%></li>
		<%
		}
		%>
		<br />
	</ul>
	<%
	}
	if (sel.equals("type2")) {
	boolean t = false;
	for (int i = 2; i <= 9; i += n2) {
		t = true;
	%>
	<table border="1">
		<%
		for (int j = 1; j <= 10; j++) {
		%>
		<tr>
			<%
			for (int k = 0; k < n2; k++) {
				if ((i + k) >= 10)
					break;
				if (t) {
			%>
			<td><%=(i + k)%>단</td>
			<%
			} else {
			%>
			<td><%=(i + k)%> * <%=(j - 1)%> = <%=(i + k) * (j - 1)%></td>
			<%
			}
			}
			t = false;
			%>
		</tr>
		<%
		}
		}
		}
		%>
	
</body>
</html>