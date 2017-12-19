<%-- member_menu.jsp --%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>

<c:url var="url1" value="/member_r_layout.jsp" scope="page">
	<c:param name="nav" value="member_r_menu" />
	<c:param name="article" value="member_r_reservation" />
</c:url>

<c:url var="url2" value="/member_r_layout2.jsp" scope="page">
	<c:param name="nav" value="member_r_menu" />
	<c:param name="article" value="member_r_reservationList" />
</c:url>


<ul>
	<li>예매</li><br>
	<li><a style="color:#f25c5c" href="${pageScope.url1 }">예매</a></li>
	<li><a style="color:#a69bd4" href="${pageScope.url2 }">예매내역조회</a></li>
</ul>