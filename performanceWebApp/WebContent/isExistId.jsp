<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form>

<div id="box">

<span id="span1">"${requestScope.member.mId }"</span><br>
<span id="span2">현재 조회된 아이디입니다!</span><br>

</div>

<button type="button">비밀번호 찾기</button>
<button type="button">로그인</button>

</form>