<%@ page contentType="text/plain; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

[
	<c:forEach var="totalInfo" items="${requestScope.totalInfos}"	varStatus="loop">
	
	{
	
		"rNo": "${pageScope.totalInfo.rNo }"
		,
		"rDate": "${pageScope.totalInfo.rDate }"
		,
		"title": "${pageScope.totalInfo.title }"
		,
		"sDate": "${pageScope.totalInfo.sDate }"
		,
		"rStatus": ${pageScope.totalInfo.rStatus }
		
	}
	 <c:if test="${loop.index < fn:length(requestScope.totalInfos) - 1  }">
     ,
     </c:if>

	</c:forEach>

]