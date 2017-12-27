<%@page contentType="text/html; charset=utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset=utf-8>
<title>공연 이미지 조회 페이지</title>
<style>
form {
	padding: 30px;
	color: gray;
	width: 100%;
	height: 750px;
}

#div1 {
	width: 100%;
	height: 35px;
	background-color: yellow;
	text-align: center;
}

li {
	display: inline-block;
	margin: 5px 10px;
	color:black;
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
	text-align: center;
}

#keyword {margin-le
	
}
</style>
</head>
<body>
	<form>
		<div id="div1">
			<ul>
				<li><a href='#'>1월</a></li>
				<li><a href='#'>2월</a></li>
				<li><a href='#'>3월</a></li>
				<li><a href='#'>4월</a></li>
				<li><a href='#'>5월</a></li>
				<li><a href='#'>6월</a></li>
				<li><a href='#'>7월</a></li>
				<li><a href='#'>8월</a></li>
				<li><a href='#'>9월</a></li>
				<li><a href='#'>10월</a></li>
				<li><a href='#'>11월</a></li>
				<li><a href='#'>12월</a></li>
			</ul>
		</div>
		<div id="div2">
			<div id="div2-1">
				<input id="keyword" type="text" placeholder="검색어를 입력하세요">
				<button id="btn1" type="submit">검색</button>
			</div>
			<div id="div2-2">
				<button type="button">이미지보기</button>
				<button type="button">텍스트보기</button>
				<select id="genre">
					<option value="G002">뮤지컬</option>
					<option value="G001">연극</option>
					<option value="G003">콘서트</option>
				</select>
			</div>
		</div>
		<br> <br>
		<div id="div3">
			<table border="1" width=180>
				<tr>
					<td>${pageScope.performance.sDate }</td>
					<td><button type="submit" id="btn2">예매하기</button>
				</tr>
				<tr>
					<c:forEach var="performance" items="${requestScope.performances }"
						varStatus="loop">
						<c:url var="url" value="/member_p_detailPerformance.do"
							scope="page">
							<c:param name="pNo" value="${pageScope.performance.pNo }" />
						</c:url>
						<c:forEach var="poster"
							items="${requestScope.performance.posters}">
							<td colspan='2'><a href="${pageScope.url }"><img
									src="C:/eclipse/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/performanceWebApp/upload/${pageScope.poster.systemFileName}"
									alt="사진"></td>
						</c:forEach>
					</c:forEach>
				</tr>
				<tr>
					<c:forEach var="performance" items="${requestScope.performances }"
						varStatus="loop">
						<c:url var="url" value="/member_p_detailPerformance.do"
							scope="page">
							<c:param name="pNo" value="${pageScope.performance.pNo }" />
						</c:url>
						<td><a href="${pageScope.url }">${pageScope.performance.title }</td>
					</c:forEach>
				</tr>
			</table>
		</div>
	</form>
</body>
</html>