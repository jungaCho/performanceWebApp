<%-- admin_layout.jsp --%>
<%@ page contentType="text/html; charset=utf-8"%>

<%
	String nav = request.getParameter("nav") + ".jsp";
	String article = request.getParameter("article") + ".jsp";
%>

<!doctype html>
<html>
<head>
<meta charset="UTF-8">

<title>메인 페이지</title>
   <link rel="stylesheet" type="text/css" href="css/common.css" />   
</head>
<body>

	<header>
		<jsp:include page="member_header.jsp" />
	</header>

	<section>

		<article class="article"><jsp:include page="<%=article%>" /></article>

	</section>


	<footer class="footer">서울특별시 금천구 가산디지털1로 186 제이플라츠 304호 | 2팀  | Tel 010 1234 1234  | COPYRIGHT © PNN	 INC. ALL RIGHTS RESERVED.</footer>
</body>
</html>