<%@ page language="java" contentType="text/html; charset=euc_kr" import="java.sql.*, AIR.*"%>
<% request.setCharacterEncoding("euc-kr"); %>
<html>
<head>
<meta charset="EUC-KR">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/join.css">
<title>ASRS</title>
</head>
<body>
	<jsp:include page="header.jsp" flush="false"/>
	<section>
		<div id="join2">
			<h3>1.������� -></h3><h3 class="blue"> 2.�����Է�</h3><h3> -> 3.�Ϸ�</h3>
			<h4>�����Է�</h4>
      		<form action="joinSystem.jsp" method="post">
      			<div class="joinform2">
      			���̵�: <input type=text class="forms" name="id" required><br><br>
      			��й�ȣ: <input type=password class="forms" name="password" required><br><br>
      			��й�ȣȮ��: <input type=password class="forms" name="password2" required><br><br>
      			�̸�: <input type=text class="forms" name="name" required><br><br>
      			����: <input type=text class="forms" name="gender" required><br><br>
      			��ȭ��ȣ: <input type=text class="forms" name="number" required><br><br>
      			����: <input type=text class="forms" name="age" required><br><br>
      			���ǹ�ȣ: <input type=text class="forms" name="passportNo" required><br><br>
      			�ּ�: <input type=text class="forms" name="address" required><br><br>
      			</div>
      			<br>
      			<input type="submit" value="����" class="joinbtn">
      		</form>
      	</div>
     </section>
</body>
</html>