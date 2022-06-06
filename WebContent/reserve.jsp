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
  <jsp:include page="header2.jsp" flush="false"/>
  <section>
  <div id="rsvWindow">
    <br> 
	<%	
	       String num = request.getParameter("num");
 	       ResultSet rs = (ResultSet) request.getAttribute("RS");
 	       if (rs == null) {
 	    	   out.println("<H3 align=center >ResultSet ��ü�� ���޵��� �ʾҽ��ϴ�.</H3>");  
 	    	   return;
 	       }
 	       
 	%>
 	   <H3 align=center > <% out.println(request.getAttribute("title"));   %> </H3>
 	         
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
	    // ���� ���λ���, �뼱��ȣ ���λ���
	    int[] price = new int[cntTuples];
	    int[] uniqueNo = new int[cntTuples];
	   // js�� Ʃ�ð��� �ο��� �ֱ�
	   out.println("<input type=\"hidden\" id=\"cnt\" value=\" "+ cntTuples+ "\">"  );
	   out.println("<input type=\"hidden\" id=\"num\" value=\" "+ num+ "\">"  );
			out.println("<tr bgcolor=#6c7ae0 class=\"ftable\" >" );

			for(int i=0; i<columns.length; i++){
				out.println("<th>" + columns[i]  + "</th>" );	
			}
			out.println("</tr>" );

			int cnt = 0;
			while(rs.next()) {
				price[cnt]= rs.getInt("�ݾ�");
				uniqueNo[cnt] = rs.getInt("�뼱��ȣ");
				out.println("<input type=\"hidden\" id=\"price" +cnt+ "\" value=\"" + price[cnt] + "\">" );
				out.println("<input type=\"hidden\" id=\"uniqueNo" +cnt+ "\" value=\"" + uniqueNo[cnt] + "\">" );
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
	  
	  <%-- ���Ź�ư������ �������� --%>
	  <form action="reserveSystem.jsp">
	  	<div class="calc"><p class="txt">�Ѱ��� : <input id="total" class="price" name="totalPrice" type="text" value="" readonly>��</p></div>  
	  	<%
	  		Customer customer = (Customer) session.getAttribute("customer");
	  	    String id = customer.getId();
	  	%>
	  	<input type="hidden" id ="uniqueNoTarget"name="uniqueNo" value="">
	  	<input type="hidden" name="id" value="<%=customer.getId()%>">
	  	<input type="hidden" name="num" value="<%=num%>">
	  	<input type="submit" value="����" class="buybtn"> 
	  </form>
	  
	  <script type="text/javascript">
	  const cnt = document.querySelector("#cnt").value;// ��Ʃ�ð���
	  const price = new Array();// ����
	  const uniqueNo = new Array();// ������ȣ
	  const total = document.querySelector("#total");// �Ѱ���
	  const uniqueNoTarget = document.querySelector("#uniqueNoTarget");// �뼱��ȣ
	  const numQ = document.querySelector("#num").value;// �ο���
	  var num = parseInt(numQ);
	  let select = 0;
	  let sum=0;
	  for(var i = 0; i < cnt; i++){
		  priceQ = document.querySelector("#price" + i);
		  price[i] = priceQ.value;
		  uniqueNoQ = document.querySelector("#uniqueNo" + i);
		  uniqueNo[i] = uniqueNoQ.value;
	  }
	  function HighlightRow(obj){
		  const index = obj.rowIndex - 1;
	  	  obj.classList.toggle("clickColor");
	  	  var b = parseInt(price[index]);
	  	  if(obj.className == "clickColor"){
	  		  
	  		if(select >= 1){
	  			alert("2�� �̻� ������ �� �����ϴ�.");
	  			obj.classList.toggle("clickColor");
	  			return 0;
	  		}
	  		select++;
	  		sum += b*num;
	  	  }else{
	  		select--;
	  		sum -= b*num;
	  	  }
	  	  total.value = sum;
	  	  uniqueNoTarget.value = uniqueNo[index];
		}
	</script>
    <br>
    </div>
    </section>
  </BODY>
</HTML>
