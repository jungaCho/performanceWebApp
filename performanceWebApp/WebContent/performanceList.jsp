///performanceList.jsp

<%@ page language="java" contentType="text/html; charset=EUC-KR"
        pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

        [

<c:forEach var="sortedMember" items= "${requestScope.perfomances}" varStatus= "loop">

        {

        "mNo" : ${pageScope.sortedMember.pNo }
        ,

        "title" : "${pageScope.sortedMember.title }"
        ,

        "video" : "${pageScope.sortedMember.video }"
        ,

        "startDate" : "${pageScope.sortedMember.startDate }"
        ,

        "endDate" : "${pageScope.sortedMember.endDate }"
        ,

        "production" : "${pageScope.sortedMember.production }"
        ,

        "contactName" : "${pageScope.sortedMember.contactName }"
        ,
        "contactNumber" : "${pageScope.sortedMember.contactNumber }"
        ,
        "runningTime" : "${pageScope.sortedMember.runningTime }"
        ,
        "note" : "${pageScope.sortedMember.note }"
        ,
        "price" : "${pageScope.sortedMember.price }"
        ,
        "viewNo" : "${pageScope.sortedMember.viewNo }"
        ,
        "viewClass" : "${pageScope.sortedMember.viewClass }"
        ,
        "genreNo" : "${pageScope.sortedMember.genreNo }"
        ,
        "viewNo" : "${pageScope.sortedMember.viewNo }"
        ,
        "genre" : "${pageScope.sortedMember.genre }"
        ,
        "tNo" : "${pageScope.sortedMember.tNo }"
        ,
        "tName" : "${pageScope.sortedMember.tName }"

        }

<c:if test="${loop.index < fn:length(requestScope.sortedMembers) - 1}">
        ,
</c:if>

</c:forEach>

        ]