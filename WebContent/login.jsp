<%@ page language="java" contentType="text/html; charset=euc_kr" 
	import="java.sql.*, AIR.*, util.*, java.util.regex.Pattern, java.util.regex.Matcher"%>
<% request.setCharacterEncoding("euc-kr"); %>
<html>
<head>
<meta charset="EUC-KR">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css">
<title>�α���</title>
</head>
<body>
	<jsp:include page="header.jsp" flush="false"/>
		<section>
			<div id="loginw">
			<h3>�α���</h3>
			<form action="loginSystem.jsp">
			���̵�   <br><input type="text" name="id" class="forms"><br><br>
			��й�ȣ  <br><input type="password" name="password" class="forms"><br><br>
			<input type="submit" value="�α���" class="loginbtn">
			</form>
			<a href="join1.jsp"><button class="joinbtn">
			ȸ������
			</button></a>
			</div>
		</section>
</body>
</html>
