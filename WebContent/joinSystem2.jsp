<%@ page contentType="text/html;charset=utf-8"
	import="java.sql.*, AIR.*, util.*, java.util.regex.Pattern, java.util.regex.Matcher"%>
<HTML>
  <HEAD><TITLE>회원가입 처리</TITLE></HEAD>
  <BODY>
	<jsp:useBean class="AIR.Customer" id="customer" scope="request" />
	<jsp:setProperty name="customer" property="id" />
	<jsp:setProperty name="customer" property="password" />
    <%  
	    request.setCharacterEncoding("utf-8");
    	String userId = customer.getUserId();
    	String password = customer.getPassword();
    	String password2 = request.getParameter("password2");
    	
    	// 비밀번호 포맷 확인(영문, 특수문자, 숫자 포함 8자 이상)
    	if(!password.equals("password2")){
    		out.print("<script>alert('다시 입력한 비밀번호가 다릅니다. ')</script>");
			out.print("<script>location='join2.jsp'</script>");
    	}
    	// 비밀번호 포맷 확인(영문, 특수문자, 숫자 포함 8자 이상)
    	Pattern passPattern1 = Pattern.compile("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*\\W).{8,20}$");
    	Matcher passMatcher1 = passPattern1.matcher(password);
    	if(!passMatcher1.find()){
			out.print("<script>alert('비밀번호는 영문과 특수문자 숫자를 포함하며 8자 이상이어야 합니다.')</script>");
			out.print("<script>location='join2.jsp'</script>");
    	}
    	
    	// 비밀번호 암호화
       	SHA256 sha256 = new SHA256();
       	String secPassword = (sha256.encrypt(password));// 기존 비밀번호 암호화
       	
       	//AIRDB.insertCustomer1(String userId, String password); DB에 정보삽입
       out.print("<script>location='join3.jsp'</script>");
    %>
    
  </BODY>
</HTML>