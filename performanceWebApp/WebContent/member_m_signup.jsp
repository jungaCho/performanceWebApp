<%-- member_signup --%>
<%@ page contentType="text/html; charset=utf-8"%>

<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>회원가입</title>
<style>
	form {
		width:500px;
		height:550px;
		background-color:yellow;
		margin: 0 auto;
	}
	
	#box {
		
		padding-top: 60px;
		margin-left: 40px;
	}
	
	#btn1{
		margin-right:60px;
	
	}
	
	#btn2 {
		margin-right:20px;
	}
	
	#btn1, #btn2 {
		float:right;
		width:80px;
		height:30px;
		margin-top:30px;
		
	}
	
</style>
<script>
	$(document).ready(function) {
		
	}
</script>
</head>
<body>
	<form>
	<div id="box">
		ID<br>
		<input type="text" name="id" size="40" placeholder="최소 5~15글자, 특수문자 포함 불가"/>&nbsp;
		<button type="button">중복확인</button><br>
		비밀번호<br>
		<input type="password" name="pwd" size="40" placeholder="최소 8~12글자, 동일 숫자 연속 3자리 불가"/><br>
		비밀번호 확인<br>
		<input type="password" name="pwdcheck" size="40" placeholder="비밀번호를 한번더 입력해주세요"/><br>
		이름<br>
		<input type="text" name="name" size="40" placeholder="이름을 입력해주세요"/><br>
		이메일<br>
		<input type="text" name="email" size="40" placeholder="이메일을 입력해주세요"/>&nbsp;
		<button type="button">이메일 인증</button><br>
		주소<br>
		<input type="text" name="address" size="40" placeholder="주소를 입력해주세요"/>
		<br>
		<button id="btn1" type="button">취소</button>&nbsp;
		<button id="btn2" type="button">회원가입</button>
	</div>
	</form>
</body>
</html>