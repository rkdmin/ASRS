<%@ page language="java" contentType="text/html; charset=euc_kr" import="java.sql.*, AIR.*"%>
<% request.setCharacterEncoding("euc-kr"); %>
<html>
<head>
<meta charset="EUC-KR">
<title>ASRS</title>
</head>
<body>
	<jsp:include page="header.jsp" flush="false"/>
	<h3>1약관동의 2정보입력 3완료</h3>
      <form action="joinSystem.jsp" method="post">
      	아이디: <input type=text name="id" required><br>
      	비밀번호: <input type=password name="password" required><br>
      	비밀번호확인: <input type=password name="password2" required><br>
      	이름: <input type=text name="name" required><br>
      	성별: <input type=text name="gender" required><br>
        전화번호: <input type=text name="number" required><br>
      	나이: <input type=text name="age" required><br>
      	여권번호: <input type=text name="passportNo" required><br>
      	주소: <input type=text name="address" required><br>
      	<br>
      	<input type="submit" value="다음">
      </form>
</body>
</html>
	