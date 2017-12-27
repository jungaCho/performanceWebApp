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
			<h3>본인확인</h3>
			<span style="font-size: 17px;">본인 확인을 위해 비밀번호를 입력하세요.</span> <br>
			<!-- reservation -->
			<div class="reservation">
				
			</div>
			<!-- /reservation -->
			 
		</div>
	</form>
</body>
</html>