<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html >
<html>
<head>
<meta charset="utf-8">
<title>예매</title>
<style>
body {
	margin: 0;
	padding: 0;
	background: #ddd;
	overflow-x: hidden;
}

.title_bg {
	width: 700px;
	height: 40px;
	font-size: 25px;
	color: #fff;
	background: gray;
	margin: 0;
	padding: 0;
	text-align: center;
}

.reservation_padding {
	padding: 10px;
	overflow: hidden;
}

.reservation_content01 {
	float: left;
}

.reservation_content01>dl {
	width: 670px;
}

.reservation_content01>dl>dt {
	height: 340px;
	background: #fff;
}

.reservation_content02 {
	float: left;
}

.reservation_title {
	height: 30px;
	line-height: 25px;
	background: gray;
	text-align: center;
	font-size: 18px;
	color: #fff;
	width: 100%;
	margin: 0;
}

.reservation_text {
	font-size: 13px;
	text-align: center;
	margin: 0;
	width: 100%;
	margin-top: 5px;
}

table {
	width: 600px;
	margin: 0 auto;
	text-align: center;
}

#closeBtn {
	width: 80px;
	background: gray;
	color: #fff;
	display: inline-block;
	height: 50px;
	text-align: center;
	line-height: 50px;
	text-decoration: none;
	border-radius: 5px;
}

#selectBtn {
	width: 80px;
	background: #368AFF;
	color: #fff;
	display: inline-block;
	height: 50px;
	text-align: center;
	line-height: 50px;
	text-decoration: none;
	border-radius: 5px;
}

a:hover {
	color: #fff;
}

label {
	color:#fff;
	width: 22px;
	height: 22px;
	display: inline-block;
	margin:2px;
	font-size:8px;
	text-align:center;
	line-height:22px;
	background:#8C8C8C;
}

.button{text-align:center; margin-top:10px;}

.reservedSeat{background-color:blue;}

.seatPadding{padding: 10px; text-align:center;}
</style>
<script src="js/jquery-3.2.1.min.js"></script>
<script>

   	
	
	$(document).ready(function(){
		
		var  price = "${requestScope.performance.price}";
		console.log(price);
		var discount = "${sessionScope.member.rank.discount}";		
		var seatArray = new Array();		
	
		$('label').on('click', function() {
			var seatNo = $(this).text();
			var index = seatArray.indexOf(seatNo);
	
			if(index == -1) {
				
				$(this).css('backgroundColor','blue');
				seatArray.push(seatNo);
		
			} else {
				$(this).css('backgroundColor','gray');
				seatArray.splice(seatArray.indexOf(seatNo), 1);
			}
			
								
			$('#selectTd').text(seatArray.join());
			$('#sumPrice').text(seatArray.length * price);
			$('#disCount').text((seatArray.length * 0.05).toFixed(1));
			$('#totalPrice').text(((seatArray.length * 0.95).toFixed(1) * (seatArray.length * price)).toFixed(0));
			
		});
		
		
		$('#selectBtn').on('click', function() {
			var queryStr = 'pNo=${param.pNo }&tNo=${param.tNo}&oNo=${param.oNo}&totalPrice='+$('#totalPrice').text()+'&selectTd='+$('#selectTd').text();
			location.href="${pageContext.request.contextPath}/member_r_reservationStart3.do?"+queryStr;		
		
		});
		
	});
</script>

</head>
<body>
	<!-- 예매페이지 -->
	<form  method="get">
		<div class="title_bg">결 제 정 보</div>
		<div class="reservation_padding">
			<div class="reservation_content01">
				<dl>
					<dt class="seatPadding">
						<c:forEach var="seat" items="${requestScope.seats}">					
								<c:if test="${fn:length(requestScope.reservedSeats) > 0 }">
								<c:forEach var="reservedSeat" items="${requestScope.reservedSeats}">	
									<c:if test="${pageScope.seat.seatNumber == pageScope.reservedSeat.seatNumber}">
											<label class="reservedSeat">${pageScope.seat.seatNumber}</label>
									</c:if>
									<c:if test="${pageScope.seat.seatNumber != pageScope.reservedSeat.seatNumber}">
											<label>${pageScope.seat.seatNumber}</label>
									</c:if>														   
								</c:forEach>
								</c:if>
								<c:if test="${fn:length(requestScope.reservedSeats) ==  0 }">
									<label>${pageScope.seat.seatNumber}</label>		
								</c:if>																											
						</c:forEach>						
					</dt>
					<dd class="reservation_text">※최대 10자리까지 예매 가능합니다.</dd>
				</dl>
				<table>
					<tr>
						<td>선택한 좌석번호</td>
						<td id="selectTd"></td>
						<td>총 금 액</td>
						<td id="sumPrice"></td>
					</tr>
					
					<tr>
						<td>할인율</td>
						<td id="disCount"></td>
						<td>최종 결제금액</td>
						<td id="totalPrice"></td>
					</tr>
				</table>
				<div class="button">
					<c:url var="url" value="/member_r_reservationStart.do">
						<c:param name="pNo" value="${param.pNo }" />
					</c:url>
					<a href="${pageScope.url }" id="closeBtn">뒤로가기</a>
				
					<a  href="#"  id="selectBtn">결제하기</a>
				</div>
			</div>
		</div>
	</form>
</body>
</html>