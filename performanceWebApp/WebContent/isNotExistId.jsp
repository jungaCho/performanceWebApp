<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${empty requestScope.member}">
<!-- <script src="js/jQuery-3.2.1.min.js"></script>
<script>

$(document).ready(function(){

$('#span1').text("현재 조회된 아이디 정보가 없습니다.");
$('#span2').text("");
});

</script> -->
</c:if>

<form>

<span>"현재 조회된 아이디 정보가 없습니다."</span>


<button type="button">비밀번호 찾기</button>
<button type="button">로그인</button>

</form>