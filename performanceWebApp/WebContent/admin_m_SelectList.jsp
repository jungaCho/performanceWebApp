<%@ page language="java" contentType="text/html; charset=utf-8"%>
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
		<th>연락처</th>
		<th>이메일</th>
		<th>주소</th>
		<th>등급</th>
		<th>비고</th>
		
	
		<tr>
		
		<tr>
		
		<th>회원2</th>
		<td>2</td>
		<td>2</td>
		<td>2</td>
		<td>2</td>
		<td>2</td>
		<td>2</td>
		<td>2</td>
		<td>2</td>
		
	
		<tr>
		
	</table>
	</form>

</body>
</html>