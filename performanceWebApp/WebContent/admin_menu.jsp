<%-- admin_menu.jsp --%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>

<c:url var="url1" value="/admin_m_layout.jsp" scope="page">
	<c:param name="nav" value="admin_menu" />
	<c:param name="article" value="admin_welcome" />
</c:url>

<ul>
	<li>관리자 모드</li><br>
	<li><a style="color:#a69bd4" href="${pageScope.url1 }">회원관리</a></li>
</ul>