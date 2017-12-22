<%@ page contentType="text/plain; charset=utf-8"%>
<%@ taglib	prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

[
	
	<c:forEach var="schedule" items="${requestScope.performance.schedules }" varStatus="loop">
		<c:forEach var="orders" items="${pageScope.schedule.orders}" > 	
	{
		"oTime" : "${pageScope.orders.oTime}"
	}
	<c:if test="${loop.index < fn:length(requestScope.performance.schedules.orders) -1 }">
	,
	</c:if>
		</c:forEach>
	</c:forEach>
]
