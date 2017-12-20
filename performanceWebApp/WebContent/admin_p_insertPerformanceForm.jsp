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
		width: 900px;
		height: 750px;
	
	}

	#btn1 {

		width: 80px;
		height: 40px;
		margin-right: 50px;
	}
	
	#btn2 {

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

	
	</style>

</head>
<body>
	<form action="${pageContext.request.contextPath}/uploadFile" enctype="multipart/form-data" method="post">
	
		<div id="pannel">
		<h1>공연 등록</h1><input type="file" name="poster" size="20"></input><br>
		<input type="file" name="poster" size="20"></input><br>
		<input type="file" name="poster" size="20"></input><br>
		<input type="file" name="poster" size="20"></input><br>
		</div>
		<div id="div1">
		<h3>공연제목 : <input type="text" name="title" size="20" placeholder="공연 제목을 입력해주세요!" autofocus></input><h3>

		<table border="1" width=800>
			<tr>
				<th>동영상</th>
				<td colspan="3"><input type="text" name="video" size="80"></input></td>
			</tr>
			<tr>
				<th>시작일</th>
				<td><input type="date" name="start_date" size="15"></td>
				<th>종료일</th>
				<td><input type="date" name="end_date" size="15"></td>
			</tr>
			<tr>
				<th>제작사</th>
				<td><input type="text" name="production" size="15"></td>
				<th>공연장소</th>
				<td><input type="text" name="theater" size="15"></td>
			</tr>
			<tr>
				<th>관람등급</th>
				<td><input type="text" name="view_class" size="15"></td>
				<th>장르</th>
				<td><input type="text" name="genre" size="15"></td>
			</tr>
			<tr>
				<th>담당자</th>
				<td><input type="text" name="contact_name" size="15"></td>
				<th>담당자 전화번호</th>
				<td><input type="text" name="contact_number" size="15"></td>
			</tr>
			<tr>
				<th>런닝타임</th>
				<td><input type="text" name="running_Time" size="15"></td>
				<th>비고</th>
				<td><input type="text" name="note" size="15"></td>
			</tr>
		</table>
		<br>
		<button type="button" id="btn1">등록</button>
		<button type="button" id="btn2">취소</button>

		<br>

		<hr width="900" align = "center" color = "black" size ="1">
		
		<div id="div2">
		<h2>상세설명<h2><input type="file" name="detailFile" size="20"></input><br>
		<input type="file" name="detailFile" size="20"></input><br>
		<input type="file" name="detailFile" size="20"></input><br>
		<input type="file" name="detailFile" size="20"></input><br>
		
		</div>
		<br>	
	</form>
</body>
</html>