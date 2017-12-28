<%-- admin_menu.jsp --%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>

<c:url var="url1" value="/admin_layout.jsp" scope="page">
	<c:param name="nav" value="admin_menu" />
	<c:param name="article" value="admin_welcome" />
</c:url>

<ul>
	<li><a style="color:#a69bd4" href="#">서브메뉴1</a></li>
	<li><a style="color:#a69bd4" href="#">서브메뉴2</a></li>
	<li><a style="color:#a69bd4" href="#">서브메뉴3</a></li>
</ul>