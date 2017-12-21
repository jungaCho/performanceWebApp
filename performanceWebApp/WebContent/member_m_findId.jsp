<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html >
<html>
<head>
<meta charset="utf-8">
<title>아이디 찾기</title>
<script src = "js/jquery-3.2.1.min.js"></script>
<script>

$(document).ready(function(){
	
	$('#findId').click(function(){
		
		
	
});
	
});


</script>
</head>
<body>
	<form action="${pageContext.request.contextPath }/findId.do" method="post">
		<div id="box">
			이름과 이메일를 입력해주세요!<br>
			이름<br>
			<input type="text" name="name" size="30" placeholder="이름을 입력해주세요"/><br>
			Email<br>
			<input type="email" name="email" size="30" placeholder="이메일을 입력해주세요"/><br>
			<br>
			<button id ="findId">아이디 찾기</button>&nbsp;
			<button>취소</button>
		</div>
	</form>

</body>
</html>