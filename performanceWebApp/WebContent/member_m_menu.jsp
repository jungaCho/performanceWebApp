<%-- member_menu.jsp --%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>

<c:url var="url1" value="/member_layout.jsp" scope="page">
	<c:param name="nav" value="member_menu" />
	<c:param name="article" value="member_welcome" />
</c:url>


<ul>
	<li>MY PAGE</li><br>
	<li><a style="color:#f25c5c" href="${pageScope.url1 }">나의 관람내역</a></li>
	<li><a style="color:#a69bd4" href="#">회원정보 조회</a></li>
	<li><a style="color:#a69bd4" href="#">회원탈퇴</a></li>
</ul>