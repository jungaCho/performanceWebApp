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


</style>
</head>
<body>

	<form>

		<div id="box">

			아이디 : <input type="text" name="id" sizes="20"
				placeholder="아이디를 입력해주세요" autofocus></input><br> 비밀번호 : <input
				type="password" name="pwd" sizes="20" placeholder="비밀번호를 입력해주세요"></input><br>

			<a href="#">아이디찾기</a>/<a href="#">비밀번호찾기</a><br>

			<button type="button" id="btn1">로그인</button>
			<button type="button" id="btn2">회원가입</button>
		</div>

	</form>


</body>
</html>