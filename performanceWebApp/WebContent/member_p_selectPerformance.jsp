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
		<input type="text" >
	</form>
</body>
</html>