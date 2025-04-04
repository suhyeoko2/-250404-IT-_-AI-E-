<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="dto.Member"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		Member member = (Member)request.getAttribute("member");
	%>
	<h1> 회원 상세 조회 및 수정</h1>
	<hr>
	<form action="update" method="post">
		번호 : <input type="text" name="mno" value="<%=member.getMno()%>"><br>
		이름 : <input type="text" name="mname" value="<%=member.getMname()%>"><br>
		이메일 : <input type="text" name="email" value="<%=member.getEmail()%>"><br>
		가입일 : <%=member.getCre_date()%> <br>
		수정일 : <%=member.getMod_date()%> <br> 
		<input type="submit" value="수정">
		<input type="reset" value="취소">
		<a href="delete?mno=<%=member.getMno()%>">[삭제]</a>	
	</form>
</body>
</html>