<%@ page language="java" contentType="text/html; charset=euc_kr" import="java.sql.*, AIR.*"%>
<% request.setCharacterEncoding("euc-kr"); %>
<html>
<head>
<meta charset="EUC-KR">
<title>ASRS</title>
</head>
<body>
	<jsp:include page="header.jsp" flush="false"/>
	<h3>1������� 2�����Է� 3�Ϸ�</h3>
      <form action="joinSystem.jsp" method="post">
      	���̵�: <input type=text name="id" required><br>
      	��й�ȣ: <input type=password name="password" required><br>
      	��й�ȣȮ��: <input type=password name="password2" required><br>
      	�̸�: <input type=text name="name" required><br>
      	����: <input type=text name="gender" required><br>
        ��ȭ��ȣ: <input type=text name="number" required><br>
      	����: <input type=text name="age" required><br>
      	���ǹ�ȣ: <input type=text name="passportNo" required><br>
      	�ּ�: <input type=text name="address" required><br>
      	<br>
      	<input type="submit" value="����">
      </form>
</body>
</html>
	