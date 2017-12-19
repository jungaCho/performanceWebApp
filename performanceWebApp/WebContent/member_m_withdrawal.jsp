<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html >
<html>
<head>
<meta charset="utf-8">
<title>회원 탈퇴</title>
<style>
	
	form {
		background-color: yellow;
		width:400px;
		height:600px;
	}
	#box {
		background-color: red;
		margin-top:50px;
		width:auto;
		height:100px;	
	}
	
	#box1 {
		background-color:green;
		width:40%;
		height:150px;
		float:left;
	}
	#box2 {
		background-color:brown;
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
	<form>
		<span>회원탈퇴</span>&nbsp;<button>확인</button>&nbsp;<button>취소</button><br>
		<div id="box">
			현재 계정 정보 : ***님<br>
			아이디 : ****님<br>
		</div>
		<div id="box1">
			등급 정보 안내
			<src image=""/>
		</div>
		<div id="box2">
			** 회원<br>
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