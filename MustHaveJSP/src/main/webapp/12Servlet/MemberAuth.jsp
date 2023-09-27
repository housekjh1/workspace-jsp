<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>MVC 패턴으로 회원인증하기</h2>
	<p>
		<strong>${ authMessage }</strong>
		<br />
		<a href="./MemberAuth.mvc?id=nakja&pass=1234">회원인증(관리자)</a>
		&nbsp;&nbsp;
		<a href="./MemberAuth.mvc?id=musthave&pass=1234">회원인증(회원)</a>
		&nbsp;&nbsp;
		<a href="./MemberAuth.mvc?id=stranger&pass=1234">회원인증(비회원)</a>
	</p>
	<form action="MemberAuth.mvc" method="post" name="loginFrm" onsubmit="return validateForm(this);">
		<table border="1">
		<tr><td>아이디 : </td><td><input type="text" name="id" /></td></tr> 
		<tr><td>패스워드 : </td><td><input type="password" name="pass" /></td></tr> 
		<tr><td class="sb" colspan="2" align="center"><input type="submit" value="로그인하기" /></td></tr>
		</table>
	</form>
</body>
</html>