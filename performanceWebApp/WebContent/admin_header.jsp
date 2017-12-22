<%-- header.jsp --%>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>

<c:url var="url1" value="/admin_layout.jsp" scope="page">
	<c:param name="nav" value="admin_menu" />
	<c:param name="article" value="admin_welcome" />
</c:url>

<c:url var="url2" value="/admin_layout.jsp" scope="page">
	<c:param name="nav" value="menu1" />
	<c:param name="article" value="welcome1" />
</c:url>

<c:url var="url3" value="/admin_p_selectPerformanceList.do" scope="page">
	<c:param name="nav" value="admin_p_menu" />
	<c:param name="article" value="admin_p_selectPerformanceList" />
</c:url>

<c:url var="urlHome" value="/admin_index.jsp" scope="page">
	<c:param name="nav" value="admin_menu" />
	<c:param name="article" value="admin_welcome" />
</c:url>

<nav>
	<a href ="${pageScope.urlHome }"><h1>로고</h1></a>
	<ul style="float:right; margin-right:30px;">
		<li><a href="${pageScope.url1 }">회원관리</a></li>
		<li><a href="#">예매관리</a></li>
		<li><a href="${pageScope.url3 }">공연관리</a></li>
		<li><span id="loginm">use사용자이름 님 환영합니다</span></li>
		<li><a href="#"><span id="loginm2">로그아웃</span></a><li>
	</ul>
</nav>
