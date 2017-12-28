<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html >
<html>
<head>
<meta charset="utf-8">
<title>예매</title>
<style>
.wrapper{margin-left:50px; margin-right:50px; min-height:600px;}

.reservation {
	margin-top: 10px;
	
}

table{border:1px solid gray; text-align:center; font-size:12px; margin-top:40px; margin-bottom:15px; width:100%}
th{background:#999; color:#fff;}
td{padding:0 25px; border-bottom:1px solid gray;}
tr{height:40px;}
.selectbox{float:right;}
.note table tr{border-bottom:1px solid #ddd;}
.confirm-1{font-size:15px;}
.confirm-2{font-size:20px;}
#selectBtn {
	width: 80px;
	background: #368AFF;
	color: #fff;
	display: inline-block;
	height: 35px;
	text-align: center;
	line-height: 35px;
	text-decoration: none;
	border-radius: 5px;
}

a:hover {
	color: #fff;
}
input{width:300px; height:25px;}
</style>
<script src="js/jquery-3.2.1.min.js"></script> 
<script type="text/javascript">
$(document).ready(function(){
	$('#selectBtn').click(function() {
		$.ajax({
			url: '${pageContext.request.contextPath}/canceledReservation.do'
			,
			method: 'POST'
			,
			dataType: 'json'
			,
			data: {
				pwd: $('#pwd').val()
			}
			,
			success: function() {
				
			}
			,
			error: function() {
				alert("error : " + jqXHR.status);
			}
		});
	});
});
</script>
</head>
<body>
		<div class="wrapper">
			<h3>본인확인</h3>
			<span class="confirm-1">본인 확인을 위해 비밀번호를 입력하세요.</span> <br>
			<!-- reservation -->
			<div class="reservation">
						<h2 class="confirm-2">환불·변경 수수료</h2>
						<table cellspacing=0; >
							<thead>
								<tr>
									<th colspan="2">구분</th>
									<th>환불수수료</th>
									<th>비고</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td colspan="2">관람일 10일전</td>
									<td>전액</td>
									<td></td>
								</tr>
								<tr>
									<td colspan="2">관람일 9일~1일 전</td>
									<td>티켓금액의 10%</td>
									<td>관람일 3일 전까지, 예매당일 환불할 경우<br> 별도의 수수료 없이 환불 가능<br> <span class="font12 f_666">(전화/방문  20:00까지, 인터넷 23:59까지)</span> </td>
								</tr>
								<tr>
									<td rowspan="2">관람당일</td>
									<td>전당기획공연</td>
									<td>티켓금액의 90%</td>
									<td>공연시작 전까지</td>
								</tr>
								<tr>
									<td>대관공연</td>
									<td>환불 불가</td>
									<td>관람일 1일 전까지 가능</td>
								</tr>
							</tbody>
						</table>
					<br>
					<input type="text" name="pwdConfirm" id="pwd" placeholder="비밀번호 확인">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="#" id="selectBtn">next></a>
			</div>
			<!-- /reservation -->
			 
		</div>
</body>
</html>