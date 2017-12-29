<%-- member_layout.jsp --%>
<%@ page contentType="text/html; charset=utf-8"%>

<%
	String nav = request.getParameter("nav") + ".jsp";
	String article = request.getParameter("article") + ".jsp";
%>

<!doctype html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>메인 페이지</title>
<style>
body, a {
	font: 20px '돋움';
	color: white;
 	font-weight: bolder; 
}

header {
	width: 960px;
	height: 100px;
	margin-left: auto;
	margin-right: auto;
	background-color: #3c394a;
}

section {
	width: 960px;
	height: auto;
	margin-left: auto;
	margin-right: auto;
	background-color: #8BC34A;
	overflow: hidden;
}

section article {
	width: 100%;
	height: 600px;
	background-color: #ebebeb;
	float: left;
}

footer {
	width: 960px;
	height: 100px;
	margin-left: auto;
	margin-right: auto;
	background-color: #f03535;
}

header nav {
	padding-top: 10px;
}

nav ul {
	list-style-type: none;
}

header nav ul li {
	display: inline;
	margin: 10px;
}

nav ul a {
	text-decoration: none;
}

header nav ul a:hover {
	color: #607d8b;
}

header nav h1 {
	
	float:left; 
	width:100px; 
	margin-left:30px; 
	color:#f25c5c;

}
a:hover {
	color: #607d8b;
}
</style>
</head>
<body>
	<header>
		<jsp:include page="member_header.jsp" />
	</header>
	<section>
		<article><jsp:include page="<%=article%>" /></article>
	</section>
	<footer class="footer">서울특별시 금천구 가산디지털1로 186 제이플라츠 304호 | 2팀  | Tel 010 1234 1234  | COPYRIGHT © PNN	 INC. ALL RIGHTS RESERVED.</footer>
</body>
</html>