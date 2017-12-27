<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="domain.performance.PerformanceVO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>공연상세조회</title>

<style>
body {
	color: gray;
	padding-left: 50px;
}

form {
	padding: 30px;
	width: 900px;
	height: 750px;
}

div {
	padding-left: 50px;
}

#pannel>h2 {
	display: inline-block;
	margin-right: 10px;
}

#div1 {
	margin-bottom: 20px;
	text-align: left;
}

#div1>#btn2 {
	margin-left: 10px;
}

#div2 {
	margin-top: 20px;
	float: right;
	text-align: center;
}

#schedule {
	width: 200px;
	height: 200px;
	display: block;
	overflow: auto;
	float: right;;
	margin-right: 150px;
	margin-bottom: 20px;
}

#div3 {
	text-align: center;
}

#btn1 {
	width: 118px;
	height: 38px;
}

a#remove {
	display: inline-block;
	text-align: center;
	vertical-align: middle;
	text-decoration: none;
	font-size: 12px;
	color: #000;
	border: 1px solid #000;
	width: 118px;
	height: 38px;
	line-height: 38px;
	color: gray;
	background-color: #F8E0F1;
}

a#modify {
	display: inline-block;
	text-align: center;
	vertical-align: middle;
	text-decoration: none;
	font-size: 12px;
	color: #000;
	border: 1px solid #000;
	width: 118px;
	height: 38px;
	line-height: 38px;
	color: gray;
	background-color: #F8E0F1;
}

#poster {
	border: 1px solid;
	padding-left: 50px;
	heigth: 80px;
	width: 40px;
}
</style>

</head>
<body>
	<div id="pannel">
		<h1>공연상세조회</h1>
	</div>
	<div>
		<c:forEach var="poster" items="${requestScope.performance.posters}" >
			<c:if test="${pageScope.poster.mainPoster == 1}">
					<img src="${pageContext.request.contextPath}/upload/${pageScope.poster.systemFileName}" 
								width="100px" height="150px" id="img">
			</c:if>
			<c:if test="${pageScope.poster.mainPoster != 1}">
					<img src="${pageContext.request.contextPath}/upload/${pageScope.poster.systemFileName}" 
								width="50px" height="50px" id="img">
			</c:if>
		</c:forEach>
	</div>
	
	<div id="div2">
		<table id="schedule" border="1">
			<tr>
				<th>날짜</th>
				<th>공연&nbsp;시간</th>
			</tr>
			<c:forEach var="schedule"
				items="${requestScope.performance.schedules }" varStatus="loop">
				<c:forEach var="order" items="${pageScope.schedule.orders}"
					varStatus="loop">

					<tr>
						<td>${pageScope.schedule.sDate }</td>
						<td>${pageScope.order.oTime }</td>
					</tr>
				</c:forEach>
			</c:forEach>
		</table>
	</div>


	<div id="div1">
		공연번호 : ${requestScope.performance.pNo }
		<br> <br>
		공연제목 : ${requestScope.performance.title }
	</div>

	<div id="div3">
		<table border="1" width=670>
			<tr>
				<th>동영상</th>
				<td>${requestScope.performance.video }</td>
				<th>가격</th>
				<td>${requestScope.performance.price }원</td>
			</tr>
			<tr>
				<th>시작일</th>
				<td>${requestScope.performance.startDate }</td>
				<th>종료일</th>
				<td>${requestScope.performance.endDate }</td>
			</tr>
			<tr>
				<th>제작사</th>
				<td>${requestScope.performance.production }</td>
				<th>공연장소</th>
				<td>${requestScope.performance.tName }</td>
			</tr>
			<tr>
				<th>관람등급</th>
				<td>${requestScope.performance.viewClass }</td>
				<th>장르</th>
				<td>${requestScope.performance.genre }</td>
			</tr>
			<tr>
				<th>담당자</th>
				<td>${requestScope.performance.contactName }</td>
				<th>담당자 전화번호</th>
				<td>${requestScope.performance.contactNumber }</td>
			</tr>
			<tr>
				<th>런닝타임</th>
				<td>${requestScope.performance.runningTime }분</td>
				<th>비고</th>
				<td>${requestScope.performance.note }</td>
			</tr>
		</table>
		<br> <br>
	</div>
	<div id="div4">
			<table border="1">
				<c:forEach var="detailFile" items="${requestScope.detailFiles }" varStatus="loop">
	 				<c:url var="url" value="/downloadFile" scope="page">
	 					<c:param name="originalFileName" value="${pageScope.detailFile.originalFileName }" />
	 					<c:param name="systemFileName" value="${pageScope.detailFile.systemFileName }" />
	 				</c:url>
					<tr>
						<td>상세설명${pageScope.loop.count }</td>
						<td><a href="${pageScope.url }">${pageScope.detailFile.originalFileName }</a></td>
					</tr>
				</c:forEach>
			</table>
	</div>
	
	<br>

	<div>
		<c:url var="removeURL" value="/admin_p_removePerformance.do"
			scope="page">
			<c:param name="pNo" value="${requestScope.performance.pNo}" />
		</c:url>
		<a id="remove" href="${pageScope.removeURL}">삭제</a>&nbsp;


		<c:url var="modifyURL" value="/admin_p_modifyPerformanceForm.do"
			scope="page">
			<c:param name="pNo" value="${requestScope.performance.pNo}" />
		</c:url>
		<a id="modify" href="${pageScope.modifyURL}">수정</a>&nbsp;
	</div>
</body>
</html>