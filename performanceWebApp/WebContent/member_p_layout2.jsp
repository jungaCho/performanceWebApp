<%-- admin_layout.jsp --%>
<%@ page contentType="text/html; charset=utf-8"%>

<%
	
	String article = request.getParameter("article") + ".jsp";
%>

<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<title>2단 Layout</title>
   <link rel="stylesheet" type="text/css" href="css/common.css" />  
</head>
<body>
	<header>
		<jsp:include page="member_header.jsp" />
	</header>
	<section>
		
		<article><jsp:include page="<%=article%>" /></article>
	</section>
	<footer>Footer</footer>
</body>
</html>