<%@ page language="java" contentType="text/html; charset=euc_kr" 
import="java.sql.*, AIR.*, util.*, java.util.regex.Pattern, java.util.regex.Matcher"%>
<% request.setCharacterEncoding("euc-kr"); %>
<HTML>
  <HEAD>
  <meta charset="EUC-KR">
  <TITLE>ȸ������ ó��</TITLE>
  </HEAD>
  <BODY>
	<jsp:useBean class="AIR.Route" id="route" scope="request" />
	<jsp:setProperty name="route" property="*" />
    <% 
       	AIRDB.loadConnectAir();	
    	boolean result = AIRDB.insertRoute(route);
    	if(result == true){
    		out.print("<script>alert('�뼱 �߰� �Ϸ�!')</script>");
    	}
        out.print("<script>location='manager.jsp'</script>");
    %>
    
  </BODY>
</HTML>