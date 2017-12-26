<%@ page contentType="text/plain; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

[
	<c:forEach var="performance" items="${requestScope.performances}"	varStatus="loop">
	
	{
	
		"pNo": "${pageScope.performance.pNo }"
		,
		"title": "${pageScope.performance.title }"
		,
		"startDate": "${pageScope.performance.startDate }"
		,
		"endDate": "${pageScope.performance.endDate }"
		,
		"genre": "${pageScope.performance.genre }"
	}
	 <c:if test="${loop.index < fn:length(requestScope.performances) - 1  }">
     ,
     </c:if>

	</c:forEach>

]