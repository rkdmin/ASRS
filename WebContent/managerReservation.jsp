<%@ page language="java" contentType="text/html; charset=euc_kr" 
	import="java.sql.*, AIR.*, util.*, java.util.regex.Pattern, java.util.regex.Matcher"%>
<% request.setCharacterEncoding("EUC-KR"); %>
<HTML>
  <HEAD>
  <meta charset="EUC-KR">
  <TITLE>ASRS</TITLE>
  </HEAD>
  <BODY>
    <%  
    
      // ���� ���� �ҷ�����
      AIRDB.loadConnectAir();		
  	  ResultSet result = AIRDB.getManagerReserve();
  	  request.setAttribute("title", "��� ������Ȳ"); 
      request.setAttribute("RS", result); 
      request.getRequestDispatcher("ListRS2.jsp").forward(request, response);
   %>
  </BODY>
</HTML>