
	<%@ page language="java" contentType="text/html; charset=euc_kr" 
	import="java.sql.*, AIR.*, util.*, java.util.regex.Pattern, java.util.regex.Matcher"%>
<% request.setCharacterEncoding("euc-kr"); %>
<HTML>
  <HEAD>
  <meta charset="EUC-KR">
  <TITLE>ȸ������ ó��</TITLE>
  </HEAD>
  <BODY>
	<jsp:useBean class="AIR.Reserve" id="reserve" scope="request" />
	<jsp:setProperty name="reserve" property="*" />
    <% 
   		AIRDB.loadConnectAir();	
		boolean result = AIRDB.insertReserve(reserve);
		
		if(result == true){
			out.print("<script>alert('������ �Ϸ�Ǿ����ϴ�!')</script>");
			out.print("<script>location='main.jsp'</script>");
		}
		else{
			out.print("<script>alert('������ �ȵƽ��ϴ�~!')</script>");
			out.print("<script>location='main.jsp'</script>");
		}
    %>
  </BODY>
</HTML>