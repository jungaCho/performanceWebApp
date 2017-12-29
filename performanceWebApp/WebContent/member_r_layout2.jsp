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
<style>
body, a {
	font: 20px '돋움';
	color: white;
	font-weight: bolder;
}

section {
	width: 100%;
	height: auto;
	margin-left: auto;
	margin-right: auto;
	background-color: #4C566E;
	overflow: hidden;
}


footer {
	width: 100%;
	height: 100px;
	margin-left: auto;
	margin-right: auto;
	background-color: #f03535;
}

nav ul {
	list-style-type: none;
}

nav ul a {
	text-decoration: none;
}

section nav {
	width: 25%;
	height: 100%;
	background-color: #4C566E;
	float: left;
}

section article {

	color: black;
	width: 100%;
	height: auto;
	background-color: #ebebeb;
	float: left;
}

#loginm {
	
	font-weight: none;

}

#loginm2 {
	
	text-decoration: bolder;
	color: #f03535;

}

header {

	width: 100%;
	height: 100px;
	margin-left: auto;
	margin-right: auto;
	background-color: #3c394a;
}


header nav {
	padding-top: 10px;
}

header nav ul li {
	display: inline;
	margin: 10px;
}

header nav h1 {
	float: left;
	width: 100px;
	margin-left: 30px;
	color: #f25c5c;
	margin-top:-10px;
}

header nav ul a:hover {
	color: #607d8b;
}

section nav ul li {
	margin: 30px 0px;
}

a:hover {
	color: #607d8b;
}
</style>
<link rel="stylesheet" type="text/css" href="css/common.css" />   
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