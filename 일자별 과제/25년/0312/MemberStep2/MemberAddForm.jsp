<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>신규 등록화면</h1>
	<hr>
	<form action="add" method="post">
		이름 : <input type="text" name="mname"><br>
		이메일 : <input type="text" name="email"><br>
		비번 : <input type="password" name="pwd"><br>
		<input type="submit" value="등록">
		<input type="reset" value="취소">
	</form>
</body>
</html>