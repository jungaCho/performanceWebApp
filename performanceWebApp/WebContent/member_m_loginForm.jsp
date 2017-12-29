<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>로그인화면</title>
<link rel="stylesheet" type="text/css" href="css/common.css" />   
<style>
.bg{background:url('upload/bg.jpg') no-repeat 100%; height:100%;}
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
<script src="js/jquery-3.2.1.min.js"></script>
<script>
	$(document).ready(function() {
		
		$("#btn1").click(function() {
			if($('#id').val().length == 0) {
				alert("아이디를 입력해주세요");
				return false;
			}
			if ($('#pwd').val().trim().length == 0) {
				alert("비밀번호를 입력해주세요");
				return false;
			}		

			$.ajax({
				url: "${pageContext.request.contextPath}/login.do"
				,
				method: 'POST'
				,
				dataType: 'json'
				,
				data: $('form').serialize()
				,
				success: function(data) {
					if(data.success == 0) {
						location.href="${pageContext.request.contextPath}/member_index.jsp";
					} else if(data.success == 1) {
						alert("탈퇴된 회원정보입니다");
						location.href="${pageContext.request.contextPath}/loginForm.do";
					} else {
						alert("존재하지 않는 회원정보입니다 아이디 혹은 비밀번호를 확인해 주세요");
						location.href="${pageContext.request.contextPath}/loginForm.do";
					}
				}
				,
				error: function(){
					alert("error : " + jqXHR.status);
					return false;
				}
			});
		
		});
		
		$("#btn2").click(function() {
			location.href="${pageContext.request.contextPath}/signUpForm.do";
		});		
	});
</script>

</head>
<body>
	<div class="bg">

		<form action="${pageContext.request.contextPath }/login.do" method="POST">
		<div id="box">
			아이디 :<br>
			<input type="text" id="id" name="id" size="20" placeholder="아이디를 입력해주세요"></input><br> 
			비밀번호 : <br>
			<input type="password" id="pwd" name="pwd" size="20" placeholder="비밀번호를 입력해주세요"></input><br>
			<br>
			<c:url var="findIDform" value="/findIdForm.do" scope="page"/>
			<c:url var="findPwdform" value="/findPwdForm.do" scope="page"/>
			<a href="${pageScope.findIDform }">아이디찾기</a>/<a href="${pageScope.findPwdform }">비밀번호찾기</a><br>
			<br>
			<button type="submit" id="btn1">로그인</button>
			<button type="button" id="btn2">회원가입</button>
		</div>
		</form>

	</div>
</body>
</html>