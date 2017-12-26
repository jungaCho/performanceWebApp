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
				<input id="keyword" type="search" placeholder="검색어를 입력하세요" size=60>
				<button id="btn1" type="submit">검색</button>
			</div>
		</div>
		<br> <br>
		<div id="div1">
			<div>
				<c:forEach var="poster" items="${requestScope.performance.posters}">
					<img
						src="C:/eclipse/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/performanceWebApp/upload/${pageScope.poster.systemFileName}"
						alt="사진">
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
				<tr>
					<th>상세설명${pageScope.loop.count }</th>
					<td><a href="${pageScope.url }">${pageScope.detailFile.originalFileName }</a></td>
				</tr>
			</table>
		</div>
	</form>
</body>
</html>