<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.*" %>
<%@ page import="domain.performance.PerformanceVO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>공연 검색</title>

<style>
form {
	padding: 30px;
	background-color: gray;
	width: 900px;
	height: 750px;
}

#btn1 {
	width: 70px;
	height: 30px;
	margin-right: 10px;
}

#btn2 {
	width: 70px;
	height: 30px;
	margin-right: 10px;
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

#search {
	float: right;
	margin-right: 100px;
}

#schedule {
	float: right;
	margin-right: 235px;
}

#div3{
		float:left;
		text-align:center;
	}
</style>

</head>
<body>
	<form>

		<div id="pannel">
			<h1>공연 조회</h1>
			<button type="button" id="btn1">선택삭제</button>
			<button type="button" id="btn2">선택해제</button>
		</div>

		<div id="search">
			<select id="keyfield" name="keyfield">
						<option value="제목">제목</option>
						<option value="기간">기간</option>
						<option value="장르">장르</option>
					</select>
			<input id="keyword" type="search" placeholder="검색어를 입력하세요">
			<button type="search" id="btn3">검색</button>
		</div>
		<br>
		<br>
		<div id="div3">
		<table border="1" width=670>
			<tr>
				<th>선택</th>
				<th>공연번호</th>
				<th>공연제목</th>
				<th>시작일</th>
				<th>종료일</th>
				<th>장르</th>
			</tr>
		<c:forEach var="performance" items="${requestScope.performances }" varStatus="loop">
			<c:url var="url" value="/admin_p_detailPerformance.jsp" scope="page">
				<c:param name="no" value="${pageScope.performance.pNo }" />
			</c:url>
			<tr>
				<td><input type="checkbox" name="check"></td>
				<td>${pageScope.performance.pNo }</td>
				<td><a href="${pageScope.url }">${pageScope.performance.title }</a></td>
				<td>${pageScope.performance.startDate }</td>
				<td>${pageScope.performance.endDate }</td>
				<td>${pageScope.performance.genre }</td>
			</tr>
		</c:forEach>
		</table>
		</div>
		<br>
	</form>
</body>
</html>