<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>회원정보 리스트</title>

<style>

	h2 {
		
		color : black;
	}

	form {
	
		color : black;
	
	}
	

</style>

	<script src="js/jquery-3.2.1.min.js"></script>
<script>
	$(document).ready(function() {
		
	/* 	$("#btn1").click(function() {
			if($('#id').val().length == 0) {
				alert("아이디를 입력해주세요");
				return false;
			}
			if ($('#pwd').val().trim().length == 0) {
				alert("비밀번호를 입력해주세요");
				return false;
			}		
		});
		
		$("#btn2").click(function() {
			location.href="${pageContext.request.contextPath}/member_m_signup.jsp";
		}); */
		
</script>

</head>
<body>
	
	<h2>회원관리</h2>
	<form>
	<select id="view">
	<option value="회원번호">
	<option value="이름">
	<option value="아이디">
	<option value="등급">
		
	</select>
	<button type="button">순으로 정렬</button>
	
	<select id="filter" >
	<option value="회원번호">
	<option value="이름">
	<option value="아이디">
	<option value="등급">
	<option value="탈퇴여부">
		
	</select>
	
	<button type="button">검색</button>

	<table id="table1" border = "1">
		
		<tr>
		
		<th>회원번호</th>
		<th>이름</th>
		<th>아이디</th>
		<th>비밀번호</th>
		<th>이메일</th>
		<th>주소</th>
		<th>등급</th>
		<th>비고</th>
		
		
		</tr>
		
		<c:forEach var="members" items= "${requestScope.memberList }" varStatus="loop">
		<tr id= "datas">
		
		<th>${pageScope.members.mNo }</th>
		<td>${pageScope.members.mName }</td>
		<td>${pageScope.members.mId }</td>
		<td>${pageScope.members.mPw }</td>
		<td>${pageScope.members.email }</td>
		<td>${pageScope.members.address }</td>
		<td>${pageScope.members.rankNo }</td>
		
		<td>비고</td>
		
		
		</tr>
		</c:forEach>
	</table>
	</form>

</body>
</html>