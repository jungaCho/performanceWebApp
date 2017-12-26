<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html >
<html>
<head>
<meta charset="utf-8">
<title>예매</title>
<style>
.wrapper{margin-left:50px; margin-right:50px;}

.reservation {
	overflow: hidden;
	margin-top: 10px;
	height: 110px;
}

table{border:1px solid gray; text-align:center; font-size:12px; margin-top:40px; margin-bottom:15px; width:100%}
th{background:#FFBB00; color:#000;}
tr{height:35px; line-height:35px;}
td{width:90px;}
.selectbox{float:right;}
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
	<form>
		<div class="wrapper">
			<h3>예매</h3>
			<span style="font-size: 17px;">예매 내역</span> <br>
			<!-- reservation -->
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
				<!-- table -->
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
					<tr>
						<td>R1711001001</td>
						<td>2017.11.14</td>
						<td>싸이콘서트</td>
						<td>2017.12.25</td>
						<td>2</td>
						<td><button type="submit">상세보기</button></td>
						<td class=""><button type="submit">예약취소</button></td>
					</tr>
				</table>
				</form>
				<!-- /table -->
			</div>
			<!-- /reservation -->
			 
		</div>
	</form>
</body>
</html>