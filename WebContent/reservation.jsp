<%@ page language="java" contentType="text/html; charset=euc_kr" 
	import="java.sql.*, AIR.*, util.*, java.util.regex.Pattern, java.util.regex.Matcher"%>
<% request.setCharacterEncoding("euc-kr"); %>
<HTML>
  <HEAD>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/rs.css">
  <meta charset="EUC-KR">
  <TITLE>ASRS</TITLE>
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