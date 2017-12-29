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
  <title>예매 관리</title>
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
			margin-top:30px;
		  }
		header nav h1{margin-top:-10px;}

	  header nav ul li {
			display: inline;
			margin: 10px;
	  }

	  nav ul a {
			text-decoration: none;
			color:#fff;
	  }

section nav {
		width: 20%;
		
		background-color: #4C566E;
		float: left;
	}

	section article {
		width: 80%;
		height: 100%;
		min-height:600px;
		background-color: #ebebeb;
		float: left;
		
	}



	  a:hover {
			color: #607d8b;
      }
      


  </style>
  <link rel="stylesheet" type="text/css" href="css/common.css" />   


  
 </head>
 <body>
	<header>
		<jsp:include page="admin_header.jsp" />
	</header>
	
	<section>
		<nav><jsp:include page="<%=nav %>" /></nav>
		<article><jsp:include page="<%=article %>" /></article>
	</section>
	<footer class="footer">서울특별시 금천구 가산디지털1로 186 제이플라츠 304호 | 2팀  | Tel 010 1234 1234  | COPYRIGHT © PNN	 INC. ALL RIGHTS RESERVED.</footer>
 </body>
</html>