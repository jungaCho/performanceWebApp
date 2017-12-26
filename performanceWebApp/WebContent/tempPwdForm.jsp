<%-- authNumberForm.jsp --%>
<%@ page contentType="text/html; charset=utf-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>임시비밀번호 입력 성공 화면</title>
<script src="js/jquery-3.2.1.min.js"></script>

<script>

	$(document).ready(function(){		
	

		
		$('#cancelBtn').click(function() {
			event.preventDefault(); 
			$(opener.document).find('#findPwd').after("<span id='span1'> 임시비밀번호 발급에 실패했습니다. </span>");
			window.close();
					 
		});		
	
		
	});
	

</script>
</head>
<body>
	<form action="${pageContext.request.contextPath}/login.do ">
		<input type="hidden" id="success" name="success">	
		인증번호 : <input type="text" name="tempPwd" size="25" placeholder="인증번호를 입력해주세요"/><br>
		<button type="submit" id="btn">로그인화면으로 돌아가기</button>&nbsp;
		<button type="submit" id="cancelBtn">취소</button>
	</form>
</body>
</html>