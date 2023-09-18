<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="InsertProcess.jsp" method="post">
		<table border="1">
			<br />
			<tr>
				<td>아이디 : <input type="text" name="id" /></td>
				<td>패스워드 : <input type="password" name="pwd" /></td>
				<td>이름 : <input type="text" name="name" /></td>
				<td><input type="submit" value="입력하기" /></td>
			</tr>
		</table>
	</form>
</body>
</html>