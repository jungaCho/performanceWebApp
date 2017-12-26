<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html >
<html>
<head>
<meta charset="utf-8">
<title>예매 정보</title>
<style>
.reservationList {
	margin: 30px;
}

.r_content1 {
	overflow: hidden;
}

.r_content1>.r_content1_sub1 {
	float: left;
}

.r_content1>.r_content1_sub2 {
	float: right;
}

.r_content1_title {
	padding-top: 50px;
}

.r_content1_title>dt {
	text-align: center;
	font-weight: bold;
}

.r_content1_title>dd {
	text-align: center;
	margin-top: 20px;
	color: #5D5D5D;
	font-size: 18px;
}

.r_content1_sub2 {
	width: 200px;
}

.r_content1_sub2_img {
	height: 250px;
	background: #000;
}

.r_content1_sub2_title {
	text-align: center;
	color: #fff;
	background: #5D5D5D;
	width: 200px;
	margin: 0 auto;
	font-size: 15px;
	height: 30px;
	line-height: 30px;
}

.r_info {
	float: left;
	width: 100%;
	font-size: 16px;
}

.r_content2 {
	overflow: hidden;
	border-top: 2px solid #5d5d5d;
	margin-top: 15px;
}

.r_content2_table {
	float: left;
	border: 1px solid #fff;
	text-align: center;
	width: 75%;
	margin-top: 15px;
	font-size: 18px;
}

.r_content2_table tr {
	height: 30px;
	line-height: 30px;
	background: #D5D5D5;
	color: #4C4C4C;
}

.r_content2_table_bg1 {
	background: #5d5d5d !important;
	color: #fff !important;
}

.r_button2 {
	float: right;
	width: 170px;
	height: 50px;
	margin-top: 80px;
	background: #2478FF;
	text-align: center;
}

.r_button2>a {
	line-height: 50px;
	text-underline: none;
	text-decoration: none;
	font-size: 15px;
}

.r_button2>a:hover {
	color: #fff;
}
</style>
</head>
<body>
		<div class="reservationList">
			<div class="r_content1">
				<div class="r_content1_sub1">
					<dl class="r_content1_title">
						<dt>
							공연을 예매해 주셔서 진심으로 감사드립니다!<br> 2017년 11월 20일 예매가 정상적으로 이루어
							졌습니다.<br> 아래 예매내역을 확인해 주세요.
						</dt>
						<dd>예매번호 : ${requestScope.reservation.rNo}</dd>
					</dl>
				</div>
				<div class="r_content1_sub2">
					<dl>
						<dt class="r_content1_sub2_img">포스터</dt>
						<dd class="r_content1_sub2_title">${param.title}</dd>
					</dl>
				</div>
				<div class="r_info">▣ 예매정보</div>
			</div>
			<div class="r_content2">
				<table class="r_content2_table">
					<tr class="r_content2_table_bg1">
						<th>총 결제 금액</th>
						<th>${param.totalPrice}</th>
					</tr>
					<tr>
						<td>공 연 일</td>
						<td>${param.sDate }</td>
					</tr>
					<tr>
						<td>공 연 시 간</td>
						<td>${param.oTime }</td>
					</tr>
					<tr>
						<td>좌 석 정 보</td>
						<td>${param.selectTd }</td>
					</tr>
					<tr>
						<td>신용카드 승인번호</td>
						<td>${requestScope.reservation.approveNumber}</td>
					</tr>
				</table>
				<div class="r_button2">
					<a href="#">예매내역 확인</a>
				</div>
			</div>
		</div>
</body>
</html>