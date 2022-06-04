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
	<h3>1약관동의 2정보입력 3완료</h3>
      <div>회원가입이 완료되었습니다<br>로그인 해주시길 바랍니다.</div>
      <br>
      <a href="login.jsp"><button>메인으로</button></a>
</body>
</html>
	