<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html >
<html>
<head>
<meta charset="utf-8">
<title>예매</title>
<style>
.reservation {
	overflow: hidden;
	margin-top: 30px;
	height: 110px;
}

.reservation>.r_img {
	float: left;
	width: 90px;
	height: 110px;
	background: #000;
}

.reservation>.r_img>a {
	display: inline-block;
	width: 90px;
	height: 110px;
}

.reservation>.r_text {
	float: left;
}

.reservation>.r_text>ul {
	margin: 0 auto;
}

.reservation>.r_text>ul>li {
	list-style: none;
	font-size: 15px;
}

.reservation>.r_text>ul>.r_text_date {
	margin-top: 15px;
	color: #5D5D5D;
}

.reservation>.r_text>ul>.r_text_title {
	font-weight: bold;
}

.reservation>.r_text>ul>.r_text_button {
	margin-top: 14px;
	width: 80px;
	height: 30px;
	background: gray;
	text-align: center;
	line-height: 30px;
	border-radius: 5px;
}

.reservation>.r_text>ul>.r_text_button>a {
	width: 80px;
	height: 30px;
	display: inline-block;
	line-height: 30px;
	text-decoration: none;
	font-size: 15px;
}

.reservation>.r_text>ul>.r_text_button>a:hover {
	color: #fff;
}
</style>
<script src="js/jquery-3.2.1.min.js"></script> 
<script type="text/javascript">
	$(document).ready(function(){
		$('#open').click(function(){
			var url = '${pageContext.request.contextPath}/member_r_reservationStart.do?pNo=P00001';
			window.open(url, "예매확인", "width=700, height=600");
		});
	});
</script>
</head>
<body>
		<div style="margin-left: 50px;">
			<h3>예매</h3>
			<span style="font-size: 17px;">공연 예매</span> <br>
			<!-- reservation -->
			<div class="reservation">
				<!-- r_img -->
				<div class="r_img">
					<c:forEach var="img" items= "${requestScope.perfomance.posters }">
					</c:forEach>
					<a href="#">포스터</a>
				</div>
				<!-- r_text -->
				<div class="r_text">
					<ul>
						<!-- r_text_title -->
						<li class="r_text_title">공연명 : 싸이콘서트</li>
						<!-- t_text_date -->
						<li class="r_text_date">기간 : 17/01/01~17/02/02<br> 가격 :
							30,000원
						</li>
						<!-- r_text_button -->
						<li class="r_text_button"><a href="#" id="open">예매하기</a></li>
					</ul>
				</div>
			</div>
			<!-- /reservation -->
			<!-- reservation -->
			<div class="reservation">
				<!-- r_img -->
				<div class="r_img">
					<a href="#">포스터</a>
				</div>
				<!-- r_text -->
				<div class="r_text">
					<ul>
						<!-- r_text_title -->
						<li class="r_text_title">공연명 : 싸이콘서트</li>
						<!-- t_text_date -->
						<li class="r_text_date">기간 : 17/01/01~17/02/02<br> 가격 :
							30,000원
						</li>
						<!-- r_text_button -->
						<li class="r_text_button"><a href="#" id="open">예매하기</a></li>
					</ul>
				</div>
			</div>
			<!-- /reservation -->
			<!-- reservation -->
			<div class="reservation">
				<!-- r_img -->
				<div class="r_img">
					<a href="#">포스터</a>
				</div>
				<!-- r_text -->
				<div class="r_text">
					<ul>
						<!-- r_text_title -->
						<li class="r_text_title">공연명 : 싸이콘서트</li>
						<!-- t_text_date -->
						<li class="r_text_date">기간 : 17/01/01~17/02/02<br> 가격 :
							30,000원
						</li>
						<!-- r_text_button -->
						<li class="r_text_button"><a href="#" id="open">예매하기</a></li>
					</ul>
				</div>
			</div>
			<!-- /reservation -->
			<!-- reservation -->
			<div class="reservation">
				<!-- r_img -->
				<div class="r_img">
					<a href="#">포스터</a>
				</div>
				<!-- r_text -->
				<div class="r_text">
					<ul>
						<!-- r_text_title -->
						<li class="r_text_title">공연명 : 싸이콘서트</li>
						<!-- t_text_date -->
						<li class="r_text_date">기간 : 17/01/01~17/02/02<br> 가격 :
							30,000원
						</li>
						<!-- r_text_button -->
						<li class="r_text_button"><a href="#" id="open">예매하기</a></li>
					</ul>
				</div>
			</div>
			<!-- /reservation -->
			<!-- reservation -->
			<div class="reservation" style="margin-bottom: 50px;">
				<!-- r_img -->
				<div class="r_img">
					<a href="#">포스터</a>
				</div>
				<!-- r_text -->
				<div class="r_text">
					<ul>
						<!-- r_text_title -->
						<li class="r_text_title">공연명 : 싸이콘서트</li>
						<!-- t_text_date -->
						<li class="r_text_date">기간 : 17/01/01~17/02/02<br> 가격 :
							30,000원
						</li>
						<!-- r_text_button -->
						<li class="r_text_button"><a href="#" id="open">예매하기</a></li>
					</ul>
				</div>
			</div>
			<!-- /reservation -->


		</div>
</body>
</html>