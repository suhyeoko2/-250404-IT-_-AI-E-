<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList, dto.Member"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	table, tr, td, th { 
				border : 1px solid black;
				border-collapse: collapse;
				}
</style>
</head>

<body>
	<h1>회원 목록</h1>
	<hr>
		<table>
		<tr><th>번호</th><th>이름</th><th>이메일</th></tr>
	<%
		ArrayList<Member> members = (ArrayList<Member>)request.getAttribute("members");
	
		for(Member m : members){ %>
			<tr>
				<td><%=m.getMno() %></td>
				<td><a href="update?mno=<%=m.getMno()%>"><%=m.getMname() %></a></td>
				<td><%=m.getEmail() %></td>
			</tr>		
	<%	}
	%>
		</table>
</body>
</html>








