<%-- member_m_signup --%>
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
	
	span {
	
	color: red;
	font-size: 12px;
	
	}
	
	#span1 {
		color: red;
		font-size: 12px;
	}
	
	#span2 {
		color: green;
		font-size: 12px;
	}
	
</style>
<script src="js/jquery-3.2.1.min.js"></script>
<script>

	

	$(document).ready(function(){
	
		$('#id').on('focus', function() {
			if($('#btnCheckId').next('span').val() != null) {
				$('#btnCheckId').next('span').remove();
			}
		});
		
		var checkIdCount = 0;
		$('#btnCheckId').on('click',function() {
			if($('#btnCheckId').next('span').val() != null) {
				$('#btnCheckId').next('span').remove();
			} 
			$.ajax({
				url: "${pageContext.request.contextPath}/IdOverlapCheck.do"
				,
				method: 'POST' 
				,
				async: true
				,
				dataType : 'json'
				, 
				data : $('form').serialize() 
				,
				success : function(data){
					if(data.success == true ) {
						$('#btnCheckId').after("<span id='span1'> 중복된 아이디입니다. </span>");
					} else if($('#id').val().length < 5 && $('#id').val().length < 16) {
						$('#btnCheckId').after("<span id='span1'> 아이디 양식을 확인해주세요! </span>");
					} else {
						$('#btnCheckId').after("<span id='span2'> 사용가능한 아이디입니다. </span>");
						checkIdCount = 1;
					}
				}
				,
				error : function(jqXHR) {
					jqXHR = null;
				}
			});
		});

		$('#btnCheckEmail').on('click',function(){
			event.preventDefault(); 			
			$.ajax({
				url: "${pageContext.request.contextPath}/sendEmail.do"
				,
				method: 'POST' 
				,
				async: true
				,
				dataType : 'json'
				, 
				data : {
					email: $('#email').val()				
				}
				,
				success : function(data){ 
					if(data.success == true ) {						
						var newWin = window.open("${pageContext.request.contextPath}/authNumberForm.jsp", "", "width=700, height=600, top=200, left=200");
					} else {
						$('#btnCheckEmail').after("<span id='span1'> 이메일 인증에 실패했습니다. </span>");
					}
				}
				,
				error : function(jqXHR) {
						$('#btnCheckEmail').after("<span id='span1'> 이메일 인증에 실패했습니다. </span>");
				}
			});
		});


		$('#pwd').on('focus',function(){
			$(this).next('span').remove();
		});

		$('#pwdcheck').on('focus',function(){
			$(this).next('span').remove();
		});

		$('#name').on('focus',function(){
			$(this).next('span').remove();
		});

		$('#birthday').on('focus',function(){
			$(this).next('span').remove();
		});

		$('#email').on('focus',function(){
			$('#btnCheckEmail').next('span').remove();
		});

		$('#address').on('focus',function(){
			$(this).next('span').remove();
		});
		
		
		$('#id').on('blur', function() {
			if($(this).val() == 0 ) {
				$('#btnCheckId').after("<span> 아이디를 입력하세요</span>");
			} else if($('#id').val().length < 5 && $('#id').val().length < 16) {
				$('#btnCheckId').after("<span id='span1'> 아이디 양식을 확인해주세요! </span>");
			}
		});
		
		$('#pwd').on('blur',function(){
			if($(this).val() == 0 ) {
				$(this).after("<span> 비밀번호를 입력하세요</span>");
			}
		});

		$('#pwdcheck').on('blur',function(){
			if($(this).val() == 0 ) {
				$(this).after("<span> 비밀번호를 한번더 입력하세요</span>");
			} else if($('#pwd').val() != $(this).val()) {
				$(this).after("<span> 비밀번호가 일치하지 않습니다</span>")
			}
		});

		$('#name').on('blur',function(){
			if($(this).val() == 0 ) {
				$(this).after("<span> 이름을 입력하세요</span>");
			}
		});

		$('#birthday').on('blur',function(){
			if($(this).val() == 0 ) {
				$(this).after("<span> 생일을 입력하세요</span>");
			}
		});

		$('#email').on('blur',function(){
			if($(this).val() == 0 ) {
				$('#btnCheckEmail').after("<span> 이메일를 입력하세요</span>");
			}
		});

		$('#address').on('blur',function(){
			if($(this).val() == 0 ) {
				$(this).after("<span> 주소를 입력하세요</span>");
			}
		});	

		$('#btn1').click(function () {
			location.href="${pageContext.request.contextPath}/loginForm.do";
		});

		$('#btn2').click(function(){
			
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
					if($('#span1').val() != null) {
						data.success == false;
						alert("입력한 정보를 확인해주세요!");
						return false;
					} else if(data.success  == true) { //data에 있는 successs 에 대한 값이 true라면 밑 명령수행
						alert("회원가입에 성공하셨습니다!!");
						location.href = "${pageContext.request.contextPath}/member_index.jsp";
					}
				}
				,
				error : function(jqXHR) {
					jqXHR = null;
					alert("회원정보를 정확히 입력해주세요!!");
				}

			});
			
		});
		
	});

</script>
</head>
<body>
	<form name="form">
		<input type="hidden" name="success" id="success">
	<div id="box">
		ID<br>
		<input type="text" id="id" name="id" size="25" placeholder="최소 5~15글자 특수문자 불가"/>&nbsp;
		<button type="button" id="btnCheckId">중복확인</button><br>
		비밀번호<br>
		<input type="password" id="pwd" name="pwd" size="25" placeholder="최소 8~12글자 동일 숫자 연속 3자리 불가"/><br>
		비밀번호 확인<br>
		<input type="password" id="pwdcheck" name="pwdcheck" size="25" placeholder="비밀번호를 한번더 입력해주세요"/><br>
		이름<br>
		<input type="text" id="name" name="name" size="25" placeholder="이름을 입력해주세요"/><br>
		이메일<br>
		<input type="text" id="email" name="email" size="25" placeholder="이메일을 입력해주세요"/>&nbsp;
		<button type="submit" id="btnCheckEmail">이메일 인증</button><br>
		생일<br>
		<input type="date" id="birthday" name="birthday">
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