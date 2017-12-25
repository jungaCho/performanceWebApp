<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html >
<html>
<head>
<meta charset="utf-8">
<title>비밀번호 찾기</title>
<script src = "js/jquery-3.2.1.min.js"></script>
<script>
	$(document).ready(function(){
		$('#cancel').click(function() {
			location.href="${pageContext.request.contextPath}/loginForm.do";
		});
		$('#findPwd').click(function() {
			if($('#id').val().trim().length == 0) {
				alert("아이디를 입력해주세요!");
				return false;
			} else if($('#name').val().trim().length == 0) {
				alert("이름을 입력해주세요!");
				return false;
			} else if($('#email').val().trim().length == 0) {
				alert("이메일을 입력해주세요!");
				return false;
			}
			return true;
		});
	});
</script>
</head>
<body>
	<form action="${pageContext.request.contextPath }/findPwd.do" method="POST">
		<div id="box">
			아이디와 이름, 이메일을 입력해주세요!<br>
			ID<br>
			<input type="text" id="id" name="id" size="30" placeholder="ID를 입력해주세요"/><br>
			Name<br>
			<input type="text" id="name" name="name" size="30" placeholder="이름을 입력해주세요"/><br>
			Email<br>
			<input type="email" id="email" name="email" size="30" placeholder="이메일을 입력해주세요"/><br>
			<br>
			<button id="findPwd" type="submit">임시비밀번호 발송</button>&nbsp;
			<button id="cancel" type="button">취소</button>
		</div>
	</form>
</body>
</html>