<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script src="js/jquery-3.2.1.min.js"></script>
<script>
	$(document).ready(function() {
		$('#findPwd').click(function() {
			location.href="${pageContext.request.contextPath}/findPwdForm.do";
		});
		
		$('#login').click(function() {
			location.href="${pageContext.request.contextPath}/loginForm.do";
		});
	});
</script>

<form>

<div id="box">

<span id="span1">"${sessionScope.findIdSession.mId }"</span><br>
<span id="span2">현재 조회된 아이디입니다!</span><br>

</div>

<button type="button" id="findPwd">비밀번호 찾기</button>
<button type="button" id="login">로그인</button>

</form>