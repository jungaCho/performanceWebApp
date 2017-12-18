<%-- header.jsp --%>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>

<c:url var="url1" value="/layout.jsp" scope="page">
	<c:param name="nav" value="menu1" />
	<c:param name="article" value="welcome1" />
</c:url>

<nav>
	<h1 style="float:left; width:100px; margin-left:30px; color:#f25c5c">로고</h1>
	<ul style="float:right; margin-right:30px;">
		<li><a href="${pageScope.url1 }">메뉴1</a></li>
		<li><a href="#">메뉴2</a></li>
		<li><a href="#">메뉴3</a></li>
	</ul>
</nav>
