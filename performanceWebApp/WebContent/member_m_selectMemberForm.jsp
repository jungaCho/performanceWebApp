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
		float : left;
		line-height:55px;
		width:470px;
	}
	
	#div2{
		float: right;
		margin:0 auto;
		width:470px;
		height:380px;
		border-left:1px solid #5b5b5b;
		text-align:center;
	
	}
	
	.wrap{padding:50px;}
	.content{border:1px solid #5b5b5b; overflow:hidden; width:950px; margin-top:40px; padding:20px 30px; height:400px; font-size:15px;}
	#btn1{background:blue;width: 50px;
    height: 30px;
    background: blue;
    color: white;
    border-radius: 5px;
    margin-top: -3px;}
	</style>
</head>
<body>
<div class="wrap">
	<form action="${pageContext.request.contextPath }/modifyForm.do" method="post">
		<div id="pannel">
		
		<h2>회원정보조회</h2><button type="submit" id="btn1">수정</button>
		</div>

		<div class="content">
			<div id="div1">
			아이디 : ${requestScope.member.mId }<br>
			비밀번호 : ${requestScope.member.mPw }<br>
			
			이름 : ${requestScope.member.mName }<br>
			생일 : ${requestScope.member.birthday }<br>
			이메일 : ${requestScope.member.email }<br>
			주소 : <br>${requestScope.member.address } <br>
			</div>
			
			<div id="div2">
			<br>
			<h4>등급 포인트 안내</h4>
			<br><br>
			<img src="upload/grade2.png" width=250px height=250px>
			<br>
			<br><br>
			등급이름 : ${requestScope.member.rank.rName }<br>
			예매 공연 횟수 : ${requestScope.reservedCount} <br>
			
			</div>
		</div>
	
	
	</form>
</div>
</body>
</html>