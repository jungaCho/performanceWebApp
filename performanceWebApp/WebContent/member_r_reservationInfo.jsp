<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>
<meta charset="utf-8">
<title>예매</title>
<style>
.wrapper{
margin-left:50px; margin-right:50px;
}

.reservation {

	margin-top: 10px;

}

table{border:1px solid gray; text-align:center; font-size:12px; margin-top:50px; margin-bottom:15px; width:100%}
th{background:#FFBB00; color:#000;}
tr{height:40px;}
td{width:90px;}
.selectbox{float:right; margin-bottom:20px;}
}
</style>
<script src="js/jquery-3.2.1.min.js"></script> 
<script type="text/javascript">
	/* $(document).ready(function(){
		$('#open').click(function(){
			var url = '${pageContext.request.contextPath}/member_r_reservationStart.do?pNo=P00001';
			window.open(url, "예매확인", "width=700, height=600");
		});
	}); */
</script>
</head>

<body>
	<form>
		<div class="wrapper">
			<h3>예매</h3>
			<span style="font-size: 17px;">예매 내역</span> <br>
		
			<div class="reservation">
				<form id="form">
				<div class="selectbox">
					<select>
						<option value="title">공연명</option>
						<option value="sDate">공연날짜</option>
						<option value="rStatus">상태</option>
					</select>
					<input type="text" name="keyword" size="20"><button type="button" id="sendBtn">검색</button>
				</div>

				<table cellspacing="0" cellpadding="0">
					<tr>
						<th>예매번호</th>
						<th>예매일자</th>
						<th>공연명</th>
						<th>공연날짜</th>
						<th>매수</th>
						<th colspand="3">상태</th>
						<th></th>
						<th></th>
					</tr>
					
					<c:forEach var="totalInfo" items="${requestScope.totalInfos }" varStatus="loop">
					<tr>
						<td>${pageScope.totalInfo.rNo }</td>
						<td>${pageScope.totalInfo.rDate }</td>
						<td>${pageScope.totalInfo.title }</td>
						<td>${pageScope.totalInfo.sDate }</td>
						<td>2</td>
						<td>${pageScope.totalInfo.rStatus }</td>
						<td><button type="submit">상세보기</button></td>
						<td><button type="submit">예약취소</button></td>
					</tr>
					</c:forEach>
				</table>
				<br>
			<br>
				
				<c:if test="${requestScope.paging.prevPage > 0 }">
					<c:url var="prevUrl" value="/totalInfoRetrieveList.do" scope="page">
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
						<c:url var="url" value="/totalInfoRetrieveList.do" scope="page">
							<c:param name="currentPage" value="${pageScope.i }"/>
						</c:url>
						<a href="${pageScope.url }">${pageScope.i }</a> &nbsp;&nbsp;
					</c:if>
				</c:forEach>
				
				<c:if test="${requestScope.paging.endPage < requestScope.paging.totalPage }">
					<c:url var="nextUrl" value="/totalInfoRetrieveList.do" scope="page">
						<c:param name="currentPage" value="${requestScope.paging.nextPage }"/>
					</c:url>
					<a href="${pageScope.nextUrl }">[다음]</a>&nbsp;&nbsp;
				</c:if>
				<c:if test="${requestScope.paging.endPage == requestScope.paging.totalPage }">
					[다음]&nbsp;&nbsp;
				</c:if>

				</form>
		
			</div>
		</div>
	</form>
</body>
</html>