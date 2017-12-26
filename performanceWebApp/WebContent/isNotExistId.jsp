<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script src="js/jquery-3.2.1.min.js"></script>
<script>
	$(document).ready(function() {
		$('#signUp').click(function() {
			location.href="${pageContext.request.contextPath}/signUpForm.do";
		});
		
		$('#login').click(function() {
			location.href="${pageContext.request.contextPath}/loginForm.do";
		});
	});
</script>

<form>

<span>"현재 조회된 아이디 정보가 없습니다."</span><br>


<button id="signUp" type="button">회원가입</button>
<button id="login" type="button">로그인</button>

</form>
