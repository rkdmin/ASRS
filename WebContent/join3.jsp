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
		<div id="join3">
			<h3>1.������� -> 2.�����Է� -></h3><h3 class="blue"> 3.�Ϸ�</h3>
			<br>
			<br>
     	 	<div class="txt">ȸ�������� �Ϸ�Ǿ����ϴ�.<br>�α��� ���ֽñ� �ٶ��ϴ�.</div>
     	 	<br>
      		<a href="login.jsp"><button class="loginbtn">�α���</button></a>
      	</div>
	</section>
</body>
</html>
	