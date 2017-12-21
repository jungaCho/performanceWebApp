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
</style>
</head>
<body>
	<form action="${pageContext.request.contextPath }/checkMember.do" method="POST">
		<h3>본인확인</h3><br>
		본인확인을 위해 비밀번호를 입력해주세요.<br>
		<div id="box">	
			<input type="password" name="pwd" placeholder="비밀번호 확인"/>&nbsp;<button type="submit">next></button>
		</div>
	</form>
</body>
</html>