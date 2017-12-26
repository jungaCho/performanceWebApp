<%@ page contentType="text/html; charset=utf-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>회원정보조회페이지</title>
<script src="js/jquery-3.2.1.min.js"></script>
<script>
</script>
</head>
<body>
	<form>
		현재 비밀번호 : <input type="password" name="oldPwd" size="25" placeholder="현재 비밀번호를 입력해주세요"/><br>
		새로운 비밀번호 : <input type="password" name="newPwd" size="25" placeholder="새로운 비밀번호를 입력해주세요"/><br>
		새로운 비밀번호 : <input type="password" name="npCheck" size="25" placeholder="비밀번호를 한번더 입력해주세요"/><br>
		<br>
		<button>비밀번호 변경</button>&nbsp;
		<button>취소</button>
	</form>
</body>
</html>