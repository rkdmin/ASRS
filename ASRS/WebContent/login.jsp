<%@ page contentType="text/html;charset=utf-8"
	import="java.sql.*, AIR.*"%>
<html>
<head>
<title>ASRS</title>
</head>
<body>
	<%
			request.setCharacterEncoding("utf-8");
	%>
	<jsp:include page="header.jsp" flush="false"/>
	<h3>로그인</h3>
	<form action="loginSystem.jsp">
		아이디: <input type="text" name="id"><br>
		비밀번호: <input type="password" name="password"><br>
		<br>
		<input type="submit" value="로그인"> 
	</form>
	<a href="join1.jsp"><button>회원가입</button></a>
</body>
</html>
