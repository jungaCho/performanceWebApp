<%-- admin_layout.jsp --%>
<%@ page contentType="text/html; charset=utf-8"%>

<%
	String nav = request.getParameter("nav") + ".jsp";
	String article = request.getParameter("article") + ".jsp";
%>

<!doctype html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="Generator" content="EditPlus®">
<meta name="Author" content="">
<meta name="Keywords" content="">
<meta name="Description" content="">
<title>2단 Layout</title>
<style>

body, a {
	
	font: 20px '돋움';
	color : #ebebeb;
	font-weight: bolder;
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
	height: 100%;
	background-color: #ebebeb;
	/* float: left; */
}

footer {
	width: 960px;
	height: 100px;
	margin-left: auto;
	margin-right: auto;
	background-color: #f03535;
}

#loginm {
	
	font-weight: none;

}

#loginm2 {
	
	text-decoration: bolder;
	color: #f03535;

}

nav ul {
	list-style-type: none;
}



header {
	width: 960px;
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
}

header nav ul a:hover {
	color: #ffffff;
}


nav ul a {
	text-decoration: none;
}

section article {
	width: 100%;
	height: 600px;
	background-color: #ebebeb;
}

section nav ul li {
	margin: 30px 0px;
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


	<footer>Footer</footer>
</body>
</html>