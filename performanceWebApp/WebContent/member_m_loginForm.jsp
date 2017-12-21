<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
<%--
<script src="js/jquery-3.2.1.min.js"></script>
<script>
	$(document).ready(function() {
		$('#btn1').click(function() {
			
			$.ajax({
				url: '${pageContext.request.contextPath }/login.do'
				,
				method: 'POST'
				,
				dataType: 'json'
				,
				data: $('form').serialize()
				,
				success: function(data) {
					if(data.success == true) {
						alert("로그인에 성공하셨습니다!!");
						location.href="${pageContext.request.contextPath}/member_index.jsp";
					} else {
						alert("아이디 혹은 비밀번호를 확인하세요!!");
						location.href="${pageContext.request.contextPath}/loginForm.do";
					}
				}
				,
				error: function(jqXHR) {
					alert("error : " + jqXHR);
				}
			});
			
		})
	});
</script>
 --%>
</head>
<body>

		<form action="${pageContext.request.contextPath }/login.do" method="POST">

		<div id="box">

			아이디 :<br>
			<input type="text" name="id" size="20" placeholder="아이디를 입력해주세요"></input><br> 
			비밀번호 : <br>
			<input type="password" name="pwd" size="20" placeholder="비밀번호를 입력해주세요"></input><br>
			<br>
			<c:url var="findIDform" value="/member_m_findId.jsp" scope="page"/>
			<a href="${pageScope.findIDform }">아이디찾기</a>/<a href="#">비밀번호찾기</a><br>
			<br>
			<button type="submit" id="btn1">로그인</button>
		</form>
			
		<form action="${pageContext.request.contextPath }/member_m_signup.jsp" method="post">
			<button type="submit" id="btn2">회원가입</button>
		</form>
			
		</div>



</body>
</html>