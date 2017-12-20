<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>로그인화면</title>
<style>
#box {
	
	width: 300px;
	height: 400px;
	background-color: yellow;
	margin: 0 auto; /*박스요소를 수평으로 가운데 정렬해주는 코드 */
	margin-bottom: 10px; /*아래 여백을 10px로 잡아줌 */
	padding: 20px; /* 안쪽 여백을 20px로 잡아줌 */
	text-align : center;
	

}
button {

	width: 80px;
	height : 50px;

}

input {

	text-align : center;
}

#btn1 {
	
	display: inline-block;
	float : left;
	margin-left: 60px;
}

#btn2 {
	
	display : inline-block;
	float : right;
	margin-right: 60px;

}

</style>

</head>
<body>

		<form action="${pageContext.request.contextPath }/loginForm.do" method="post">

		<div id="box">

			아이디 :<br>
			 <input type="text" name="id" size="20"
				placeholder="아이디를 입력해주세요" autofocus></input><br> 
			비밀번호 : <br>
			<input type="password" name="pwd" size="20" placeholder="비밀번호를 입력해주세요"></input><br>
			<br>
			<a href="#">아이디찾기</a>/<a href="#">비밀번호찾기</a><br>
			<br>
			<button type="submit" id="btn1">로그인</button>
		</form>
			
		<form action="${pageContext.request.contextPath }/member_m_signup.jsp" method="post">
			<button type="submit" id="btn2">회원가입</button>
		</form>
			
		</div>

	</form>


</body>
</html>