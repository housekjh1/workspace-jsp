<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="JoinProcess.jsp" method="post">

		<table border="1">

			<tr>
				<td>아이디 :</td>
				<td><input type="text" name="id" /></td>
			</tr>
			<tr>
				<td>패스워드 :</td>
				<td><input type="password" name="pwd" /></td>
			</tr>
			<tr>
				<td>이름 :</td>
				<td><input type="text" name="name" /></td>
			</tr>
			<tr>
				<td class="sb" colspan="2" align="center"><input type="submit" value="입력하기" /></td>
			</tr>

		</table>
	</form>
	<a href="LoginForm.jsp">로그인창으로 돌아가기</a>
</body>
</html>