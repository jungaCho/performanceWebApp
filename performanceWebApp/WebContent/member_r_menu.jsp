<%-- member_menu.jsp --%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>



<ul>
	<li>예매</li><br>
	<li><a style="color:#f25c5c" href="${pageScope.url1 }">예매</a></li>
	<li><a style="color:#a69bd4" href="${pageContext.request.contextPath}/totalInfoRetrieveList.do">예매내역조회</a></li>
</ul>