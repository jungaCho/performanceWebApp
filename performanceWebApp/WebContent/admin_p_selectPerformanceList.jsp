<%@ page contentType="text/html; charset=utf-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>공연 검색</title>

<style>
form {
	padding: 30px;
	background-color: gray;
	width: 900px;
	height: 750px;
}

#btn1 {
	width: 70px;
	height: 30px;
	margin-right: 10px;
}

#btn2 {
	width: 70px;
	height: 30px;
	margin-right: 10px;
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

#search {
	float: right;
	margin-right: 100px;
}

#schedule {
	float: right;
	margin-right: 235px;
}

#div3{
		float:left;
		text-align:center;
	}
</style>

</head>
<body>
	<form>

		<div id="pannel">
			<h1>공연 조회</h1>
			<button type="button" id="btn1">선택삭제</button>
			<button type="button" id="btn2">선택해제</button>
		</div>

		<div id="search">
			<select id="keyfield" name="keyfield">
						<option value="제목">제목</option>
						<option value="기간">기간</option>
						<option value="장르">장르</option>
					</select>
			<input id="keyword" type="search" placeholder="검색어를 입력하세요">
			<button type="button" id="btn3">검색</button>
		</div>
		<br>
		<br>
		<div id="div3">
		<table border="1" width=670>
			<tr>
				<th>선택</th>
				<th>공연번호</th>
				<th>공연제목</th>
				<th>시작일</th>
				<th>종료일</th>
				<th>장르</th>
			</tr>
			<tr>
				<td><input type="checkbox" name="check"></td>
				<td>T00001</td>
				<td>빌리 엘리어트</td>
				<td>2017/12/20</td>
				<td>2017/12/30</td>
				<td>연극</td>
			</tr>
		</table>
		</div>
		<br>
	</form>
</body>
</html>