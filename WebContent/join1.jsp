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
      <label><input type="checkbox" name="agree" value="1" id="one">개인정보 제공 동의</label><br>
      <label><input type="checkbox" name="agree" value="2" id="two">개인정보 사용 동의</label><br>
      <label><input type="checkbox" name="agree" value="3" id="three">제 3자 이용 동의</label><br>
      <input type="button" value="확인" onclick="test()">
      <script type="text/javascript">
      function test() {
    	  const one = document.getElementsByName("agree")[0].checked;
    	  const two = document.getElementsByName("agree")[1].checked;
    	  const three = document.getElementsByName("agree")[2].checked;
    	  if(one==true&&two==true&&three==true){
    		  location.href = "join2.jsp";
    	  }else{
    		  alert("전부 동의하셔야합니다.");
    	  }
      }
</script>
</body>
</html>
	