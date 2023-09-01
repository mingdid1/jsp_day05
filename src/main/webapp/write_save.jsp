<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	write_save.jsp<br>
	
	<% request.setCharacterEncoding("utf-8"); %>
	
	<jsp:useBean id="dto" class="test.TestDTO"/>
	<jsp:setProperty property="*" name="dto"/>
	
	<jsp:useBean id="dao" class="test.TestDAO"/>
	
	<c:choose>
		<c:when test="${dao.writeSave(dto) == 1 }">
			<script type="text/javascript">
				alert("등록 성공~!!");
				location.href="list.jsp";
			</script>
		</c:when>
		<c:otherwise>
			<script type="text/javascript">
				alert("등록 실패..");
				history.back();
			</script>
		</c:otherwise>
	</c:choose>
</body>
</html>