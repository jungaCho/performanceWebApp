<%@page contentType="text/html; charset=utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset=utf-8>
<title>공연 상세조회 페이지</title>
<style>
form {
	padding: 30px;
	background-color: gray;
	width: 800px;
	height: 750px;
}

li {
	display: inline-block;
	margin: 5px 50px;
}

a {
	text-decoration: none;
}

#div2 {
	overflow: hidden;
}

#div2-1 {
	margin: 15px 150px;
	text-align: center;
	float: left;
	width: 65%;
}

#div2-2 {
	float: right;
	width: 35%;
	font-size: 15px;
}

#div2-2>ul {
	overflow: hidden;
}

#div2-2>ul li {
	display: inline-block;
	float: left;
}

#div3 {
	float: right;
	text-align: center;
}

#btn2 {
	width: 80px;
}

#div1 {
	float: left;
	margin-left: 45px;
	text-align: center;
}

#div4 {
	text-align: center;
}
</style>
</head>
<body>
	<form>
		<div id="div2">
			<div id="div2-1">
				<input id="keyword" type="text" name="keyword" placeholder="검색어를 입력하세요" size=60>
				<button id="btn1" type="button">검색</button>
			</div>
		</div>
		<br> <br>
		<div id="div1">
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

			<button type="button" id="btn2" type="button">예매하기</button>

		</div>
		<div id="div3">
			<table id="detailPerformance" border="1" width=600>
				<tr>
					<td colspan='2'>${requestScope.performance.title }</td>
				<tr>
					<th>기간</th>
					<td>${requestScope.performance.startDate }~
						${requestScope.performance.endDate }</td>
				</tr>
				<tr>
					<th>시간</th>
					<td>${requestScope.performance.oTime }</td>
				</tr>
				<tr>
					<th>장소</th>
					<td>${requestScope.performance.tName }</td>
				</tr>
				<tr>
					<th>관람등급</th>
					<td>${requestScope.performance.viewClass }</td>
				</tr>
				<tr>
					<th>관람시간</th>
					<td>${requestScope.performance.runningTime }</td>
				</tr>
				<tr>
					<th>장르</th>
					<td>${requestScope.performance.genre }</td>
				</tr>
				<tr>
					<th>가격</th>
					<td>${requestScope.performance.price }원</td>
				</tr>
			</table>
			<br>
		</div>

		<br>
		<hr width="800" align="center" color="black" size="1">
		<br>

		<div id="div4">
			<table id="detailFile" border="1" width=800>
				<tr>
					<th>할인정보</th>
					<td>할인정보</td>
				</tr>
			</table>
		</div>
		<div id="div4">
			<table id="detailFile" border="1" width=800>
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
	</form>
</body>
</html>