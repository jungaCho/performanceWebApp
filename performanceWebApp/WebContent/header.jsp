<%-- header.jsp --%>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>

<c:url var="url1" value="/member_layout.jsp" scope="page">
	<c:param name="nav" value="menu1" />
	<c:param name="article" value="welcome1" />
</c:url>

<c:url var="url2" value="/member_layout.jsp" scope="page">
	<c:param name="nav" value="menu1" />
	<c:param name="article" value="welcome1" />
</c:url>

<c:url var="url3" value="/member_layout.jsp" scope="page">
	<c:param name="nav" value="menu1" />
	<c:param name="article" value="welcome1" />
</c:url>

<c:url var="urlHome" value="/member_layout.jsp" scope="page">
	<c:param name="nav" value="menu1" />
	<c:param name="article" value="welcome1" />
</c:url>

<nav>
	<a href ="#"><h1 style="float:left; width:100px; margin-left:30px; color:#f25c5c">로고</h1></a>
	<ul style="float:right; margin-right:30px;">
		<li><a href="${pageScope.url1 }">공연정보</a></li>
		<li><a href="#">예매</a></li>
		<li><a href="#">마이페이지</a></li>
	</ul>
</nav>
