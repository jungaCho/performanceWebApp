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
	overflow-x:hidden;
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
	width:100%;
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


.ulText{font-size:20px; font-weight:bold;}


.btnLeft{margin:0 auto; text-align:center; width:550px; margin-top:20px;}

table{width:550px; text-align:center; margin-top:30px;}
tr{line-height:25px; background:#D5D5D5; border-bottom:1px solid #ddd;}

.tr1{font-weight:bold; background:#fff;}
</style>
<script src="js/jquery-3.2.1.min.js"></script> 
<script>

	$(document).ready(function(){
		$('#selectBtn').click(function(){
			if(confirm("선택한 좌석 정보로 예약하시겠습니까?") == true){
				console.log("OK");
				self.close();
				$(opener.location).attr('href', '${pageContext.request.contextPath}/member_r_layout2.jsp');
			}else{
				return;
			}
		});
	});
</script>
</head>
<body>
	<!-- 예매페이지 -->
	<form method="get">
		<div class="title_bg">결 제 정 보</div>
		<div class="reservation_padding">
			<div class="ulText">▣ 최종 결제정보</div>
			<table cellpadding=0 cellspacing=0>
					<tr class="tr1">
						<td>총 결제금액</td>
						<td>60,000원</td>
					</tr>
					<tr>
						<td>공연명</td>
						<td>바람과 함께 사라지다</td>
					</tr>
					<tr>
						<td>공연일</td>
						<td>2018/01/01 (월)</td>
					</tr>
					<tr>
						<td>공연시간</td>
						<td>15:00~18:00</td>
					</tr>
					<tr>
						<td>좌석정보</td>
						<td>K4,K5</td>
					</tr>
					<tr>
						<td>카드번호</td>
						<td>5707-3759-7222-1045</td>
					</tr>										
					</table>
				<div style="text-align:center; margin-top:20px; width:550px;">
					위 결제 정보가 맞습니까? <br>
					'확인'버튼을 클릭하면 결제가 진행됩니다.
				</div>
				<div class="btnLeft">
					<a href="${pageContext.request.contextPath}/member_r_reservationStart3.jsp" id="closeBtn">뒤로가기</a> &nbsp;&nbsp;
					<a href="#" id="selectBtn"  value="확인창" ">확인</a>
				</div>
		</div>

	</form>
</body>
</html>