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
	<h3>1약관동의 2정보입력 3정보입력2 3완료</h3>
      <form action="joinSystem.jsp">
      	아이디: <input type=text name="id"><br>
      	비밀번호: <input type=text name="password"><br>
      	비밀번호확인: <input type=text name="password2"><br>
      	<br>
      	<input type="submit" value="다음">
      </form>
</script>
</body>
</html>
	