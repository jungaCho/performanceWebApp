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
	
	span {
		color: red;
		font-size: 12px;	
	}
	
</style>
<script src="js/jquery-3.2.1.min.js"></script>
<script>

	$(document).ready(function(){
		/*
		$('input[type=text], input[type=password], input[type=email], input[type=date]').on('focus',function() {
			$(this).next('span').remove();
			$('#btnCheckId').next('span').remove();
			$('#btnCheckEmail').next('span').remove();
		});
		
		$('input[type=text], input[type=password], input[type=email], input[type=date]').on('blur',function() {
			if($(this).val().length == 0 ) {
				if($(this).attr('name') == 'id') {
					$('#btnCheckId').after("<span> 아이디를 입력하세요.</span>");
				} else if($(this).attr('name') == 'pwd') {
					$(this).after("<span> 비밀번호를 입력하세요.</span>");
				} else if($(this).attr('name') == 'pwdcheck') {
					$(this).after("<span> 비밀번호를 한번더 입력하세요.</span>");
				} else if($(this).attr('name') == 'name') {
					$(this).after("<span> 비밀번호를 입력하세요.</span>");
				} else if($(this).attr('name') == 'email') {
					$('#btnCheckEmail').after("<span> 비밀번호를 입력하세요.</span>");
				} else if($(this).attr('name') == 'birthday') {
					$(this).after("<span> 비밀번호를 입력하세요.</span>");
				} else if($(this).attr('name') == 'address') {
					$(this).after("<span> 비밀번호를 입력하세요.</span>");
				}
			}
		});
		*/
		
		$('#id').on('focus', function() {
			$('#btnCheckId').next('span').remove();
		});
		

		$('btnCheckId').on('click',function(){
			location.href="${pageContext.request.contextPath}/IdOverlapCheck.do";
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
					if(data.success  == true) { //data에 있는 successs 에 대한 값이 true라면 밑 명령수행
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
	<form>
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
		<button type="button" id="btnCheckEmail">이메일 인증</button><br>
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