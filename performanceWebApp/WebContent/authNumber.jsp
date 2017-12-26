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
		alert('call');
		window.open("${pageContext.request.contextPath}/authNumberForm.jsp", "", "width=700, height=600, top=200, left=200");
		
		
	});
	

</script>
</head>
<body>
	
</body>
</html>