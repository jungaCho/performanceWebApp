<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${empty requestScope.member.mId}">
<script src="js/jQuery-3.2.1.min.js"></script>
	<script>

	$('#box').text("현재 조회된 아이디 정보가 없습니다.");
	</script>
</c:if>

	<form>
		<div id="box">
		
			"${requestScope.mId }"<br>
			현재 조회된 아이디입니다!<br>
			
			<button type="button">비밀번호 찾기</button>
			<button type="button">로그인</button>			
		</div>
	</form>
