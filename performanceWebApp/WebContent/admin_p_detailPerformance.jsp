<%@ page contentType="text/html; charset=utf-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>공연상세조회</title>

	<style>
	
	form {
	
		padding : 30px;
		background-color : gray;
		width: 900px;
		height: 750px;
	
	}
	
	button {
	
		width: 80px;
		height: 40px;
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
	
	#schedule{
		
		float: right;
		margin-right: 235px;
		
	}

	
	</style>

</head>
<body>
	<form>
	
		<div id="pannel">
		<h1>공연상세조회</h1>
		</div>
		
		<table id="schedule" border="1" width=300>
			<tr>
				<th>날짜</th>
				<th>공연시간</th>
			</tr>
			<tr>
				<td>2017/10/20</td>
				<td>오후 06:00</td>
		</table>
		
		
		<div id="div1">
		공연번호 : 공연번호<br><br>
		공연제목 : 공연제목
		</div>

		<table border="1" width=670>
			<tr>
				<th>동영상</th>
				<td>url</td>
				<th>가격</th>
				<td>200000</td>
			</tr>
			<tr>
				<th>시작일</th>
				<td>2017/10/09</td>
				<th>종료일</th>
				<td>2017/10/30</td>
			</tr>
			<tr>
				<th>제작사</th>
				<td>바람</td>
				<th>공연장소</th>
				<td>소극장</td>
			</tr>
			<tr>
				<th>관람등급</th>
				<td>청소년관람불가</td>
				<th>장르</th>
				<td>연극</td>
			</tr>
			<tr>
				<th>담당자</th>
				<td>김길동</td>
				<th>담당자 전화번호</th>
				<td>010-1111-2222</td>
			</tr>
			<tr>
				<th>런닝타임</th>
				<td>120분</td>
				<th>비고</th>
				<td></td>
			</tr>
		</table>
		<br>
		<button type="button" id="btn1">수정</button>
		<button type="button" id="btn2">삭제</button>
	
	</form>
</body>
</html>