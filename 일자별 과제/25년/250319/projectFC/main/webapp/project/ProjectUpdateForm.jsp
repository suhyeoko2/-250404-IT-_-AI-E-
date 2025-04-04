<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>프로젝트 수정/삭제/조회</h1>
	<hr>
	<form action="update.do" method="post">
		프로젝트번호 : <input type="text" name="pno" value="${project.pno}" readonly><br>
		프로젝트명 : <input type="text" name="pname" value="${project.pname}"><br>
		내용 : <input type="text" name="content" value="${project.content}"><br>
		시작일 : <input type="date" name="startdate" value="${project.startdate}"><br>
		종료일 : <input type="date" name="enddate" value="${project.enddate}"><br>
		상태 : <input type="number" name="state" value="${project.state}"><br>
		등록일 ${project.createddate} <br>
		태그 : <input type="text" name="tags" value="${project.tags}"><br>
		<input type="submit" value="수정"><input type="reset" value="취소">
		<a href="delete.do?pno=${project.pno}">[삭제]</a>

	</form>
</body>
</html>