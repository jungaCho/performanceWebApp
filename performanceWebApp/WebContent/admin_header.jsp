<%-- header.jsp --%>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>


<c:url var="url1" value="/processMemberList.do" scope="page"/>	
<c:url var="url2" value="/admin_layout.jsp" scope="page"/>	
<c:url var="url3" value="/admin_p_selectPerformanceList.do" scope="page" />
<c:url var="url4" value="/admin_m_login.jsp" scope="page"/>
<c:url var="urlHome" value="/admin_index.jsp" scope="page" />
<script src=js/jquery-3.2.1.min.js></script>
<script>

$(document).ready(function () {
		var noSession = ${ empty sessionScope.aId};
		if(noSession) { 
			$('#loginm').text("로그인이 필요합니다");	
			$('#manage1, #manage2, #manage3').click(function () {
				alert("로그인이 필요한 서비스입니다.");
				event.preventDefault();
			});
			
			$('#loginm2').text("로그인").unwrap().wrap('<a href="${pageScope.url4}"></a>'); 
		}
	});
</script>
<nav>
	<a href ="${pageScope.urlHome }"><h1>로고</h1></a>
	<ul style="float:right; margin-right:30px;">
		<li id="manage1"><a href="${pageScope.url1 }">회원관리</a></li>
		<li id="manage3"><a href="#">예매관리</a></li>
		<li id="manage2"><a href="${pageScope.url3 }">공연관리</a></li>
		<li><span id="loginm">${sessionScope.aId} 님 환영합니다</span></li>
		<li><a href="#"><span id="loginm2">로그아웃</span></a><li>
	</ul>
</nav>
