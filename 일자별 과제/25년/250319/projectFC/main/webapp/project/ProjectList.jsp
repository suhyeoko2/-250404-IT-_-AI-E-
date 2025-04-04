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
	<h1>프로젝트 리스트</h1>
	<a href="add.do">신규등록</a>
	<hr>
	<table>
		<tr><th>번호</th><th>프로젝트명</th><th>생성일자</th></tr>
		<c:forEach var="p" items="${projects}">
			<tr><td>${p.pno}</td>
			<td><a href="update.do?pno=${p.pno}">${p.pname}</a></td>
			<td>${p.createddate}</td></tr>
		</c:forEach>
	</table>

</body>
</html>