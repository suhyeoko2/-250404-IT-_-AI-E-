<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>프로젝트 등록</h1>
	<hr>
	<form action="add.do" method="post">
		프로젝트명 : <input type="text" name="pname"><br>
		내용 : <input type="text" name="content"><br>
		시작일 : <input type="date" name="startdate"><br>
		종료일 : <input type="date" name="enddate"><br>
		상태 : <input type="number" name="state"><br>
		태그 : <input type="text" name="tags"><br>
		<input type="submit" value="저장"><input type="reset" value="취소">
	</form>
</body>
</html>