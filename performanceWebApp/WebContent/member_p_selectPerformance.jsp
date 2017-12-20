<%@page contentType="text/html; charset=utf-8" %> 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta  charset=utf-8>
<title>공연 이미지 조회 페이지</title>
<style>
	#div1{
		width:100%;
		height:35px;
		background-color:yellow;	
		text-align:center;
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
		margin:15px 5px;
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
</style>
</head>
<body>
	<form>
		<div id="div1">
			<ul>
				<li><a href='#'>이전달</a></li>
				<li><a href='#'>현재달</a></li>
				<li><a href='#'>다음달</a></li>
			</ul>
		</div>
		<div id="div2">
		<div id="div2-1">
			<input type="text" placeholder="검색어를 입력하세요">
			<button id="btn1" type="submit">검색</button>
		</div>
		<div id="div2-2">
			<ul id="ul1">
				<li>이미지보기</li>
				<li>텍스트보기</li>
				<li>공연장르</li>
				<ul id="ul2">
					<li>뮤지컬</li>
					<li>연극</li>
					<li>콘서트</li>
				</ul>
			</ul>
		</div>
		</div>
	</form>
</body>
</html>