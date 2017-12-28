<%-- admin_p_menu.jsp --%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>

<c:url var="url1" value="/admin_r_layout.do" scope="page">
	<c:param name="nav" value="admin_r_menu" />
	<c:param name="article" value="admin_r_retrieveReservationList"/>
</c:url>



<ul>
	<li>관리자 모드</li><br>
	<li><a style="color:#a69bd4" href="${pageScope.url1 }">예매관리</a></li>
</ul>