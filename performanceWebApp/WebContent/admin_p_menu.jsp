<%-- admin_p_menu.jsp --%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>

<c:url var="url1" value="/admin_p_selectPerformanceList.do" scope="page"/>


<c:url var="url2" value="/uploadFile" scope="page"/>


<c:url var="url3" value="/admin_p_insertScheduleForm.do" scope="page"/>



<ul>
	<li>공연관리</li><br>
	<li><a style="color:#f25c5c" href="${pageScope.url1 }">공연조회</a></li>
	<li><a style="color:#a69bd4" href="${pageScope.url2 }">공연등록</a></li>
	<li><a style="color:#a69bd4" href="${pageScope.url3 }">일정등록</a></li>
</ul>