<%-- header.jsp --%>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>


<c:url var="url1" value="/member_layout.jsp" scope="page">
	<c:param name="article" value="member_p_selectPerformance" />
</c:url>

<c:url var="url2" value="/member_r_layout.jsp" scope="page">
	<c:param name="nav" value="member_r_menu" />
	<c:param name="article" value="member_r_reservation" />
</c:url>

<c:url var="url3" value="/member_m_layout.jsp" scope="page">
	<c:param name="nav" value="member_m_menu" />
	<c:param name="article" value="member_m_myPageMain" />
</c:url>

<c:url var="urlHome" value="/mem_mainTestjsp.jsp" scope="page">
	<c:param name="article" value="member_welcome" />
</c:url>

<c:url var="urlLogin" value="/loginForm.do" scope="page">
</c:url>

<c:url var="urlLogout" value="/member_m_layout.jsp" scope="page">
	<c:param name="article" value="member_welcome" />
</c:url>

<c:if test="${empty sessionScope.member}">
<script src=js/jquery-3.2.1.min.js></script>
<script>
	$(document).ready(function () {
		$('#loginm').text("로그인이 필요합니다");
		$('#loginm2').text("로그인");
	});
	
</script>
</c:if>


<nav>
	<a href ="${ pageScope.urlHome }"><h1>로고</h1></a>
	<ul style="float:right; margin-right:30px;">
		<li><a href="${pageScope.url1 }">공연정보</a></li>
		<li><a href="${pageScope.url2 }">예매</a></li>
		<li><a href="${pageScope.url3 }">마이페이지</a></li>
		<li><span id="loginm">${requestScope.member.mId } 님 환영합니다</span></li>
		<li><a href="${pageScope.urlLogin }" id="link"><span id="loginm2">로그아웃</span></a><li>
	</ul>
</nav>
