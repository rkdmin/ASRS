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
      <label><input type="checkbox" name="agree" value="1" id="one">�������� ���� ����</label><br>
      <label><input type="checkbox" name="agree" value="2" id="two">�������� ��� ����</label><br>
      <label><input type="checkbox" name="agree" value="3" id="three">�� 3�� �̿� ����</label><br>
      <input type="button" value="Ȯ��" onclick="test()">
      <script type="text/javascript">
      function test() {
    	  const one = document.getElementsByName("agree")[0].checked;
    	  const two = document.getElementsByName("agree")[1].checked;
    	  const three = document.getElementsByName("agree")[2].checked;
    	  if(one==true&&two==true&&three==true){
    		  location.href = "join2.jsp";
    	  }else{
    		  alert("���� �����ϼž��մϴ�.");
    	  }
      }
</script>
</body>
</html>
	