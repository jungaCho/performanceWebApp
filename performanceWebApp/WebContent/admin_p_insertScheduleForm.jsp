<%@ page contentType="text/html; charset=utf-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>일정 등록</title>

<style>
form {
	padding: 30px;
	background-color: gray;
	width: 600px;
	height: 550px;
}

button {
	width: 80px;
	height: 40px;
	margin-left: 50px;
	margin-right: 120px;
}

#pannel>h2 {
	display: inline-block;
	margin-right: 10px;
}

#div1 {
	margin-bottom: 20px;
	text-align: left;
}

#div1>#btn2 {
	margin-left: 10px;
}

#div2 {
	margin-top: 20px;
	text-align: center;
}
</style>
<script src="js/jquery-3.2.1.min.js"></script>
<script>
	$(document).ready(function() {

		var scheduleArray = new Array();

		$(#btn1).on('click', function() {
			
			var theater = $(this).text();
			var title = $(this).text();
			var sDate = $(this).text();
			var oTime = $(this).text();
		
			$('#theater').text(scheduleArray.join());
			$('#title').text(scheduleArray.join());
			$('#sDate').date(scheduleArray.join());
			$('#oTime').time(scheduleArray.join());

		});
	});
</script>

</head>
<body>
	<form method="get">

		<div id="pannel">
			<h2>공연 일정 등록</h2>
		</div>

		<div id="div1">
			공연장소 : <select id="theater" name="theater">
				<option value="T001">소극장</option>
				<option value="T002">대극장</option>
				<option value="T003">콘서트홀1</option>
				<option value="T004">콘서트홀2</option>
				<option value="T005">뮤지컬관</option>
			</select><br>
			공연제목 : <input type="text" id="title" name="title" size="15"></input><br>
			공연일자 : <input type="date" id="date" name="sDate" size="30"></input><br>
			공연시간 : <input type="time" id="oTime" name="oTime" size="30"></input><br> <br>
			<button type="submit" id="btn1">확인</button>
			<button type="reset" id="btn2">취소</button>
			<br> <br>

			<hr width="600" align="center" color="black" size="1">
		</div>

		<div id="div2">
			<table border="1" width=400>
				<tr>
					<th>장소</th>
					<th>제목</th>
					<th>일자</th>
					<th>시간</th>
				</tr>
				<tr>
					<td id="theater"></td>
					<td id="title"></td>
					<td id="sDate"></td>
					<td id="oTime"></td>
			</table>

		</div>
		<br>
		<button type="submit" id="btn3">등록</button>
		<button type="reset" id="btn4">뒤로가기</button>
	</form>
</body>
</html>