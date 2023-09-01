<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	write_view.jsp<br>
	
	<form action="write_save.jsp" method="post">
		이름 : <input type="text" name="name"><br>
		제목 : <input type="text" name="title"><br>
		내용 : <textarea rows="" cols="" name="content"></textarea>
		<hr>
		<input type="submit" value="등록"><br>
		<!--
		 history.back() : 웹 브라우저에서 저장되어있는 이전으로 이동
		  웹상에 남아있는 기록으로 넘어가기때문에 기록이 남은 상태로 돌아가짐
		  (글등록, 수정시에 글 작성 폼을 남긴상태로 돌아갈수있음)
		-->
		<input type="button" value="목록 이동" onclick="history.back()"><br>
	</form>
	
</body>
</html>