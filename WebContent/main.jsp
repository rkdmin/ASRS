<%@ page language="java" contentType="text/html; charset=euc_kr" 
	import="java.sql.*, AIR.*, java.text.SimpleDateFormat, java.util.Calendar, java.util.Date"%>
<% request.setCharacterEncoding("euc-kr"); %>
<html>
<head>
<meta charset="EUC-KR">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
<title>ASRS</title>
</head>
<body>
	<jsp:include page="header2.jsp" flush="false"/>
	<section>
		<div id="main">
	<h3>��������</h3>
	<form action=route.jsp method="post">
    <select name="start" class="sct">
     <option value="��õ">��õ</option>
     <option value="����ī">����ī</option>
     <option value="����">����</option>
     <option value="����">����</option>
     <option value="��">��</option>
     <option value="�ĸ�">�ĸ�</option>
	</select>
	=>
	<select name="arrive" class="sct">
     <option value="��õ">��õ</option>
     <option value="����ī">����ī</option>
     <option value="����">����</option>
     <option value="����">����</option>
     <option value="��">��</option>
     <option value="�ĸ�">�ĸ�</option>
	</select>
	<br><br>
	�ο��� : <input type=number name="num" min="1" max="10" class="forms"><br><br>
	<%
		// ���� ��¥ ���
		Date now = Calendar.getInstance().getTime();       
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat formatter2 = new SimpleDateFormat("dd");
		String min = formatter.format(now);
	%>
	��߳�¥ : <input type="date" value=<%=min %> min=<%=min%> name="date" class="forms"><br><br>
	<input type="submit" value="�˻��ϱ�" class="searchbtn">
	</form>
		</div>
	</section>
</body>
</html>
	