<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="common.JDBConnect"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JDBC</title>
</head>
<body>
	<h2>회원 추가 테스트(executeUpdate() 사용)</h2>
	<%
	JDBConnect jdbc = new JDBConnect(application);
	Connection con = jdbc.getConnection();
	int c = 0;
	
	String[] ids = {"musthave", "willhave", "dohave"};
	String[] names = {"머스트해브", "윌해브", "두해브"};
	
	PreparedStatement pst = con.prepareStatement("INSERT INTO member (id, pass, name) VALUES (?, ?, ?)");
	
	for (int i = 0; i < ids.length; i++) {
		pst.setString(1, ids[i]);
		pst.setString(2, "1234");
		pst.setString(3, names[i]);
		c += pst.executeUpdate();
	}
	
	out.println("member 테이블에 " + c + "행이 입력되었습니다.");
	pst.close();
	jdbc.close();
	%>
</body>
</html>