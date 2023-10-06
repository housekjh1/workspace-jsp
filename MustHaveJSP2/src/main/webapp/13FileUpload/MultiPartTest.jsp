<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FileUpLoad</title>
</head>
<script>
	function validateForm(form) {
		if (form.title.value == "") {
			alert("제목을 입력하세요.");
			form.title.focus();
			return false;
		}
		if (form.attachedFile.value == "") {
			alert("첨부 파일은 필수 입력입니다.");
			return false;
		}
	}
</script>
<body>
	<h3>파일 업로드</h3>
	<span style="color: red;">${errorMessage }</span>
	<form name="fileForm" method="post" enctype="multipart/form-data"
		action="TestProcess.do" onsubmit="return validateForm(this);">
		제목 : <input type="text" name="title" /><br /> 
		카테고리(선택사항) : 
			<input type="checkbox" name="cate" value="사진" checked />사진 
			<input type="checkbox" name="cate" value="과제" />과제 
			<input type="checkbox" name="cate" value="워드" />워드 
			<input type="checkbox" name="cate" value="음원" />음원<br /> 
		연령대(선택사항) : 
			<input type="radio" name="sel" value="10"/>~10대
			<input type="radio" name="sel" value="20" checked/>20대
			<input type="radio" name="sel" value="30"/>30대
			<input type="radio" name="sel" value="40"/>40대
			<input type="radio" name="sel" value="50"/>50대
			<input type="radio" name="sel" value="60"/>60대~ <br/>
		성별 :
			<input type="radio" name="gen" value="m" checked/>남자
			<input type="radio" name="gen" value="w" />여자<br/>
		첨부 파일 : <input type="file" name="ofile" multiple/><br />
		<input type="submit" value="전송하기" />
	</form>
	<c:if test="${not (result eq null) }" var="res">
		<c:forEach items="${result }" var="val">${val }<br/>
		</c:forEach>
	</c:if>
	<c:if test="${not res }">
		데이터를 입력하고 전송하세요.
	</c:if>
</body>
</html>