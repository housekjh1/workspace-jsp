<%@page import="utils.JSFunction"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>등가속 운동 물체 위치</h2>
	<span>${ result1 }</span>
	<form action="freefall.do" method="post">
		<table border="1">
			<tr>
				<td>시간 :</td>
				<td><input type="text" name="time" /></td>
			</tr>
			<tr>
				<td>초기위치 :</td>
				<td><input type="text" name="position" /></td>
			</tr>
			<tr>
				<td class="sb" colspan="2" align="center"><input type="submit" value="계산하기" /></td>
			</tr>
		</table>
	</form>
</body>
</html>