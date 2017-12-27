<%-- authNumberForm.jsp --%>
<%@ page contentType="text/html; charset=utf-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>임시비밀번호 입력 성공 화면</title>
<script src="js/jquery-3.2.1.min.js"></script>
<script>

	$(document).ready(function() {
		$('#btn').click(function(){
			$(opener.location).attr("href","${pageContext.request.contextPath}/loginForm.do");
			window.close();
		});
	});

</script>
</head>
<body>
	<form>	
		임시비밀번호 발송이 완료 되었습니다!<br>
		로그인 화면으로 돌아갑니다!<br>
		<button type="button" id="btn">로그인화면으로 돌아가기</button>&nbsp;
	</form>
</body>
</html>