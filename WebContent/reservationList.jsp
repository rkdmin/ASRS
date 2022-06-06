<%@ page language="java" contentType="text/html; charset=euc_kr" 
	import="java.sql.*, AIR.*, util.*, java.util.regex.Pattern, java.util.regex.Matcher"%>
<% request.setCharacterEncoding("euc-kr"); %>
<HTML>
<head>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/rs.css">
  <meta charset="EUC-KR">
</head>
  <BODY>
  
  <jsp:include page="header2.jsp" flush="false"/>
  <section>
   <div id="rsvWindow">
    <br> 
	<%	
 	       ResultSet rs = (ResultSet) request.getAttribute("RS");
 	       if (rs == null) {
 	    	   out.println("<H3 align=center >ResultSet ��ü�� ���޵��� �ʾҽ��ϴ�.</H3>");  
 	    	   return;
 	       }
 	       
 	%>
 	   <H3 align=center class="rsvstat"> <% out.println(request.getAttribute("title"));   %> </H3>
 	         
 	<% 
			rs.last();  // rs Ŀ���� ������ �̵��Ͽ� ���� ���� Ȯ��
			int cntTuples = rs.getRow();  // ���� ������ ����
			rs.beforeFirst();  // rs Ŀ���� ó������ �̵�
			System.out.println("   >> cntTuples = " + cntTuples + "\n");	
			 
			if (cntTuples == 0) { // ���� ������ 0�̸�
				out.println("<center>(��� ����)</center>");
				return;
			}
			
 	       
 System.out.println("   >> rs : " + rs + "\n");	       
 
		   ResultSetMetaData md = rs.getMetaData();
		   int count = md.getColumnCount();
		   String[] columns = new String[count];
		   String[] columnTypes = new String[count];
		   
		   
		   for(int i=0; i<count; i++){
			   columns[i] = md.getColumnLabel(i+1);
			   columnTypes[i] = md.getColumnTypeName(i+1);
			   
System.out.println("   >> clms : " + columns[i] + " " + columnTypes[i]+ "\n");							
     	   }
			   
	  %>
	  
	  <table id=table align=center valign=top border=1 cellpadding=8 cellspacing=0 bordercolor=#999999>
	   <%      		
	    // �����ȣ ���λ���
	    int[] reserveId = new int[cntTuples];
	   
	   // js�� Ʃ�ð��� �ο��� �ֱ�
	   out.println("<input type=\"hidden\" id=\"cnt\" value=\" "+ cntTuples+ "\">"  );
			out.println("<tr bgcolor=#6c7ae0 class=\"ftable\" >" );

			for(int i=0; i<columns.length; i++){
				out.println("<th>" + columns[i]  + "</th>" );	
			}
			out.println("</tr>" );

			int cnt = 0;
			while(rs.next()) {
				reserveId[cnt] = rs.getInt("�����ȣ");
				out.println("<input type=\"hidden\" id=\"reserveId" +cnt+ "\" value=\"" + reserveId[cnt] + "\">" );
				cnt++;
				out.println("<tr onClick=\"HighlightRow(this)\">" );

				for(int i=0; i<columns.length; i++){
					Object obj= rs.getObject(columns[i]);
					out.println("<td>" + obj+  "</td>");
				}
				out.println("</tr>" );
			}
			
      %>
      
	  </table >
	  
	  <%-- ��ҹ�ư������ �������� --%>
	  <form action="reserveCancelSystem.jsp">
	  	<input type="hidden" id ="reserveIdTarget"name="reserveId" value="">
	  	<input type="submit" value="���" class="canbtn"> 
	  </form>
	  
	  <script type="text/javascript">
	  const cnt = document.querySelector("#cnt").value;// ��Ʃ�ð���
	  const reserveId = new Array();// ������ȣ
	  const reserveIdTarget = document.querySelector("#reserveIdTarget");// �뼱��ȣ
	  let select = 0;
	  for(var i = 0; i < cnt; i++){
		  reserveIdQ = document.querySelector("#reserveId"+ i);
		  reserveId[i] = reserveIdQ.value;
		  console.log(reserveId[i]);
	  }
	  function HighlightRow(obj){
		  const index = obj.rowIndex-1;
	  	  obj.classList.toggle("clickColor2");
	  	  if(obj.className == "clickColor2"){
	  		if(select >= 1){
	  			alert("2�� �̻� ������ �� �����ϴ�.");
	  			obj.classList.toggle("clickColor2");
	  			return 0;
	  		}
	  		select++;
	  	  }else{
	  		select--;
	  	  }
	  	  reserveIdTarget.value = reserveId[index];
	  	console.log("fuck" + reserveId[index]);
		}
	</script>
    <br>
    </div>
    </section>
  </BODY>
</HTML>
