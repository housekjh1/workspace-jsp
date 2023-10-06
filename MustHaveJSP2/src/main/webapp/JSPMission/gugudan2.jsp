<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Gugudan2</title>
</head>
<body>
	<%
	int n = 2;
	boolean t = false;
	String dan = (String)request.getParameter("dan");
	if(dan != null) n = Integer.parseInt(dan);
	for (int i = 2; i <= 9; i += n) {
		t = true;
		%>
		<table border="1">
		<%
		for (int j = 1; j <= 10; j++) {
			%>
			<tr>
			<%
			for (int k = 0; k < n; k++) {
				if ((i + k) >= 10) break;
				if (t) {
				%>
				<td><%= (i + k) %>단</td>
				<%
				}
				else {
				%>
				<td><%= (i + k) %> * <%= (j - 1) %> = <%= (i + k) * (j - 1) %></td>
				<%
				}
			}
			t = false;
			%>
			</tr>
			<%
		}
	}
	%>
	</table>
</body>
</html>