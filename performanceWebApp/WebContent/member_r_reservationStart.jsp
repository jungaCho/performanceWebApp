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
	width: 200px;
	margin-left: 20px;
	margin-top:15px;
}


#closeBtn{width:80px; background:gray; color:#fff; display:inline-block; height:50px; text-align:center; line-height:50px; text-decoration:none; border-radius:5px;}
#selectBtn{width:80px; background:#368AFF; color:#fff; display:inline-block; height:50px; text-align:center; line-height:50px; text-decoration:none; border-radius:5px;}
a:hover{color:#fff;}
</style>
<script src="js/jquery-3.2.1.min.js"></script> 
<script type="text/javascript">
	$(document).ready(function(){
		$('#closeBtn').click(function(){
			close();
		});
	});


</script>
</head>
<body>
	<!-- 예매페이지 -->
	<form action="" method="get">
		<div class="title_bg">예 매</div>
		<div class="reservation_padding">
			<div class="reservation_content01">
				<dl>
					<dt>포스터</dt>
					<dd class="reservation_title">공연명</dd>
					<dd class="reservation_text">※만석일 경우, 공연시간이 선택되지 않습니다.</dd>
				</dl>
			</div>
			<div class="reservation_content02">
				<table>
					<tr>
						<td>공연장소</td>
						<td>4관(8층)</td>
					</tr>
					<tr>
						<td>금 액</td>
						<td>30,000원</td>
					</tr>
					<tr>
						<td>공연일</td>
						<td><select name="performanceDate">
								<option value="firstDate">2018/01/01</option>
						</select></td>
					</tr>
					<tr>
						<td>공연시간</td>
						<td><select name="performanceTime">
								<option value="firstTime">09:00-12:00</option>
						</select></td>
					</tr>
					<tr>
						<td><a href="#" id="closeBtn">닫기</a></td>
						<td><a href="${pageContext.request.contextPath}/member_r_reservationStart2.jsp" id="selectBtn">좌석선택</a></td>
					</tr>
				</table>
			</div>
		</div>
	</form>
</body>
</html>