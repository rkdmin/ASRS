<%@ page language="java" contentType="text/html; charset=euc_kr" 
	import="java.sql.*, AIR.*, util.*, java.util.regex.Pattern, java.util.regex.Matcher"%>
<% request.setCharacterEncoding("euc-kr"); %>
<HTML>
  <HEAD>
  <meta charset="EUC-KR">
  <TITLE>�α��� ó��</TITLE>
  </HEAD>
  <BODY>
    <%  
      String id = request.getParameter("id");
      String password = request.getParameter("password");
      
      if (id.equals("") || password.equals("")) {
      		out.print("<script>alert('�α��� ���̵�� �н����尡 ��� �Էµ��� �ʾҽ��ϴ�.')</script>");
      		out.print("<script>location='login.jsp'</script>");
       }
        
   // ��й�ȣ ��ȣȭ
       SHA256 sha256 = new SHA256();
     	String secPassword = (sha256.encrypt(password));// ���� ��й�ȣ ��ȣȭ
     	
     	AIRDB.loadConnectAir();	
  	    Customer customer = AIRDB.loginProcess(id, secPassword);// ȸ������ã��
  		
      if (customer == null) {
     		out.print("<script>alert('�߸��� ���̵� �Ǵ� �н����� �Դϴ�.')</script>");     
     		out.print("<script>location='login.jsp'</script>");
      }
      else if(customer.getId().equals("admin")){
    	  System.out.println("  <<for debug >> �α����� ���̵� : " + customer.getId() + ", ����� �̸�: " + customer.getName() + "\n");
    	  session.setAttribute("admin", customer.getId());  // // ����ó��
    	  out.print("<script>location='admin.jsp'</script>");
      }
      else {
		System.out.println("  <<for debug >> �α����� ���̵� : " + customer.getId() + ", ����� �̸�: " + customer.getName() + "\n");
    	session.setAttribute("customer", customer);  // // ����ó��
    	out.print("<script>location='main.jsp'</script>");

      }
   %>
  </BODY>
</HTML>