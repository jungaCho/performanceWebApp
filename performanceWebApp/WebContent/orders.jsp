<%@ page contentType="text/plain; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

[

<c:forEach var="orders"
	items="${requestScope.orders}" varStatus="loop"> 	
	{
		"oTime" : "${pageScope.orders.oTime}",
		"oNo" : "${pageScope.orders.oNo}"
	}


	<c:if test="${loop.index < fn:length(requestScope.orders) -1 }">
	,
	</c:if>

</c:forEach>

]
