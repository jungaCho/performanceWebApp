<%-- member_signup --%>
<%@ page contentType="text/html; charset=utf-8"%>

<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>회원가입</title>
<style>
	body {
		background: green;
	}
	form {
		width:500px;
		height:550px;
		background-color:yellow;
		margin: 0 auto;
	}
	
	#box {
		
		padding-top: 60px;
		margin-left: 40px;
	}
	
	#btn1{
		margin-right:60px;
	
	}
	
	#btn2 {
		margin-right:20px;
	}
	
	#btn1, #btn2 {
		float:right;
		width:80px;
		height:30px;
		margin-top:30px;
		
	}
	
</style>
<script src="js/jquery-3.2.1.min.js"></script>
<script>

	$(document).ready(function(){
		$('#btn1').click(function () {
			location.href="${pageContext.request.contextPath}/loginForm.do";
		});
		
		$('#btn2').click(function(){
			if($)
			
			$.ajax({
				//ajax는 부메랑. url에 있는 곳으로 먼저 이동 - 거기선 "/member_m_newMember.jsp"로 이동된다.
				url: "${pageContext.request.contextPath}/SignUp.do"
				,
				method: 'POST' 
				,
				async: true
				,
				dataType : 'json'
				, //이걸 꼭 지정해줘야 데이터를 받아올수있다. "/member_m_newMember.jsp"에 있는 json 데이터이다.
				data : $('form').serialize() 
				,
				success : function(data){ //부메랑이니까 다시 돌아와 이 json데이터를 받아오는게 성공했다면 밑 내용이 수행된다. 
					
					if(data.success  == true) { //data에 있는 successs 에 대한 값이 true라면 밑 명령수행
						alert("회원가입에 성공하셨습니다!!");
						location.href = "${pageContext.request.contextPath}/member_index.jsp";
					}
					
				},
				
				error : function(jqXHR){
					alert('Error: ' + jqXHR.status );
					
				}
				
			});
			
		});
		
	});

</script>
</head>
<body>
	<form>
	<div id="box">
		ID<br>
		<input type="text" id="id" name="id" size="25" placeholder="최소 5~15글자 특수문자 불가"/>&nbsp;
		<button type="button">중복확인</button><br>
		비밀번호<br>
		<input type="password" id="pwd" name="pwd" size="25" placeholder="최소 8~12글자 동일 숫자 연속 3자리 불가"/><br>
		비밀번호 확인<br>
		<input type="password" id="pwdcheck" name="pwdcheck" size="25" placeholder="비밀번호를 한번더 입력해주세요"/><br>
		이름<br>
		<input type="text" id="name" name="name" size="25" placeholder="이름을 입력해주세요"/><br>
		이메일<br>
		<input type="text" id="email" name="email" size="25" placeholder="이메일을 입력해주세요"/>&nbsp;
		<button type="button">이메일 인증</button><br>
		생일<br>
		<input type="date" id="date" name="birthday">
		<br> 
		주소<br>
		<input type="text" id="address" name="address" size="40" placeholder="주소를 입력해주세요"/>
		<br>
		<button id="btn1" type="button">취소</button>&nbsp;
		<button id="btn2" type="button">회원가입</button>
	</div>
	</form>
</body>
</html>