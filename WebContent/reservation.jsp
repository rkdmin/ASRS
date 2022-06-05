<%@ page language="java" contentType="text/html; charset=euc_kr" 
	import="java.sql.*, AIR.*, util.*, java.util.regex.Pattern, java.util.regex.Matcher"%>
<% request.setCharacterEncoding("EUC-KR"); %>
<HTML>
  <HEAD>
  <meta charset="EUC-KR">
  <TITLE>노선검색</TITLE>
  </HEAD>
  <BODY>
    <%  
      // 세션 정보 불러오기
      Customer customer = (Customer) session.getAttribute("customer");
	  String id = customer.getId();
	  
      AIRDB.loadConnectAir();		
  	  ResultSet result = AIRDB.getCustomerReserve(id);
  	  request.setAttribute("title", id+"님의 예약현황"); 
      request.setAttribute("RS", result); 
      request.getRequestDispatcher("reservationList.jsp").forward(request, response);
   %>
  </BODY>
</HTML>