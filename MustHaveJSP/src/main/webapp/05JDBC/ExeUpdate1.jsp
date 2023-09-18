<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="common.JDBConnect"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 추가 테스트(executeUpdate() 사용)</title>
</head>
<body>
	<h2>게시판 추가 테스트(executeUpdate() 사용)</h2>
	<%
	JDBConnect jdbc = new JDBConnect(application);
	Connection con = jdbc.getConnection();
	
	int c = 0;
	
	String[] ids = {"musthave", "willhave", "dohave"};
	
	String sql = "insert into board (title, content, id) values (?, ?, ?)";
	PreparedStatement pst = con.prepareStatement(sql);
	
	for (int i = 0; i < ids.length; i++) {
		for (int j = 1; j < 10; j++) {
			pst.setString(1, "제목 " + j + "입니다.");
			pst.setString(2, "내용 " + j + "입니다.");
			pst.setString(3, ids[i]);
			
			c += pst.executeUpdate();
		}
		out.println("board 테이블에 사용자 id[" + ids[i] + "]에 " + c + "행이 입력되었습니다.<br/>");
		c = 0;
	}
	
// 	int k = 0;
// 	while (k < 10) {
// 		pst.setString(1, "제목 " + k + "입니다.");
// 		pst.setString(2, "내용 " + k + "입니다.");
// 		pst.setString(3, ids[k%3]);
// 		c += pst.executeUpdate();
// 	}
// 	out.println("board 테이블에 " + c + "행이 입력되었습니다.");
	
	pst.close();
	con.close();
	%>
</body>
</html>