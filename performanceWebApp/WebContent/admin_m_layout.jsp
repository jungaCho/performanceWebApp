<%-- admin_layout.jsp --%>
<%@ page contentType="text/html; charset=utf-8" %>

<%
	String nav = request.getParameter("nav") + ".jsp";
	String article= request.getParameter("article") + ".jsp";
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
		color: white;
		font-weight: bolder;
	}

	header {
		width: 100%;
		height: 100px;
		margin-left: auto;
		margin-right: auto;
		background-color: #3c394a;
	}

	section {
		width: 100%;
		height: 100%;
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



	section article {
		width: 100%;
		height: 800px;
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
      
      header nav h1 {
      	float: left;
      	width: 100px;
      	margin-left: 30px;
      	color:#f25c5c;
      }

  </style>
 </head>
 <body>
	<header>
		<jsp:include page="admin_header.jsp" />
	</header>
	
	<section>
		<article><jsp:include page="admin_m_SelectList.jsp" /></article>
	</section>
	<footer>Footer</footer>
 </body>
</html>