<%@ page language="java" contentType="text/html; charset=euc_kr" 
	import="java.sql.*, AIR.*, util.*, java.util.regex.Pattern, java.util.regex.Matcher"%>
<% request.setCharacterEncoding("euc-kr"); %>
<HTML>
  <HEAD>
  <meta charset="EUC-KR">
  <TITLE>�뼱�˻�</TITLE>
  </HEAD>
  <BODY>
    <%  
      String start = request.getParameter("start");
      String arrive = request.getParameter("arrive");
      String numString = request.getParameter("num");
      int num = Integer.parseInt(numString);
      String sDate = request.getParameter("sDate");
      String aDate = request.getParameter("aDate");
      
      AIRDB.loadConnectAir();		
  	  ResultSet result = AIRDB.getRoute(sDate);
  	  request.setAttribute("title", "��߳뼱"); 
      request.setAttribute("RS", result); 
      request.getRequestDispatcher("ListRS.jsp").forward(request, response);
   %>
  </BODY>
</HTML>