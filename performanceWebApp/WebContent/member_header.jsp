<%-- header.jsp --%>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>


<c:url var="url1" value="/member_p_selectPerformance.do" scope="page" />


<c:url var="url2" value="/member_r_layout.do" scope="page" />


<c:url var="url3" value="/checkMemberForm.do" scope="page"/>

<c:url var="urlHome" value="/mem_mainTestjsp.jsp" scope="page">
	<c:param name="article" value="member_welcome" />
</c:url>

<c:url var="urlLogin" value="/loginForm.do" scope="page"/>

<c:url var="urlLogout" value="/logout.do" scope="page">
	<c:param name="article" value="member_welcome" />
</c:url>

<script src=js/jquery-3.2.1.min.js></script>
<script>

	$(document).ready(function () {
		var noSession = ${ empty sessionScope.member};
		if(noSession) { 
			$('#loginm').text("로그인이 필요합니다");	
			$('#mypage, #reservation').click(function () {
				alert("로그인이 필요한 서비스입니다.");
				event.preventDefault();
			});
			$('#loginm2').text("로그인").unwrap().wrap('<a href="${pageScope.urlLogin}"></a>'); 
		}
	});
 
</script>

<nav>
	<a href ="${ pageScope.urlHome }"><h1><img src="upload/logo.png" width=80px height=80px></h1></a>
	<ul style="float:right; margin-right:30px; margin-top:30px;">
		<li><a href="${pageScope.url1 }">공연정보</a></li>
		<li><a href="${pageScope.url2 }" id="reservation">예매</a></li>
		<li><a href="${pageScope.url3 }" id="mypage">마이페이지</a></li>
		<li><span id="loginm">${sessionScope.member.mId }님 환영합니다</span></li>
		<li><a href="${pageScope.urlLogout }"><span id="loginm2">로그아웃</span></a></li>
	</ul>
</nav>
