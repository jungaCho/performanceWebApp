<%@ page contentType="text/html; charset=utf-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>회원정보조회페이지</title>
	<style>
	form {
	
		padding : 30px;
	}
	
	#pannel > h2 {
		
		display: inline-block;
		margin-right: 10px;
	}
	
	#div1{
		margin-bottom: 20px;
		text-align: left;
	}
	
	#div1 > #btn2 {
		margin-left: 10px;
	}
	
	#div2{
		margin-top:20px;
		text-align: left;
	}
	
	</style>
</head>
<body>
	<form>
		<div id="pannel">
		<h2>회원정보수정</h2>
		<button type="button" id="btn1">확인</button>
		<button type="button" id="btn1">취소</button>
		</div>
	
		<div id="div1">
		아 이 디 : <input type="text" name="pwd" size="30" value="use el"readonly></input><br>
		비밀번호 : <input type="password" name="pwd" size="30" value="use el"readonly></input><button type="button" id="btn2">변경</button><br>
		
		</div>
		
		<hr width="500" align = "center" color = "black" size ="1">
		
		<div id="div2">
		
		이  름 : <input type="text" name="name" value="use el" size="30"/><br>
		생  일 : <input type="text" name="phone" value="use el" size="30"/><br>
		이메일 :<input type="text" name="email" value="use el" size="30"/><br>
		주  소 : <input type="text" name="address" value="use el" size="30"/><br>
		
		</div>
	
	
	</form>
</body>
</html>