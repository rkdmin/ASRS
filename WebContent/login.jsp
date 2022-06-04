<%@ page language="java" contentType="text/html; charset=euc_kr" 
	import="java.sql.*, AIR.*, util.*, java.util.regex.Pattern, java.util.regex.Matcher"%>
<% request.setCharacterEncoding("euc-kr"); %>
<html>
<head>
<meta charset="EUC-KR">
<title>ASRS</title>
</head>
<body>
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
