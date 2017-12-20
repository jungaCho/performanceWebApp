<%@page contentType="text/html; charset=utf-8" %> 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta  charset=utf-8>
<title>공연 상세조회 페이지</title>
<style>

	form {
	
		padding : 30px;
		background-color : gray;
		width: 800px;
		height: 750px;
	
	}
	
	li{
		display:inline-block;
		margin:5px 50px;
		
	}
	a{
		text-decoration:none;
	}
	#div2{
		overflow:hidden;
	}
	#div2-1{
		margin:15px 150px;
		text-align:center;
		float:left;
		width:65%;
	}
	#div2-2{
	
		float:right;
		width:35%;
		font-size:15px;
	}
	#div2-2>ul{
		
		overflow:hidden;
	}
	#div2-2>ul li{
		display:inline-block;
		float:left;
	}
	#div3{
		float:right;
		text-align:center;
	}
	#btn2 {
		width: 80px;
	}
	#div1 {
		float: left;
		text-align:center;
	}
	#div4 {
		text-align:center;

	}


</style>
</head>
<body>
	<form>
		<div id="div2">
			<div id="div2-1">
				<input id="keyword" type="search" placeholder="검색어를 입력하세요" size=60>
				<button id="btn1" type="submit">검색</button>
			</div>
		</div>
		<br>
		<br>
		<div id="div1">
		<table border="1" width=200>
			<tr>
				<td colspan='3'>메인포스터</td>
			</tr>
			<tr>
				<td>포스터1</td>
				<td>포스터2</td>
				<td>포스터3</td>
			</tr>
			<tr>
				<td colspan='3'><button type="button" id="btn2" type="button">예매하기</button></td>
			</tr>
		</table>
		</div>
		<div id="div3">
		<table id="detailPerformance" border="1" width=600>
			<tr>
				<td colspan='2'>연극(병동소녀는 집으로, 돌아가지 않는다)</td>
			<tr>
				<th>기간</th>
				<td>2017.10.20 ~ 2017.10.30</td>
			</tr>
			<tr>
				<th>시간</th>
				<td>오후 8:00</td>
			</tr>
			<tr>
				<th>장소</th>
				<td>콘서트홀</td>
			</tr>
			<tr>
				<th>관람등급</th>
				<td>전체관람가</td>
			</tr>
			<tr>
				<th>관람시간</th>
				<td>120분</td>
			</tr>
			<tr>
				<th>장르</th>
				<td>콘서트</td>
			</tr>
			<tr>
				<th>가격</th>
				<td>120000원</td>
			</tr>
		</table>
		<br>
		</div>
		
		<br>
		<hr width="800" align = "center" color = "black" size ="1">
		<br>
		
		<div id="div4">
		<table id="detailFile" border="1" width=800>
			<tr>
				<td>할인정보</td>
			</tr>
			<tr>
				<td>상세정보</td>
			</tr>

		</table>
		</div>
		
	</form>
</body>
</html>