<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html >
<html>
<head>
<meta charset="utf-8">
<title>회원 탈퇴</title>
<style>
	
form {
		padding : 30px;
	}
	
	span {
		font-size: 12px;
	}
	
	#pannel > h2 {
		
		display: inline-block;
		margin-right: 10px;
	}
	
	#div1{
		text-align: left;
		line-height:35px;
		margin-top:50px;
	}
	
	#div1 > #btn2 {
		margin-left: 10px;
	}
	
	#div2{
		    margin-top: 20px;
    text-align: left;
    line-height: 35px;
    border-top: 1px solid #5b5b5b;
    padding-top: 20px;
	}
	
	.wrap{padding:50px; font-size:15px;}
	.content{font-size:15px; overflow:hidden; width:950px; font-size:15px; }
	#btn1{background:blue;width: 50px;
    height: 30px;
    color: white;
    border-radius: 5px;
    margin-top: -3px;}
    
    
    #btn2{background:gray;width: 50px;
    height: 30px;
    color: white;
    border-radius: 5px;
    margin-top: -3px;}
    
    input{border:1px solid #5b5b5b; margin-top:5px; margin-left:15px; height:25px; font-size:15px;}
	
	h3{float:left;}
	
	#box{line-height:35px;}
	#box1{line-height:35px;}
	#box2{line-height:35px;}
	
	textarea{border: 1px solid #5b5b5b;
    margin-top: 15px;
    height: 45px;}
</style>
<script src="js/jquery-3.2.1.min.js"></script>
<script>
	$(document).ready(function() {

		$('#btn2').click(function() {
			location.href="${pageContext.request.contextPath}/myPageForm.do";
		});
	});
</script>
</head>
<body>
	<div class="wrap">
	<form action="${pageContext.request.contextPath }/withdrawal.do" method="POST">
		<div>
			<h3>회원탈퇴</h3>&nbsp;
			<button id="btn1" type="submit">확인</button>
			<button id="btn2" type="button">취소</button><br>
		</div>
		<div id="box">
			현재 계정 정보 : ${requestScope.member.mName }님<br>
			아이디 : ${requestScope.member.mId }님<br>
		</div>
		<div id="box1">
			등급 정보 안내<br>
			<img src="upload/grade2.png" width=150px height=150px>
		</div>
		<div id="box2">
			${requestScope.member.rank.rName } 회원<br>
			예매 공연 횟수 : ${requestScope.reservedCount} <br>
			총 결제액 : ${requestScope.totalPrice }<br>
		</div>

		<div id="box3">
			탈퇴사유(선택사항) : <br>
			<textarea name="wdReason" placeholder="탈퇴사유 입력은 선택사항입니다."></textarea> 
		</div>
		
	</form>
	</div>
</body>
</html>