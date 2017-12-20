<%@ page contentType="text/html; charset=utf-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>회원정보조회페이지</title>

	<style>
	
	#pannel > h2 {
		
		display: inline-block;
		margin-right: 10px;
	}

	
	#div1{
		
		padding : 40px;
		float : left;
		background-color : yellow;
		width: 300px;
		height: 500px
	
	}
	
	#div2{
		float: left;
		background-color : yellow;
		width: 300px;
		height: 500px;
		
	
	}
	
	</style>

</head>
<body>
	<form action="${pageContext.request.contextPath }/memberModifyForm.do" method="post">
		<div id="pannel">
		<h2>회원정보조회</h2><button type="button" id="btn1">수정</button>
		</div>
	
		<div id="div1">
		

		아이디 : use el<br>
		비밀번호 : use el<br>
		
		이름 : use el<br>
		연락처 : use el<br>
		이메일 : use el<br>
		주소 : use el <br>
		
		</div>
		
		<div id="div2">
		
		<h4>등급 포인트 안내</h4>
		
		use el등급이름<br>
		예매 공연 횟수 : use el<br>
		감상평 수 : use el<br>
		
		</div>
	
	
	</form>
</body>
</html>