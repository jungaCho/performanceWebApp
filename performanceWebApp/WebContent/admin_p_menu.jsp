<%-- member_menu.jsp --%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>

<c:url var="url1" value="/member_m_layout.jsp" scope="page">
	<c:param name="nav" value="member_m_menu" />
	<c:param name="article" value="admin_p_selectPerformanceList" />
</c:url>

<c:url var="url2" value="/retrieveMember.do" scope="page">
	<c:param name="nav" value="member_m_menu" />
	<c:param name="article" value="member_m_selectMemberForm" />
</c:url>

<c:url var="url3" value="/member_m_layout.jsp" scope="page">
	<c:param name="nav" value="member_m_menu" />
	<c:param name="article" value="member_m_withdrawal" />
</c:url>


<ul>
	<li>공연관리</li><br>
	<li><a style="color:#f25c5c" href="${pageScope.url1 }">공연조회</a></li>
	<li><a style="color:#a69bd4" href="${pageScope.url2 }">공연등록</a></li>
	<li><a style="color:#a69bd4" href="${pageScope.url3 }">일정등록</a></li>
</ul>