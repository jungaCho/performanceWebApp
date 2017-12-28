<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.*"%>
<%@ page import="domain.performance.PerformanceVO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>공연 검색</title>
<style>
/* form {
	padding: 30px;
	background-color: gray;
	width: 900px;
	height: 750px;
}
 */
body {
	color: gray;
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

#div3 {
	float: left;
	text-align: center;
}

a {
	color: gray;
}

#page {
	margin-top: 290px;
	text-align:center;
	font-size:15px;
}

.wrapper{padding: 0 40px 10px 40px; width:1200px;}
#table{width:1100px; font-size:15px;}
tr{height:40px;}
td{border-bottom:1px solid gray; border-right:1px solid gray;}
th{border-bottom:1px solid gray; border-right:1px solid gray; background:#FFBB00; color:#fff;}

</style>
<script src="js/jquery-3.2.1.min.js"></script>
<script>
	$(document).ready(function() {
		
		$('#btn1').on("click",function(){
			
			//string[] 예매번호를 배열로 넘긴다.

			var rNoArrays = [];
			
			$('#checkSelect:checked').each(function(){
			
				rNoArrays.push($(this).val());		
			});	
			
			/* $('#remove').prepend("<input type='hidden' name='rNo' value='" + rNoArrays + "'>  "); */
			alert("선택한 공연이 삭제되었습니다!!");
			location.href= "${pageContext.request.contextPath}/remove.do?checked="+rNoArrays;
			
			
			
		});
		
		$('#btn2').click(function(){
        	$("input[name=check]").prop("checked",false);
        });
	
      });
</script>

</head>	
	<div class="wrapper">
	<div id="pannel">
		<h1>공연 조회</h1>
		
	<!-- 	<form action="remove.do" method="get"  id="remove"> -->
			<button type="button" id="btn1">선택삭제</button>
<!-- 		</form> -->
		<button type="button" id="btn2">선택해제</button>
	</div>


	<form id="search">
		<select id="keyfield" name="keyfield">
			<option value="title">예매일자</option>
			<option value="date">공연제목</option>
			<option value="genre">예매자</option>
			<option value="genre">공연날짜</option>
			<option value="genre">예매상태</option>
		</select> <input id="keyword" type="text" name="keyword" placeholder="검색어를 입력하세요">
		<button id="btn3" type="button">검색</button>
	</form>

	<br>
	<br>
	<div id="div3">

	<input type="hidden" value="${pageScope.totalInfos.rNo }">
		<table id="table" cellspacing=0>
			<tr>
				<th></th>
				<th style="border-left:1px solid gray">예매번호</th>
				<th>예매일자</th>
				<th>공연제목</th>
				<th>공연날짜</th>
				<th>예매매수</th>
				<th>상태</th>
				<th>회원번호</th>
				<th>예매자</th>
				<th>공연관</th>
				<th>좌석번호</th>
			</tr>
			<c:forEach var="totalInfos" items="${requestScope.totalInfos }" varStatus="loop">
				
				<tr>
					<td><input type="checkbox" name="check"  id="checkSelect" value="${pageScope.totalInfos.rNo }"></td>
					<td style="border-left:1px solid gray">${pageScope.totalInfos.rNo }</td>
					<td>${pageScope.totalInfos.rDate }</td>
					<td>${pageScope.totalInfos.title }</td>
					<td>${pageScope.totalInfos.sDate }</td>
					<td>2</td>
					<td>${pageScope.totalInfos.rStatus }</td>
					<td>${pageScope.totalInfos.mNo }</td>
					<td>${pageScope.totalInfos.mName }</td>
					<td>${pageScope.totalInfos.tName }</td>
					<c:forEach var="seats" items="${pageScope.totalInfos.seats }" varStatus="loop">
					<td>${pageScope.seats.seatNo }</td>
					</c:forEach>
				
				</tr>
			</c:forEach> 
		</table>	
	</div>
	<br>
	<br>
	&nbsp;

	<%-- 페이지 네비게이션 처리  --%>
       <form id="page">
              <c:if test="${requestScope.paging.prevPage > 0 }">
					<c:url var="prevUrl" value="/admin_r_layout.do" scope="page">
						<c:param name="currentPage" value="${requestScope.paging.prevPage }" />
					</c:url>
					<a href="${pageScope.prevUrl }">[이전]</a>&nbsp;&nbsp;
				</c:if>
				<c:if test="${requestScope.paging.prevPage < 1 }">
					[이전]&nbsp;&nbsp;
				</c:if>
				
				<c:forEach var="i" begin="${requestScope.paging.startPage }" end="${requestScope.paging.endPage }" step="1">
					<c:if test="${requestScope.paging.currentPage == pageScope.i }">
						${pageScope.i } &nbsp;&nbsp;
					</c:if>
					<c:if test="${requestScope.paging.currentPage != pageScope.i }">
						<c:url var="url" value="/admin_r_layout.do" scope="page">
							<c:param name="currentPage" value="${pageScope.i }"/>
						</c:url>
						<a href="${pageScope.url }">${pageScope.i }</a> &nbsp;&nbsp;
					</c:if>
				</c:forEach>
				
				<c:if test="${requestScope.paging.endPage < requestScope.paging.totalPage }">
					<c:url var="nextUrl" value="/admin_r_layout.do" scope="page">
						<c:param name="currentPage" value="${requestScope.paging.nextPage }"/>
					</c:url>
					<a href="${pageScope.nextUrl }">[다음]</a>&nbsp;&nbsp;
				</c:if>
				<c:if test="${requestScope.paging.endPage == requestScope.paging.totalPage }">
					[다음]&nbsp;&nbsp;
				</c:if>
       </form>

    </div>

</body>
</html>