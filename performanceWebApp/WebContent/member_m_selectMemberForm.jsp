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
	
	div {
		padding: 40px;
		width: 250px;
	}
	
	#div1{
		float : left;
	}
	
	#div2{
		float: left;	
	}
	
	</style>
</head>
<body>
session : ${not empty sessionScope.member}<br>
	<form action="${pageContext.request.contextPath }/modifyForm.do" method="post">
		<div id="pannel">
		
		<h2>회원정보조회</h2><button type="submit" id="btn1">수정</button>
		</div>

		<div id="div1">
		아이디 : ${requestScope.member.mId }<br>
		비밀번호 : ${requestScope.member.mPw }<br>
		
		이름 : ${requestScope.member.mName }<br>
		생일 : ${requestScope.member.birthday }<br>
		이메일 : ${requestScope.member.email }<br>
		주소 : <br>${requestScope.member.address } <br>
		</div>
		
		<div id="div2">
		
		<h4>등급 포인트 안내</h4>
		
		등급이름 : ${requestScope.member.rank.rName }<br>
		예매 공연 횟수 : use el<br>
		
		</div>
	
	
	</form>
</body>
</html>