<%@ page contentType="text/html; charset=utf-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>일정 등록</title>

	<style>
	
	form {
	
		padding : 30px;
		background-color : gray;
		width: 600px;
		height: 550px;
	
	}

	button {

		width: 80px;
		height: 40px;
		margin-left: 50px;
		margin-right: 120px;
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
		text-align: center;
	}
	
	</style>
<script src="js/jquery-3.2.1.min.js"></script>
<script>

	$(documen).ready(function() {
		$('#sDate').text()
	});
</script>
	
</head>
<body>
	<form>
	
		<div id="pannel">
		<h2>공연 일정 등록</h2>
		</div>
	
		<div id="div1">
		
		공연장소 : <input type="text" name="theater" size="30" ></input><br>
		공연제목 : <input type="text" name="title" size="30" ></input><br>
		시작일자 : <input type="date" name="startDate" size="30" ></input><br>
		종료일자 : <input type="date" name="endDate" size="30" ></input><br>
		공연시간 : <input type="time" name="oTime" size="30" ></input><br>

		<br>

		<hr width="600" align = "center" color = "black" size ="1">
		
		<div id="div2">
		<table border="1" width=400>
			<tr>
				<th>일자</th>
				<th>시간</th>
			</tr>
			<tr>
				<td id="sDate"></td>
				<td id="oTime"></td>
		</table>
		
		</div>
		<br>
		<button type="submit" id="btn1">확인</button>
		<button type="rest" id="btn2">취소</button>
	
	
	</form>
</body>
</html>