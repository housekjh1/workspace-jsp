<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2><%= request.getAttribute("msg") %></h2>
	<br/>
	<a href="JoinForm.jsp">입력창으로 돌아가기</a>
</body>
</html>