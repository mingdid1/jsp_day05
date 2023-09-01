<%@page import="test.TestDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	modify.jsp<br>
	<% request.setCharacterEncoding("utf-8"); %>
	
	<jsp:useBean id="dto" class="test.TestDTO"/>
	<jsp:setProperty property="*" name="dto"/>
	
	<jsp:useBean id="dao" class="test.TestDAO"/>
	${dao.modify(dto) }
	<% response.sendRedirect("content_view.jsp?id="+dto.getId() ); %>
	
</body>
</html>