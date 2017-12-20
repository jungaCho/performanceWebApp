<%@ page contentType="text/html; charset=utf-8"%>
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
	width: 600px;
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
	width: 290px;
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

.reservation_content02 {
	width: 240px;
	margin-left: 20px;
	margin-top:15px;
}


#closeBtn{width:80px; background:gray; color:#fff; display:inline-block; height:50px; text-align:center; line-height:50px; text-decoration:none; border-radius:5px;}
#selectBtn{width:80px; background:#368AFF; color:#fff; display:inline-block; height:50px; text-align:center; line-height:50px; text-decoration:none; border-radius:5px;}
a:hover{color:#fff;}
</style>

</head>
<body>
	<!-- 예매페이지 -->
	<form action="" method="get">
		<div class="title_bg">결 제 정 보</div>
		<div class="reservation_padding">
			<div class="reservation_content01">
				<dl>
					<dt></dt>
					<dd class="reservation_text">※최대 10자리까지 예매 가능합니다.</dd>
				</dl>
			</div>
			<div class="reservation_content02">
				<table>
					<tr>
						<td>선택한 좌석번호</td>
						<td>K4,K5</td>
					</tr>
					<tr>
						<td>총 금 액</td>
						<td>60,000원</td>
					</tr>
					<tr>
						<td>할인율</td>
						<td>5% [실버등급]</td>
					</tr>
					<tr>
						<td>최종 결제금액</td>
						<td>57,000원</td>
					</tr>
					<tr>
						<td><a href="${pageContext.request.contextPath}/member_r_reservationStart.jsp" id="closeBtn">뒤로가기</a></td>
						<td><a href="${pageContext.request.contextPath}/member_r_reservationStart3.jsp" id="selectBtn">결제하기</a></td>
					</tr>
				</table>
			</div>
		</div>
	</form>
</body>
</html>