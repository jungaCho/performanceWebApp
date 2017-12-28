<%-- performanceList.jsp --%>

<%@ page language="java" contentType="text/html; charset=EUC-KR"
        pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

        [

<c:forEach var="sortedPerf" items= "${requestScope.perfomances}" varStatus= "loop">

        {

        "mNo" : "${pageScope.sortedPerf.pNo }"
        ,

        "title" : "${pageScope.sortedPerf.title }"
        ,

        "video" : "${pageScope.sortedPerf.video }"
        ,

        "startDate" : "${pageScope.sortedPerf.startDate }"
        ,

        "endDate" : "${pageScope.sortedPerf.endDate }"
        ,


        "production" : "${pageScope.sortedPerf.production }"
        ,

        "contactName" : "${pageScope.sortedPerf.contactName }"
        ,
        "contactNumber" : "${pageScope.sortedPerf.contactNumber }"
        ,
        "runningTime" : "${pageScope.sortedPerf.runningTime }"
        ,
        "note" : "${pageScope.sortedPerf.note }"
        ,
        "price" : "${pageScope.sortedPerf.price }"
        ,
        "viewNo" : "${pageScope.sortedPerf.viewNo }"
        ,
        "viewClass" : "${pageScope.sortedPerf.viewClass }"
        ,
        "genreNo" : "${pageScope.sortedPerf.genreNo }"
        ,
        "viewNo" : "${pageScope.sortedPerf.viewNo }"
        ,
        "genre" : "${pageScope.sortedPerf.genre }"
        ,
        "tNo" : "${pageScope.sortedPerf.tNo }"
        ,
        "tName" : "${pageScope.sortedPerf.tName }"

        }

<c:if test="${loop.index < fn:length(requestScope.perfomances) - 1}">
        ,
</c:if>

</c:forEach>

        ]