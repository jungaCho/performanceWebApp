<%@page contentType="text/plain; charset=utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions"%>
[
	<c:forEach var="detailFile" items="${requestScope.DetailFiles}"  varStatus="loop">
	
	{
		"systemFileName" :${pageScope.detailFile.systemFileName} ,
		"fileSize": "${pageScope.detailFile.fileSize}",
		
	}
	<c:if test="${loop.index < fn:length(requestScope.articles)-1}">
		,
	</c:if>
	</c:forEach>
]
