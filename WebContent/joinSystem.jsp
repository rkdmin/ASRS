
	<%@ page language="java" contentType="text/html; charset=euc_kr" 
	import="java.sql.*, AIR.*, util.*, java.util.regex.Pattern, java.util.regex.Matcher"%>
<% request.setCharacterEncoding("euc-kr"); %>
<HTML>
  <HEAD>
  <meta charset="EUC-KR">
  <TITLE>ȸ������ ó��</TITLE>
  </HEAD>
  <BODY>
	<jsp:useBean class="AIR.Customer" id="customer" scope="request" />
	<jsp:setProperty name="customer" property="*" />
    <% 
        String id = customer.getId();
    	String password = request.getParameter("password");
    	String password2 = request.getParameter("password2");
    	// ���Է� ��й�ȣ �ٸ���
    	if(!password.equals(password2)){
    		out.print("<script>alert('�ٽ� �Է��� ��й�ȣ�� �ٸ��ϴ�.')</script>");
			out.print("<script>location='join2.jsp'</script>");
    	}

    	// ��й�ȣ ���� Ȯ��(����, Ư������, ���� ���� 8�� �̻�)
    	Pattern passPattern1 = Pattern.compile("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*\\W).{8,20}$");
    	Matcher passMatcher1 = passPattern1.matcher(password);
    	if(!passMatcher1.find()){
			out.print("<script>alert('��й�ȣ�� ������ Ư������ ���ڸ� �����ϸ� 8�� �̻��̾�� �մϴ�.')</script>");
			out.print("<script>location='join2.jsp'</script>");
    	}
    	
    	//���̵� �ߺ� �Ͻ�
    	AIRDB.loadConnectAir();	
    	boolean result1 = AIRDB.idDuplication(id);
    	if(result1 == false){
    	out.print("<script>alert('�̹� �����ϴ� ���̵� �ֽ��ϴ�.')</script>");
		out.print("<script>location='join2.jsp'</script>");
    	}
    	
    	// ��й�ȣ ��ȣȭ
       	SHA256 sha256 = new SHA256();
       	String secPassword = (sha256.encrypt(password));// ���� ��й�ȣ ��ȣȭ
       	customer.setPassword(secPassword);// ��ȣȭ�� ��й�ȣ ����
       	customer.output();
       	Customer c = customer;
       	
       	AIRDB.loadConnectAir();	
    	boolean result2 = AIRDB.insertCustomer(c);
    	if(result2 == true){
    		out.print("<script>alert('ȸ������ �Ϸ�!')</script>");
    	}
        out.print("<script>location='join3.jsp'</script>");
    %>
    
  </BODY>
</HTML>