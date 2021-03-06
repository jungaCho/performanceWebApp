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


.wrap{padding:50px;}

select{font-size:15px; height:25px;}
#searchBtn{font-size:15px; height:25px; width:40px;}

table{border:1px solid gray; text-align:center; font-size:12px; margin-top:50px; margin-bottom:15px; width:100%}
th{background:#FFBB00; color:#000; line-height:40px;}
tr{height:40px; line-height:40px;}
td{width:90px; line-height:40px;}
.selectbox{float:right; margin-bottom:20px;}
.page{font-size:15px; text-align:center;}
</style>
<script src="js/jquery-3.2.1.min.js"></script> 
<script type="text/javascript">
	$(document).ready(function() {
		
		$('#table').on('click', '#detailView', function () {
			alert("call");
		});
		
		$('#table').on('click','#cancelReserved',function(event) {
			if($(this).parents('tr:first').find('#crs').text() != "예매") {
				alert("이미 예매취소된 내역입니다.");
	 			return false;
			} else {
				var rNo = $(this).parents('tr:first').attr('id');
				location.href="${pageContext.request.contextPath}/cancelReservationForm.do?rNo="+rNo;
			} 
		});		
		
		$('#searchBtn').on('click',function() {
			$.ajax ({
				url: '${pageContext.request.contextPath}/totalKeywordSearch.do'
				,
				method:'POST'
				,
				dataType:'json'
				,
				data: $('.selectbox').serialize()
				,
				success: function() {
					$('#table').find('tr:not(:first)').remove();
					var htmlStr ="";
					for(var i=0;i<data.length;i++) {
						htmlStr += "<tr>";
						htmlStr += "<td id='rNo'>"+data[i].rNo+"</td>";
						htmlStr += "<td>"+data[i].rDate+"</td>";
						htmlStr += "<td>"+data[i].title+"</td>";
						htmlStr += "<td>"+data[i].sDate+"</td>";
						htmlStr += "<td>"+data[i].seatNo+"</td>";
						htmlStr += "<td>"+data[i].rStatus+"</td>";
						htmlStr += "<td><button id='detailView' type='button'>상세보기</button></td>";
						htmlStr += "<td><button id='reservedCancel' type='button'>예약취소</button></td>";
						htmlStr += "<tr>";
						$(htmlStr).appendTo("#table");
						htmlStr += "";
					}
				}
				,
				error: function() {

				}
			});
		});
		
	});
	
</script>
</head>

<body>
		<div class="wrap">
			<h3>예매</h3><br>
			<span style="font-size: 17px;">예매 내역</span> <br>
		
			<div class="reservation">
				<form id="keyfieldBox">
				<div class="selectbox">
					<select name="keyfield">
						<option value="title">공연명</option>
						<option value="sDate">공연날짜</option>
						<option value="rStatus">상태</option>
					</select>
					<input type="text" name="keyword" size="20"><button type="button" id="searchBtn">검색</button>
				</div>
				</form>

				<table id="table" cellspacing="0" cellpadding="0">
					<tr>
						<th>예매번호</th>
						<th>예매일자</th>
						<th>공연명</th>
						<th>공연날짜</th>
						<th>매수</th>
						<th>상태</th>
						<th></th>
						<th></th>
					</tr>
					
					<c:forEach var="totalInfo" items="${requestScope.totalInfos }" varStatus="loop">
					<tr id="${pageScope.totalInfo.rNo }">
						<td>${pageScope.totalInfo.rNo }</td>
						<td>${pageScope.totalInfo.rDate }</td>
						<td>${pageScope.totalInfo.title }</td>
						<td>${pageScope.totalInfo.sDate }</td>
						<%-- <c:forEach var="rSeat" items="${requestScope.rSeats }" varStatus="loop"> --%>
						<td>예매수</td>
						<%-- </c:forEach> --%>
						<td id="status">
						<c:if test="${pageScope.totalInfo.rStatus == 0}">
							<span id="crs">예매취소</span>
						</c:if>
						<c:if test="${pageScope.totalInfo.rStatus == 1}">
							<span id="crs">예매</span>
						</c:if>						
						</td>
						<td><button id="detailView" type="button">상세보기</button></td>
						<td><button id="cancelReserved" type='button'>예약취소</button></td>
						<%-- <td id="cancelReserved"><a class="reservedCancel" id="${pageScope.totalInfo.rNo }">예약취소</a></td> --%>
					</tr>
					</c:forEach>
				</table>
				<br>
				<br>
				
				<div class="page">

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
				</div>
			</div>
		</div>
</body>
</html>