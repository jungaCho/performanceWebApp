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
	padding: 20px;
	overflow: hidden;
}

.reservation_padding ul{overflow:hidden;}
.reservation_padding ul li{list-style:none; margin-top:15px;}

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

.ulText{font-size:20px; font-weight:bold;}
input {width:300px;}

.btnLeft{margin-left:35px;}

#cardCompany{height:25px;}
</style>
<script src="js/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
	
	$(document).ready(function() {
				
		$('#selectBtn').click(function(){	
			
			var select = 'pNo=${param.pNo}&tNo=${param.tNo}&oNo=${param.oNo}&totalPrice=${param.totalPrice}&selectTd=${param.selectTd}&cardNumber='+$('#cardNumber').val()+'&cardCoNo=' +$('#cardCoNo').find('option:selected').val()
							+'&oTime=${param.oTime}'+'&title=${param.title}&sDate=${param.sDate}&seatNo=${param.seatNo}';

			
			$(location).attr('href', '${pageContext.request.contextPath}/member_r_reservationStart4.do?'+select);
	
		}); 
		
 	
	});
</script>



</head>
<body>
	<!-- 예매페이지 -->
		<div class="title_bg">결 제 정 보</div>
		<div class="reservation_padding">
			<ul>
				<li class="ulText">▣ 결제정보 입력</li>
				<li><span>결제금액 : </span>&nbsp;&nbsp;<span>${param.totalPrice }</span></li>
				<li><span>카드번호 : </span>&nbsp;&nbsp;<input type="text" id="cardNumber" name="cardNumber">&nbsp;- 포함 작성</li>
				<li><span>카드사 : </span>&nbsp;&nbsp;
					<select id="cardCoNo" name="cardCoNo">
						<option value="1">국민</option>
						<option value="2">BC</option>
						<option value="3">농협</option>
						<option value="4">신한</option>
						<option value="5">우리</option>
					</select>
				</li>
			</ul>
			<div class="btnLeft">
			<c:url var="url" value="/member_r_reservationStart2.do">
					<c:param name="pNo" value="${param.pNo }" />
					<c:param name="tNo" value="${param.tNo}" />
					<c:param name="oNo" value="${param.oNo }"/>
			</c:url> 
			<a href="${pageScope.url }" id="closeBtn">뒤로가기</a> 
			<%-- <c:url var="url" value="/member_r_reservationStart4.do">
					<c:param name="pNo" value="${param.pNo }" />
					<c:param name="tNo" value="${param.tNo}" />
					<c:param name="oNo" value="${param.oNo }"/>
					<c:param name="totalPrice" value="${param.totalPrice }"/>
					<c:param name="selectTd" value="${param.selectTd }"/>
					<c:param name="cardNumber" value="${param.cardNumber }"/>
			</c:url>  --%>			
			<a href="#" id="selectBtn">결제하기</a>
			</div>
		</div>
</body>
</html>