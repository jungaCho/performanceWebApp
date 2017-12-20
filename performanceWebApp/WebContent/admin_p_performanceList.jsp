<%@ page contentType="text/html; charset=utf-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>공연 조회</title>

	<style>
	
	form {
	
		padding : 30px;
		background-color : gray;
		width: 800px;
		height: 750px;
	
	}
	
	#btn1 {

		float: right;
		
	}
	
	#search {
	
		float: right;
	
	}

	#btn2 {

		width: 70px;
		height: 35px;
		
	}
	
	#btn3 {

		width: 70px;
		height: 35px;
		margin-right: 50px;
	}

	#pannel > h2 {
		
		display: inline-block;
		margin-right: 10px;
	}

	
	#div1{
		
		margin-bottom: 20px;
		text-align: left;
				
	}
	
	#div1 > #btn2 {
		
		margin-left: 10px;
	
	}
	
	
	
	#div2{
		
		margin-top:20px;
		text-align: left;
		
	}

	
	</style>

</head>
<body>
	<form>
	
		<div id="pannel">
		<h1>공연조회</h1>
		<button type="button" id="btn1">검색</button><input id="search" type="text" name="keyword" size=10>
		<br>
		<button type="button" id="btn2">선택삭제</button>
		<button type="button" id="btn3">선택해제</button>
		</div>
		<div id="div1">

		<table border="1" width=600>
			<tr>
				<th>선택</th>
				<th>공연번호</th>
				<th>공연제목</th>
				<th>시작일</th>
				<th>종료일</th>
				<th>장르</th>
			</tr>
			<tr>
				<td><input type="checkbox" name="performance" value="공연번호"></td>
				<td>P00001</td>
				<td>싸이콘서트</td>
				<td>2017.10.20</td>
				<td>2017.10.30</td>
				<td>콘서트</td>
			</tr>
		</table>
	</form>
</body>
</html>