<%@ page contentType="text/html;charset=utf-8"
	import="java.sql.*, AIR.*, java.util.Calendar;"%>
<html>
<head>
<title>ASRS</title>
</head>
<body>
	<%
			request.setCharacterEncoding("utf-8");
	%>
	<jsp:include page="header.jsp" flush="false"/>
	<h3>빠른예약</h3>
    <select name="start">
     <option value="인천">인천</option>
     <option value="오사카">오사카</option>
     <option value="제주">제주</option>
     <option value="뉴욕">뉴욕</option>
     <option value="괌">괌</option>
     <option value="파리">파리</option>
	</select>
	=>
	<select name="arive">
     <option value="인천">인천</option>
     <option value="오사카">오사카</option>
     <option value="제주">제주</option>
     <option value="뉴욕">뉴욕</option>
     <option value="괌">괌</option>
     <option value="파리">파리</option>
	</select>
	<br><br>
	인원수 : <input type=number value="people" min="0" max="5">
	<%
		
	%>
	날짜 : <input type="date" value="2019-09-22" min="2019-09-10" max="2019-09-25">
	
</body>
</html>
	