<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html >
<html>
<head>
<meta charset="utf-8">
<title>비밀번호 찾기</title>
<script src = "js/jquery-3.2.1.min.js"></script>
<script>



	$(document).ready(function(){
		
		$('#btn1').click(function(){
			
			
			alert("이메일로 임시비밀번호 발급이 완료되었습니다!");
			
		});
		
	});
	 

</script>
</head>
<body>
	<form>
		<div id="box">
			아이디와 이름, 이메일을 입력해주세요!<br>
			ID<br>
			<input type="text" name="id" size="30" placeholder="ID를 입력해주세요"/><br>
			Name<br>
			<input type="text" name="name" size="30" placeholder="이름을 입력해주세요"/><br>
			Email<br>
			<input type="email" name="email" size="30" placeholder="이메일을 입력해주세요"/><br>
			<br>
			<button id="btn1" type="button">임시 비밀번호 받기</button>&nbsp;
			<button>취소</button>
		</div>
	</form>
</body>
</html>