<%-- admin_layout.jsp --%>
<%@ page contentType="text/html; charset=utf-8" %>

<%
	String nav = request.getParameter("nav") + ".jsp";
	String article= request.getParameter("article") + ".jsp";
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

section nav {
		width: 25%;
		height: 600px;
		background-color: #4C566E;
		float: left;
	}

	section article {
		width: 75%;
		height: 600px;
		background-color: #ebebeb;
		float: left;
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
 </head>
 <body>
	<header>
		<jsp:include page="header.jsp" />
	</header>
	<section>
		<nav><jsp:include page="<%=nav %>" /></nav>
		<article><jsp:include page="<%=article %>" /></article>
	</section>
	<footer>Footer</footer>
 </body>
</html>