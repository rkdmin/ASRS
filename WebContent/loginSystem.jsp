<%@ page contentType="text/html;charset=utf-8"
	import="java.sql.*, AIR.*, util.*, java.util.regex.Pattern, java.util.regex.Matcher"%>
<HTML>
  <HEAD><TITLE>회원가입 처리</TITLE></HEAD>
  <BODY>
    <%  
      request.setCharacterEncoding("utf-8");
  	   
      String ID = request.getParameter("id");
      String password = request.getParameter("password");
      
      if (ID.equals("") || password.equals("")) {
      		out.print("<script>alert('로그인 아이디와 패스워드가 모두 입력되지 않았습니다.')</script>");
      		out.print("<script>location='login.jsp'</script>");
       }
        
   // 비밀번호 암호화
       SHA256 sha256 = new SHA256();
     	String secPassword = (sha256.encrypt(password));// 기존 비밀번호 암호화
     	
  	  Customer customer = AIRDB.loginProcess(ID, secPassword);// 회원정보찾기
  		
      if (customer == null) {
     		out.print("<script>alert('잘못된 아이디 또는 패스워드 입니다.')</script>");     
     		out.print("<script>location='login.jsp'</script>");
      }
      else {
		System.out.println("  <<for debug >> 로그인한 아이디 : " + customer.getId() + ", 사용자 이름: " + customer.getName() + "\n");
    	session.setAttribute("customer", customer);  // // 세션 객체에 로그인한 은행원 객체 bankerLogin를 이름 "bankerLogin"로 저장
    	out.print("<script>location='main.jsp'</script>");

      }
   %>
  </BODY>
</HTML>