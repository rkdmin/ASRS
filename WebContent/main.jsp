<%@ page contentType="text/html;charset=utf-8"
	import="java.sql.*, AIR.*, java.text.SimpleDateFormat, java.util.Calendar, java.util.Date"%>
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
	<form action=route.jsp>
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
	인원수 : <input type=number value="num" min="1" max="10"><br><br>
	<%
		// 현재 날짜 출력
		Date now = Calendar.getInstance().getTime();       
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat formatter2 = new SimpleDateFormat("dd");
		String min = formatter.format(now);         
	%>
	날짜 : <input type="date" value=<%=min %> min=<%=min%>><br><br>
	<input type="submit" value="검색하기">
	</form>
</body>
</html>
	