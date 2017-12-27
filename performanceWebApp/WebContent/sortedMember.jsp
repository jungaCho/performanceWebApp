<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 
 [
 
 	<c:forEach var="sortedMember" items= "${requestScope.sortedMembers}" varStatus= "loop">
 	
 	{
 	
 	"mNo" : ${pageScope.sortedMember.mNo }
 	,
 	
 	"mName" : "${pageScope.sortedMember.mName }"
 	,
 	
 	"mId" : "${pageScope.sortedMember.mId }"
 	,
 	
 	"mPw" : "${pageScope.sortedMember.mPw }"
 	,
 	
 	"email" : "${pageScope.sortedMember.email }"
 	,
 	
 	"address" : "${pageScope.sortedMember.address }"
 	,
 	
 	"rankNo" : "${pageScope.sortedMember.rankNo }"
 
 		
 	}
 	
 	<c:if test="${loop.index < fn:length(requestScope.sortedMembers) - 1}">
 	,
 	</c:if>
 	
 	</c:forEach>
 
]