<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html >
<html>
<head>
<meta charset="utf-8">
<title>아이디 찾기</title>
<script src = "js/jquery-3.2.1.min.js"></script>
<script>

$(document).ready(function(){
	$('#cancel').click(function() {
		location.href="${pageContext.request.contextPath}/loginForm.do";
	});
	
	$('#findId').click(function(){
		if($('#name').val().trim().length == 0){
			alert("이름을 입력해주세요!!");
			return false;
		} else if($('#email').val().trim().length == 0) {
			alert("이메일을 입력해주세요!!");
			return false;
		}
		
		$.ajax({
			url: "${pageContext.request.contextPath}/findId.do"
			,
			method: 'POST'
			,
			dataType: 'json'
			,
			data: {
				name: $('#name').val(),
				email: $('#email').val()
			}
			,
			success: function(data) {
				if(data.success == true) {
					location.href="${pageContext.request.contextPath}/findIdRetrieve.do";
				} else if (data.success == false) {
					alert("해당 정보의 아이디가 존재하지 않습니다!!");
					return false;
				}
			}
			,
			error: function() {
				alert("error : " + jqXHR.status);
			}
		});
	}); 
});


</script>
</head>
<body>
	<%-- <form action="${pageContext.request.contextPath }/findId.do" method="post"> --%>
	<form>
		<div id="box">
			이름과 이메일을 입력해주세요!<br>
		<%-- 	<input type="hidden" name="member" value="${requestScope.member }"> --%>
			이름<br>
			<input type="text" id="name" name="name" size="30" placeholder="이름을 입력해주세요"/><br>
			Email<br>
			<input type="email" id="email" name="email" size="30" placeholder="이메일을 입력해주세요"/><br>
			<br>
			<button type="submit" id ="findId">아이디 찾기</button>&nbsp;
			<button type="button" id ="cancel">취소</button>
		</div>
	</form>

</body>
</html>