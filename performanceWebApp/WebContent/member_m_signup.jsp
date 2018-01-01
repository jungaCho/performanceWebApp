<%-- member_m_signup --%>
<%@ page contentType="text/html; charset=utf-8"%>

<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>회원가입</title>
<style>

	body {
		background: #ddd;
	}
	form {
		width:500px;
		height:550px;
		background-color:#f0f0f0;
		margin: 0 auto;
		margin-top:90px;
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
	
	#accept {
		color: green;
		font-size: 12px;
	}
	
</style>
<script src="js/jquery-3.2.1.min.js"></script>
<script>

	$(document).ready(function(){
	
		$('#id').on('focus', function() {
			if($('#btnCheckId').next('span').val() != null) {
				if($('#btnCheckId').next('accept').val() == null) {
					$('#btnCheckId').next('span').remove();
				}
			}
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
			} else if($(this).val().length < 5 && $(this).val().length < 16) {
				$('#btnCheckId').after("<span id='problem'> 최소5~15글자 특수문자 불가 </span>");
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
						$('#btnCheckId').after("<span id='problem'> 중복된 아이디입니다. </span>");
						checkIdCount = 0;
					} else if($('#id').val().length < 5 && $('#id').val().length < 16) {
						$('#btnCheckId').after("<span id='problem'> 아이디 양식을 확인해주세요! </span>");
						checkIdCount = 0;
					} else if(data.success == false){
						$('#btnCheckId').after("<span id='accept'> 사용가능한 아이디입니다. </span>");
						checkIdCount = 1;
					}
					console.log("checkIdCount : " + checkIdCount);
				}
				,
				error : function(jqXHR) {
					jqXHR = null;
				}
			});
		});
		
		$('#pwd').on('blur',function(){
			if($(this).val() == 0 ) {
				$(this).after("<span> 비밀번호를 입력하세요</span>");
			} else if($(this).val().length < 5 && $(this).val().length < 16) {
				$(this).after("<span id='problem'> 최소8~12글자 동일숫자 연속 3자리 불가 </span>");
			}
		});

		$('#pwdcheck').on('blur',function(){
			if($(this).val() == 0 ) {
				$(this).after("<span> 비밀번호를 한번더 입력하세요</span>");
			} else if($('#pwd').val() != $(this).val()) {
				$(this).after("<span id='problem'> 비밀번호가 일치하지 않습니다</span>")
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
		
		var checkEmailCount = 0;
		$('#btnCheckEmail').on('click',function(){
			event.preventDefault();
			if($('#btnCheckEmail').next('span').val() != null) {
				$('#btnCheckEmail').next('span').remove();
			}
			
			if($('#btnCheckEmail').next('br').next('span').val() != null) {
				if($('#btnCheckEmail').next('br').next('#accept').val() == null) {
					$('#btnCheckEmail').next('br').next('span').remove();
					$('#btnCheckEmail').next('br').remove();
				} else {
					alert("인증이 완료되었습니다!!");
					return false;
				}
			}

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
						$('#btnCheckEmail').after("<br><span id='problem'> 이메일 양식에 맞지 않거나 존재하지 않는 이메일 입니다! </span>");
						checkEmailCount = 0;
					}
				}
				,
				error : function(jqXHR) {
					$('#btnCheckEmail').after("<span id='problem'> 이메일 인증에 실패했습니다. </span>");
					checkEmailCount = 0;
				}
			});
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
			if($('#btnCheckEmail').next('br').next('#accept').val() != null) {
				checkEmailCount = 1;
				console.log("checkEmailCount :" + checkEmailCount)
			} else {
				checkEmailCount = 0;
			}		
			
			if($('#problem').val() != null) {
				alert("입력한 정보를 확인해주세요!");
				return false;
			} else if(checkIdCount == 0) {
				alert("아이디 중복확인이 필요합니다!!");
				return false;
			} else if(checkEmailCount == 0) {
				alert("이메일 인증이 필요합니다!!");	
				return false;
			}
			
			$.ajax({
				url: "${pageContext.request.contextPath}/SignUp.do"
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
					if(data.success  == true) { 
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