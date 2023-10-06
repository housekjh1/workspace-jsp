<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
//     request.setCharacterEncoding("UTF-8");
    String pValue = "방랑시인";
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>액션 태그 - param</title>
</head>
<body>
	<jsp:useBean id="person" class="common.Person" scope="request"/>
	<jsp:setProperty property="name" value="김삿갓" name="person"/>
	<jsp:setProperty property="age" value="56" name="person"/>
	<jsp:forward page="ParamForward.jsp?param1=김병연">
		<jsp:param value="경기도 양주" name="param2"/>
		<jsp:param value="<%= pValue %>" name="param3"/>
	</jsp:forward>
</body>
</html>