<%@ page contentType="text/html; charset=utf-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>공연 등록</title>

	<style>
	
	form {
	
		padding : 30px;
		background-color : gray;
		width: 800px;
		height: 550px;
	
	}

	#btn2 {

		width: 100px;
		height: 50px;
		margin-right: 50px;
	}
	
	#btn3 {

		width: 100px;
		height: 50px;
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
		<h2>공연 등록</h2><button type="button" id="btn1">사진추가</button>
		</div>
		<div id="div1">
		공연제목 : <input type="text" name="theater" size="30" placeholder="공연 제목을 입력해주세요" autofocus></input><br>
		<button type="button" id="btn2">등록</button>
		<button type="button" id="btn3">취소</button>

		<table border="1" width=400>
			<tr>
				<th>이미지</th>
				<td>가나다라</td>
				<th>동영상</th>
				<td>가나다라</td>
			</tr>
			<tr>
				<th>시작일</th>
				<td>가나다라</td>
				<th>종료일</th>
				<td>가나다라</td>
			</tr>
			<tr>
				<th>제작사</th>
				<td>가나다라</td>
				<th>공연장소</th>
				<td>가나다라</td>
			</tr>
			<tr>
				<th>관람등급</th>
				<td>가나다라</td>
				<th>장르</th>
				<td>가나다라</td>
			</tr>
			<tr>
				<th>담당자</th>
				<td>가나다라</td>
				<th>담당자 전화번호</th>
				<td>가나다라</td>
			</tr>
			<tr>
				<th>비고</th>
				<td>가나다라</td>
				<th>런닝타임</th>
				<td>가나다라</td>
			</tr>
		</table>

		<br>

		<hr width="600" align = "center" color = "black" size ="1">
		
		<div id="div2">
		<h2>상세설명<h2><button type="button" id="btn1">파일추가</button>
		
		</div>
		<br>	
	</form>
</body>
</html>