<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="domain.performance.PerformanceVO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>공연상세조회</title>

	<style>
	
	form {
	
		padding : 30px;
		background-color : gray;
		width: 900px;
		height: 750px;
	
	}
	
	button {
	
		width: 80px;
		height: 40px;
		margin-right: 50px;

	}

	
	#pannel > h2 {
		
		display: inline-block;
		margin-right: 10px;
	}

	
	#div1{
		
		margin-bottom: 20px;
		text-align: left;
				
	}
	
	#div1 > #btn2 {
		
		margin-left: 10px;
	
	}
	
	
	#div2{
		
		margin-top:20px;
		text-align: left;
		
	}
	
	#schedule{
		
		 width: 180px; height:200px;
   		 display: block;
   		 overflow: auto;
   		 float:right;;
   		 margin-right:150px;
   		 margin-bottom:20px;
	}
		
	}
	
	#div2 {
		float: right;
		text-align: center;
	}
	
	#div3 {
		text-align: center;
	}
	
	a { 
		display:inline-block; 
		text-align:center;
		 vertical-align:middle; 
		 text-decoration:none; 
		 font-size:12px; color:#000; 
		 border:1px solid #000; 
		 width:118px; height:38px;
		  line-height:38px; 
		  }

	</style>

</head>
<body>
	<form > 
	
		<div id="pannel">
		<h1>공연상세조회</h1>
		</div>
		<div>
			<%-- <c:forEach var ="poster" items="${requestScope.performance.posters}">
				<img src="C:\eclipse\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\performanceWebApp\upload/${pageScope.poster.systemFileName}">
			</c:forEach> --%>
		</div>
		<div id="div2">
		<table id="schedule" border="1"  >
			<tr>
				<th>날짜</th>
				<th>공연시간</th>
			</tr>
		<c:forEach var="schedule" items="${requestScope.performance.schedules }" varStatus="loop">
			<c:forEach var="order" items="${pageScope.schedule.orders}" varStatus="loop">
			
			<tr>
				<td>${pageScope.schedule.sDate }</td>
				<td>${pageScope.order.oTime }</td>
			</tr>	
			</c:forEach>
			</c:forEach>
		</table>
		</div>
		
		
		<div id="div1">
		공연번호 : ${requestScope.performance.pNo }<br><br>
		공연제목 : ${requestScope.performance.title }
		</div>
		<div id="div3">
		<table border="1" width=670>
			<tr>
				<th>동영상</th>
				<td>${requestScope.performance.video }</td>
				<th>가격</th>
				<td>${requestScope.performance.price }</td>
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
				<td>${requestScope.performance.runningTime }</td>
				<th>비고</th>
				<td>${requestScope.performance.note }</td>
			</tr>
		</table>
		</div>
		<br>
		
		<c:url var="removeURL" value="/admin_p_removePerformance.do" scope="page">
			<c:param name="pNo" value="${requestScope.performance.pNo}" />
		</c:url>
		<a href="${pageScope.removeURL}">삭제</a>&nbsp;
		
		<c:url var="modifyURL" value="/admin_p_modifyPerformanceForm.do" scope="page">
			<c:param name="pNo" value="${requestScope.performance.pNo}" />
		</c:url>
		<a href="${pageScope.modifyURL}">수정</a>&nbsp;
	
	</form>
</body>
</html>