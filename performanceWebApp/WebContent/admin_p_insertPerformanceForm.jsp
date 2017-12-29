<%@ page contentType="text/html; charset=utf-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>공연 등록</title>

<style>
form {
	color: gray;
	width: 900px;
	height:100%;
}
input{height:30px;}
select{height:30px;}
#btn1 {
	display: inline-block;
    text-align: center;
    vertical-align: middle;
    text-decoration: none;
    font-size: 12px;
    color: #000;
    /* border: 1px solid #000; */
    width:65px;
    height: 30px;
    line-height: 30px;
    color: white;
    background-color: blue;
    border-radius: 5px;
}

#btn2 {
	display: inline-block;
    text-align: center;
    vertical-align: middle;
    text-decoration: none;
    font-size: 12px;
    color: #000;
    /* border: 1px solid #000; */
    width:65px;
    height: 30px;
    line-height: 30px;
    color: white;
    background-color: gray;
    border-radius: 5px;
}

h2 {
	color:#000;
}

#div1 {
	margin-bottom: 20px;
	text-align: left;
	margin-top:30px;
}

#div1>#btn2 {
	margin-left: 10px;
}

#div2 {
	margin-top: 20px;
	text-align: left;
	font-size:15px;
}

.insertTable th{background: #ddd;
    width: 120px;
    font-size: 15px;
    border-bottom: 1px solid gray;
    height: 30px;
    line-height: 30px;}
    
.insertTable td{font-size:15px; background:#f0f0f0;border-bottom:1px solid gray;  height: 30px;
    line-height: 30px; }    
.content1{overflow:hidden}
#pannel{float:left;font-size: 15px; color: #000;}
.title{float:left; font-size:15px; color:#000;}
.wrap{padding:50px;}
</style>
<script src="js/jquery-3.2.1.min.js"></script>
<script>
      $(document).ready(function() {
    	  
    	  $('#btn1').click(function() {
    		  alert("공연 정보가 등록되었습니다! 일정 등록을 해주세요!") 	  
    	  });
    	  
    	  $('#btn2').click(function() {
     			location.href="${pageContext.request.contextPath}/admin_p_selectPerformanceList.do";
     	  });
      });
</script>
</head>
<body>
	<div class="wrap">
	<form action="${pageContext.request.contextPath}/uploadFile"
		enctype="multipart/form-data" method="post">
	<div class="content1">
		<h2><strong>공연 등록</strong></h2><br>
		<div id="pannel" >
			메인포스터:<input type="file" name="mainPoster" size="20"></input><br>
			부가포스터:<input type="file" name="poster" size="20"></input><br>
			<input type="file" name="poster" size="20"></input><br>
			<input type="file" name="poster" size="20"></input><br>
		</div>
		<div class="title">
		<h3>
				공연제목 : <input type="text" name="title" size="20"
					placeholder="공연 제목을 입력해주세요!" autofocus style="height:20px;"></input>
		</h3>
		<br>
			<button type="submit" id="btn1">등록</button>
		<button type="reset" id="btn2">취소</button>
		</div>

		</div>
		<div id="div1">
			
			<table width=1000 cellspacing="0" colspacing="0" class="insertTable">
				<tr>
					<th>동영상</th>
					<td><input type="url" name="video" size="40"></input></td>
					<th style="background:#f0f0f0"></th>
					<td></td>
				</tr>
				<tr>
					<th>시작일</th>
					<td><input type="date" name="startDate" size="15"></td>
					<th>종료일</th>
					<td><input type="date" name="endDate" size="15"></td>
				</tr>
				<tr>
					<th>제작사</th>
					<td><input type="text" name="production" size="15"></td>
					<th>가격</th>
					<td><input type="text" name="price" size="15"></input></td>
				</tr>
				<tr>
					<th>관람등급</th>
					<td><select id="view_Class" name="viewNo">
							<option value="V001">전체관람가</option>
							<option value="V002">12세 이상</option>
							<option value="V003">15세 이상</option>
							<option value="V004">청소년관람불가</option>
					</select></td>
					<th>장르</th>
					<td><select id="genre" name="genre">
							<option value="G001">연극</option>
							<option value="G002">뮤지컬</option>
							<option value="G003">콘서트</option>
					</select></td>
				</tr>
				<tr>
					<th>담당자</th>
					<td><input type="text" name="contactName" size="15"></td>
					<th>담당자 전화번호</th>
					<td><input type="text" name="contactNumber" size="15"></td>
				</tr>
				<tr>
					<th>런닝타임</th>
					<td><input type="text" name="runningTime" size="15"></td>
					<th>비고</th>
					<td><input type="text" name="note" size="15"></td>
				</tr>
			</table>
		</div>
		<br>
	
		<br>

		<hr width="900" align="center" color="black" size="1">

		<div id="div2">
			<h2 style="margin-bottom:20px;">상세설명</h2>
			<input type="file" name="detailFile" size="20"></input><br>
			<input type="file" name="detailFile" size="20"></input><br>
			<input type="file" name="detailFile" size="20"></input><br>
			<input type="file" name="detailFile" size="20"></input><br>

		</div>
		<br>
	</form>
	</div>
</body>
</html>