<%@ page language="java" contentType="text/html; charset=euc_kr" 
	import="java.sql.*, AIR.*, util.*, java.util.regex.Pattern, java.util.regex.Matcher"%>
<% request.setCharacterEncoding("EUC-KR"); %>
<HTML>
  <HEAD>
  <meta charset="EUC-KR">
  <TITLE>�뼱�˻�</TITLE>
  </HEAD>
  <BODY>
    <%  
      // ���� ���� �ҷ�����
      Customer customer = (Customer) session.getAttribute("customer");
	  String id = customer.getId();
	  
      AIRDB.loadConnectAir();		
  	  ResultSet result = AIRDB.getCustomerReserve(id);
  	  request.setAttribute("title", id+"���� ������Ȳ"); 
      request.setAttribute("RS", result); 
      request.getRequestDispatcher("reservationList.jsp").forward(request, response);
   %>
  </BODY>
</HTML>