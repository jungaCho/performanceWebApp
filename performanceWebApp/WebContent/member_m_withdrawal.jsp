<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html >
<html>
<head>
<meta charset="utf-8">
<title>회원 탈퇴</title>
<style>
	
	form {

		width:400px;
		height:600px;
	}
	#box {

		margin-top:50px;
		width:auto;
		height:100px;	
	}
	
	#box1 {
		width:40%;
		height:150px;
		float:left;
	}
	#box2 {
		width:60%;
		height:150px;
		float:left;
	}
	
	#box3 > textarea {
		width:300px;
		height:200px;
	}
</style>
</head>
<body>
	<form action="${pageContext.request.contextPath }/withdrawal.do" method="POST">
		<span>회원탈퇴</span>&nbsp;
		<button id="btn1" type="submit">확인</button>&nbsp;
		<button id="btn2" type="button">취소</button><br>
		<div id="box">
			현재 계정 정보 : ${requestScope.member.mName }님<br>
			아이디 : ${requestScope.member.mId }님<br>
		</div>
		<div id="box1">
			등급 정보 안내
			<src image=""/>
		</div>
		<div id="box2">
			${requestScope.member.rank.rName } 회원<br>
			<br>
			예매 공연 횟수 :	<br> 
			총 결제액 : 
		</div>

		<div id="box3">
			탈퇴사유(선택사항) : <br>
			<textarea name="wdReason" placeholder="탈퇴사유 입력은 선택사항입니다."></textarea> 
		</div>
		
	</form>
</body>
</html>