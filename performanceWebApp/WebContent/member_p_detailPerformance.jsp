<%@page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset=utf-8>
<title>공연 상세조회 페이지</title>
<style>
form {
	padding: 30px;
	color: gray;
	width: 800px;
	height: 100%;
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
	width: 100px;
	height: 35px;
}

#div1 {
	float: left;
	margin-left: 30px;
	text-align: center;
}

#div4 {
	text-align: center;
}
#detailPerformance {
	border-collapse:separate;
	border-spacing:0px;
	color:black;
	font:20px '돋움';
}

#detailPerformance th {
	
	background-color:#a69bd4;
	border-left:1px solid gray;
}
#btn2{
	margin-bottom:20px;
	margin-top:15px;
}
#titlee{
	background-color:#a69bd4;
}

</style>
<script src="js/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
       $(document).ready(function() {
              $('#btn2').click(function() {
            	  	var p_no = '${requestScope.performance.pNo}';
            	  	var url = '${pageContext.request.contextPath}/member_r_reservationStart.do?pNo=' + p_no;
            	  	window.open(url, "예매확인","width=700, height=600");
              });
       });
</script>
</head>
<body>
	<form>
		<h1 id='strong'><strong>공연 상세정보</strong></h1>
		<br> 
		<div id="div1">
			<c:forEach var="poster" items="${requestScope.performance.posters}" >
				<c:if test="${pageScope.poster.mainPoster == 1}">
					<img src="${pageContext.request.contextPath}/upload/${pageScope.poster.systemFileName}" 
									width="220" height="270px" id="img">
				</c:if>
				<br>
				<c:if test="${pageScope.poster.mainPoster != 1}">
					<img src="${pageContext.request.contextPath}/upload/${pageScope.poster.systemFileName}" 
									width="50px" height="50px" id="img">
				</c:if>
			</c:forEach>
			<br>
			<button type="button" id="btn2" type="button">예매하기</button>
		</div>

		<div id="div3">
			<table id="detailPerformance" width=500 height="270" cellspacing=0>
				<tr>
					<td id="titlee" colspan='2' style="border-left:1px solid gray">${requestScope.performance.title }</td>
				<tr>
					<th>기간</th>
					<td>${requestScope.performance.startDate }~
						${requestScope.performance.endDate }</td>
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
					<td>${requestScope.performance.runningTime }분</td>
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
		<hr id="hr" width="800" align="center" color="black" size="1">
		<br>
		
		<div id="div4">
			<h2>상세설명</h2>
				<div id="div5">
					<iframe width="640" height="360" src="${requestScope.performance.video }" 
					frameborder="0" gesture="media" allow="encrypted-media" allowfullscreen></iframe>
				</div><br><br>
				<c:forEach var="detailFile" items="${requestScope.detailFiles }" varStatus="loop">
	 				<c:url var="url" value="/downloadFile" scope="page">
	 					<c:param name="originalFileName" value="${pageScope.detailFile.originalFileName }" />
	 					<c:param name="systemFileName" value="${pageScope.detailFile.systemFileName }" />
	 				</c:url>
					<img src="${pageContext.request.contextPath}/upload/${pageScope.detailFile.systemFileName}" 
									width="100%" height="100%" id="img">
					
				</c:forEach>
			
		</div>
	</form>
</body>
</html>