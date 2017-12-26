<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html >
<html>
<head>
<meta charset="utf-8">
<title>비밀번호 찾기</title>
<style>

	#span1{
	color: red;
	font-size: 12px;
	}
	
</style>
<script src = "js/jquery-3.2.1.min.js"></script>
<script>
	$(document).ready(function(){
		
		$('#cancel').click(function() {
			location.href="${pageContext.request.contextPath}/loginForm.do";
		});
		
		$('#findPwd').click(function() {
			event.preventDefault();
			if($('#id').val().trim().length == 0) {
				alert("아이디를 입력해주세요!");
				return false;
			} else if($('#name').val().trim().length == 0) {
				alert("이름을 입력해주세요!");
				return false;
			} else if($('#email').val().trim().length == 0) {
				alert("이메일을 입력해주세요!");
				return false;
			}
			
			//임시비밀번호발급 팝업창으로 넘어가기.	
			$.ajax({

				url: "${pageContext.request.contextPath}/sendTempPwd.do"
				,
				method: "POST"
				,
				async: true
				,
				dataType: "json"
				,
				data: {
					email: $('#email').val()
				}
				,
				success: function(data){
					if(data.success == true){
						var newWin = window.open("${pageContext.request.contextPath}/tempPwdForm.jsp","","width=700, height=600, top=200,left=200");
					} else {
						alert("<span id=span1>올바른 이메일 주소를 입력해주세요.</span1>");
						return false;
					}
				}
				,
				error: function(jqXHR){
					$('#findPwd').after("<span id=span1>임시비밀번호 발급에 실패했습니다.</span1>");
				}
			});
		});
	});
	
</script>
</head>
<body>
	<form action="${pageContext.request.contextPath }/findPwd.do" method="POST">
		<div id="box">
			아이디와 이름, 이메일을 입력해주세요!<br>
			ID<br>
			<input type="text" id="id" name="id" value="${sessionScope.findIdSession.mId }" size="30" placeholder="ID를 입력해주세요"/><br>
			Name<br>
			<input type="text" id="name" name="name" size="30" placeholder="이름을 입력해주세요"/><br>
			Email<br>
			<input type="email" id="email" name="email" size="30" placeholder="이메일을 입력해주세요"/><br>
			<br>
			<button id="findPwd" type="submit">임시비밀번호 발송</button>&nbsp;
			<button id="cancel" type="button">취소</button>
		</div>
	</form>
</body>
</html>