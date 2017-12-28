<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html >
<html>
<head>
<meta charset="utf-8">
<title>본인확인</title>
<style>
	form {
		margin-left:20px;
	}
	#box {
		margin-top:100px;
	}
	.wrap{padding:50px;}
</style>
</head>
<script src="js/jquery-3.2.1.min.js"></script>
<script>
	$(document).ready(function(){
		$('#sideMenu, #btn1').click(function () {
			var pwd = $('#pwd').val();
			if(pwd.length == 0){
				alert("본인확인을 위해 비밀번호를 입력해주세요.");
				return false;
			}
		});
	});
</script>
<body>
	<div class="wrap">
	<form action="${pageContext.request.contextPath }/checkMember.do" method="POST">
		<h3>본인확인</h3><br>
		본인확인을 위해 비밀번호를 입력해주세요.<br>
		<div id="box">	
			<input type="password" id="pwd" name="pwd" placeholder="비밀번호 확인"/>&nbsp;<button type="submit" id="btn1">next></button>
		</div>
	</form>
	</div>
</body>
</html>