<%-- authNumberForm.jsp --%>
<%@ page contentType="text/html; charset=utf-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>이메일 인증화면</title>
<script src="js/jquery-3.2.1.min.js"></script>
<script>

	$(document).ready(function(){		
	
		$('#btn').click(function() {
			 var authNumber1 = '${sessionScope.authNumber}';
			 var authNumber2 =  $(':text[name=authNumber]').val();
			 if(authNumber1 == authNumber2) {
				$(opener.document).find('#btnCheckEmail').after("<span id='span1'> 이메일 인증에 성공하였습니다. </span>"); 					
				  
			 } else {				 
				 $(opener.document).find('#btnCheckEmail').after("<span id='span1'> 이메일 인증에 실패하였습니다. </span>");				
			 }
			 
			 window.close();
		});		
		
		$('#cancelBtn').click(function() {			
			 window.close();
		});		
	
		
	});
	

</script>
</head>
<body>
	<form  name="form">
		<input type="hidden" id="success" name="success">	
		인증번호 : <input type="text" name="authNumber" size="25" placeholder="인증번호를 입력해주세요"/><br>
		<button type="button" id="btn">입력</button>&nbsp;
		<button type="button" id="cancelBtn">취소</button>
	</form>
</body>
</html>