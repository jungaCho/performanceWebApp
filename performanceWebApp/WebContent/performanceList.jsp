///performanceList.jsp

<%@ page language="java" contentType="text/html; charset=EUC-KR"
        pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core";; %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions";; %>

        [

<c:forEach var="sortedMember" items= "${requestScope.perfomances}" varStatus= "loop">

        {

        "mNo" : ${pageScope.perfomances.pNo }
        ,

        "title" : "${pageScope.perfomances.title }"
        ,

        "video" : "${pageScope.perfomances.video }"
        ,

        "startDate" : "${pageScope.perfomances.startDate }"
        ,

        "endDate" : "${pageScope.perfomances.endDate }"
        ,

        "production" : "${pageScope.perfomances.production }"
        ,

        "contactName" : "${pageScope.perfomances.contactName }"
        ,
        "contactNumber" : "${pageScope.perfomances.contactNumber }"
        ,
        "runningTime" : "${pageScope.perfomances.runningTime }"
        ,
        "note" : "${pageScope.perfomances.note }"
        ,
        "price" : "${pageScope.perfomances.price }"
        ,
        "viewNo" : "${pageScope.perfomances.viewNo }"
        ,
        "viewClass" : "${pageScope.perfomances.viewClass }"
        ,
        "genreNo" : "${pageScope.perfomances.genreNo }"
        ,
        "viewNo" : "${pageScope.perfomances.viewNo }"
        ,
        "genre" : "${pageScope.perfomances.genre }"
        ,
        "tNo" : "${pageScope.perfomances.tNo }"
        ,
        "tName" : "${pageScope.perfomances.tName }"

        }

<c:if test="${loop.index < fn:length(requestScope.sortedMembers) - 1}">
        ,
</c:if>

</c:forEach>

        ]